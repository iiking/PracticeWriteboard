package android.ncvt.zxz;

import java.util.ArrayList;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.SurfaceHolder.Callback;

public class MyPaintView extends SurfaceView implements Callback, Runnable {

    //true 跟随，false 不跟随
    boolean isFollow = false;

    public boolean getFollow() {
        return isFollow;
    }

    public void setFollow(boolean f) {
        this.isFollow = f;
    }

    //控制绘图循环
    boolean mbLoop = false;

    private ArrayList<Path> aPath = new ArrayList();
    private Path mPath;

    private float mX, mY;
    private static final float TOUCH_TOLERANCE = 4;

    //定义SurfaceHolder对象
    private SurfaceHolder mSurfaceHolder = null;
    private ArrayList<Bitmap> aImage;
    public ArrayList<Bitmap> getaImage() {
        return aImage;
    }

    public void setaImage(ArrayList<Bitmap> aImage) {
        this.aImage = aImage;
        miCurPos = aImage.size() - 1;
    }

    private Bitmap bkImage;
    private int miCurPos = 0;

    public void setMiCurPos(int miCurPos) {
        this.miCurPos = miCurPos;
    }

    //界面的刷新频率
    private int miFlushFreq = 10;
    //字的刷新频率
    public static int miDrawFreq = 50;
    //多少帧刷新
    private int miFreqCount = 0;
    //控制播放
    private boolean mbPlay = false;
    //控制播放是否多次循环
    private boolean mbPlayOne = true;

    public MyPaintView(Context context) {
        super(context);
        mSurfaceHolder = this.getHolder();
        mSurfaceHolder.addCallback(this);
        this.setFocusable(true);
        mbLoop = true;
        //设置画板背景
        bkImage = ((BitmapDrawable) getResources().getDrawable(R.raw.bg)).getBitmap();
        miFreqCount = 0;
    }

    public void run() {
        while (mbLoop) {
            try {
                Thread.sleep(this.miFlushFreq);
            } catch (Exception e) {
                e.printStackTrace();
            }
            synchronized (mSurfaceHolder) {
                Draw();
            }
        }
    }

    public void Draw() {

        Canvas canvas = mSurfaceHolder.lockCanvas();

        if (mSurfaceHolder == null || canvas == null)
            return;

        Paint mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.GRAY);

        //计算画布的大小
        int nCanvasWidth = canvas.getWidth();
        int nCanvasHeight = canvas.getHeight();

        //计算图像的大小
        int nImgWidth = bkImage.getWidth();
        int nImgHeight = bkImage.getHeight();

        //定义缩放比率
        Matrix matrix = new Matrix();
        matrix.postScale(nCanvasWidth * 1.0f / nImgWidth, nCanvasHeight * 1.0f / nImgHeight);

        //更新背景
        canvas.drawRect(0, 0, nCanvasWidth, nCanvasHeight, mPaint);

        //添加米字背景
        Bitmap tBkImg = Bitmap.createBitmap(bkImage, 0, 0, nImgWidth, nImgHeight, matrix, true);
        canvas.drawBitmap(tBkImg, 0, 0, null);

        //添加字的图像
        Bitmap t = aImage.get(miCurPos);
        int tw = t.getWidth(), th = t.getHeight();
        Matrix matrix1 = new Matrix();
        matrix1.postScale(nCanvasWidth * 1.0f / tw, nCanvasHeight * 1.0f / th);

        Bitmap tImage = Bitmap.createBitmap(aImage.get(miCurPos), 0, 0, tw, th, matrix1, true);
        canvas.drawBitmap(tImage, 0, 0, null);

        //画笔迹
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setColor(Color.GREEN);

        for (int i = 0; i < aPath.size(); i++)
            canvas.drawPath(aPath.get(i), paint);

        //判断下一帧需要显示的字图像
        miFreqCount++;

        if (miFreqCount >= miDrawFreq / miFlushFreq) {
            if (mbPlay) {
                miCurPos++;
                if (mbPlayOne) {
                    if (miCurPos >= aImage.size()) {
                        miCurPos = aImage.size() - 1;
                        mbPlay = false;
                    }
                } else {
                    if (miCurPos >= aImage.size()) {
                        miCurPos = 0;
                    }
                }
            }
            miFreqCount = 0;
        }
        mSurfaceHolder.unlockCanvasAndPost(canvas);
    }

    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {

    }

    public void surfaceCreated(SurfaceHolder arg0) {
        //开始绘图线程
        new Thread(this).start();
    }

    public void surfaceDestroyed(SurfaceHolder arg0) {
        mbLoop = false;
    }

    //播放一次
    public void startPlayOne() {
        mbPlayOne = true;
        miCurPos = 0;
        mbPlay = true;
    }

    public void clearPens() {
        aPath.clear();//清除痕迹
    }

    private void touchStart(float x, float y) {
        mPath = new Path();
        aPath.add(mPath);
        mPath.moveTo(x, y);
        this.mX = x;
        this.mY = y;
    }

    private void touchUp() {

        mPath.lineTo(mX, mY);

        if (isFollow == true) {
            this.miCurPos++;

            if (miCurPos >= aImage.size()) {
                miCurPos = aImage.size() - 1;
                aPath.clear();
            }
        }
    }

    private void touchMove(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchStart(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                touchMove(x, y);
                break;
            case MotionEvent.ACTION_UP:
                touchUp();
                break;
            default:
                break;
        }
        return true;
    }

}
