package iiking.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import com.orhanobut.logger.Logger;

import iiking.utils.DisplayUtil;

/**
 * Created by yushanglicai on 2018/7/30.
 */

public class SplashPathView extends View {

    private static final String TAG = SplashPathView.class.getSimpleName();

    //圆点的画笔
    private Paint mPaint0;
    //第一段画笔
    private Paint mPaint1;
    //第二段画笔
    private Paint mPaint2;
    //第三段画笔
    private Paint mPaint3;
    //灰色画笔
    private Paint mPaint4;

    private Context mContext;
    private int mScreenWidth = 0;
    private int mScreenHeight = 0;

    public SplashPathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Logger.init(TAG);
        mContext = context;
        mScreenWidth = DisplayUtil.getScreenWidth(mContext);
        mScreenHeight = DisplayUtil.getScreenHeight(mContext);
        initView();
    }

    private void initView() {
        mPaint0 = new Paint();
        mPaint1 = new Paint();
        mPaint2 = new Paint();
        mPaint3 = new Paint();
        mPaint4 = new Paint();

        //抗锯齿
        mPaint0.setAntiAlias(true);
        mPaint1.setAntiAlias(true);
        mPaint2.setAntiAlias(true);
        mPaint3.setAntiAlias(true);
        mPaint4.setAntiAlias(true);

        //防抖动
        mPaint0.setDither(true);
        mPaint1.setDither(true);
        mPaint2.setDither(true);
        mPaint3.setDither(true);
        mPaint4.setAntiAlias(true);

        //设置画笔未实心
        mPaint0.setStyle(Paint.Style.FILL);
        mPaint1.setStyle(Paint.Style.STROKE);
        mPaint2.setStyle(Paint.Style.STROKE);
        mPaint3.setStyle(Paint.Style.STROKE);
        mPaint4.setStyle(Paint.Style.STROKE);

        //设置颜色
        mPaint0.setColor(Color.RED);
        mPaint1.setColor(Color.GREEN);
        mPaint2.setColor(Color.YELLOW);
        mPaint3.setColor(Color.BLUE);
        mPaint4.setColor(Color.LTGRAY);

        //设置画笔宽度
        mPaint0.setStrokeWidth(18);
        mPaint1.setStrokeWidth(10);
        mPaint2.setStrokeWidth(10);
        mPaint3.setStrokeWidth(10);
        mPaint4.setStrokeWidth(2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Point point1 = new Point(60, mScreenHeight - 160);

        Point point2 = new Point(830, mScreenHeight - 530);
        Point point3 = new Point(mScreenWidth / 3, mScreenHeight - 190);

        Point point4 = new Point(point3.x - 50, point3.y + 75);
        Point point5 = new Point(point1.x + 410, point1.y + 40);

        Point point6 = new Point(point1.x + 350, point1.y - 80);
        Point point7 = new Point(mScreenWidth / 4 * 2, mScreenHeight - 380);

        Path path1 = new Path();
        Path path2 = new Path();
        Path path3 = new Path();

        //移动到初始位置point1
        path1.moveTo(point1.x,point1.y);
        //第一段路径
        path1.cubicTo(point1.x,point1.y,point2.x,point2.y,point3.x, point3.y);

        //移动到初始位置point3
        path2.moveTo(point3.x,point3.y);
        //第二段路径
        path2.cubicTo(point3.x, point3.y, point4.x, point4.y, point5.x, point5.y);

        //移动到初始位置point5
        path3.moveTo(point5.x,point5.y);
        //第三段路径
        path3.cubicTo(point5.x, point5.y, point6.x, point6.y, point7.x, point7.y);

        canvas.drawPath(path1, mPaint1);
        canvas.drawPath(path2, mPaint2);
        canvas.drawPath(path3, mPaint3);

        canvas.drawLine(point1.x, point1.y, point2.x, point2.y, mPaint4);
        canvas.drawLine(point2.x, point2.y, point3.x, point3.y, mPaint4);
        canvas.drawLine(point3.x, point3.y, point4.x, point4.y, mPaint4);
        canvas.drawLine(point4.x, point4.y, point5.x, point5.y, mPaint4);
        canvas.drawLine(point5.x, point5.y, point6.x, point6.y, mPaint4);
        canvas.drawLine(point6.x, point6.y, point7.x, point7.y, mPaint4);

        canvas.drawCircle(point1.x, point1.y, 10, mPaint0);
        canvas.drawCircle(point2.x, point2.y, 10, mPaint0);
        canvas.drawCircle(point3.x, point3.y, 10, mPaint0);
        canvas.drawCircle(point4.x, point4.y, 10, mPaint0);
        canvas.drawCircle(point5.x, point5.y, 10, mPaint0);
        canvas.drawCircle(point6.x, point6.y, 10, mPaint0);
        canvas.drawCircle(point7.x, point7.y, 10, mPaint0);

    }
}
