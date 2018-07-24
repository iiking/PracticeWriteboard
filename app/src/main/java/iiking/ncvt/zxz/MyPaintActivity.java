package iiking.ncvt.zxz;

import iiking.base.BaseActivity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import iiking.model.Fonts;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MyPaintActivity extends BaseActivity implements OnMenuItemClickListener {

    private static final String TAG = MyPaintActivity.class.getSimpleName();

    @InjectView(R.id.ivAnimView)
    ImageView ivAnimView;
    @InjectView(R.id.fontlinearLayout)
    LinearLayout fontlinearLayout;
    @InjectView(R.id.btnOneShot)
    Button btnOneShot;
    @InjectView(R.id.btnStartAnim)
    Button btnStartAnim;
    @InjectView(R.id.btnClear)
    Button btnClear;
    @InjectView(R.id.btnretAnim)
    Button btnretAnim;
    @InjectView(R.id.linearLayout3)
    LinearLayout linearLayout3;

    private int size;
    private ArrayList<Bitmap> aImage = new ArrayList<Bitmap>();
    private MyPaintView myPaintView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.layout_practice_font);
        ButterKnife.inject(this);
        Logger.init(TAG);

        Bundle bundle = getIntent().getExtras();
        Fonts font = (Fonts) bundle.getSerializable("font");
        AssetManager as = this.getAssets();
        String spath = font.getUrl();
        size = font.getSize();
        String path = spath.substring(19);

        try {
            for (int i = 0; i <= size; i++) {
                InputStream s = as.open(path + "/" + i + ".png");
                aImage.add(BitmapFactory.decodeStream(s));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        myPaintView = new MyPaintView(this);
        myPaintView.setaImage(aImage);
        fontlinearLayout.removeAllViews();
        fontlinearLayout.addView(myPaintView);

    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuItem deleteMenuItem = menu.add(1, 1, 1, "播放速度设置");
        deleteMenuItem.setIcon(R.drawable.menu);
        deleteMenuItem.setOnMenuItemClickListener(this);

        MenuItem menuMenuItem = menu.add(1, 2, 2, "关闭");
        menuMenuItem.setIcon(R.drawable.delete);
        menuMenuItem.setOnMenuItemClickListener(this);

        return true;
    }

    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == 2) {
            finish();
        } else {
            Intent intent = new Intent();
            intent.setClass(MyPaintActivity.this, MenuActivity.class);
            MyPaintActivity.this.startActivity(intent);
        }
        return true;
    }

    @OnClick({R.id.btnOneShot, R.id.btnStartAnim, R.id.btnClear, R.id.btnretAnim})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnOneShot:
                myPaintView.clearPens();
                myPaintView.startPlayOne();
                break;
            case R.id.btnStartAnim:
                Button btnStartAnim = (Button) findViewById(R.id.btnStartAnim);
                boolean isFollow = myPaintView.getFollow();
                if (isFollow == true) {
                    btnStartAnim.setText("跟随练习");
                    myPaintView.setMiCurPos(size);
                    myPaintView.setFollow(false);
                    myPaintView.clearPens();
                } else {
                    myPaintView.setFollow(true);
                    btnStartAnim.setText("整字练习");
                    myPaintView.setMiCurPos(1);
                    myPaintView.clearPens();
                }
                break;
            case R.id.btnClear:
                myPaintView.clearPens();
                break;
            case R.id.btnretAnim:
                Intent intent = new Intent();
                intent.setClass(mBaseContext, ChoosefontActivity.class);
                MyPaintActivity.this.startActivity(intent);
                break;
        }
    }
}