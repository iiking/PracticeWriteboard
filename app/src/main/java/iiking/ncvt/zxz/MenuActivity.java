package iiking.ncvt.zxz;

import android.app.Activity;
import android.content.Intent;
import iiking.model.User;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MenuActivity extends Activity implements OnClickListener {

    private SeekBar seekbar;
    private TextView tvbar;
    private Button btmenu01;
    private Button btmenu02;
    public User miDraw = new User();

    int max = 200;
    boolean flag = true;

    private Handler handler = new Handler() {

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //这里是实现全屏
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏

        this.setContentView(R.layout.menulist);

        seekbar = (SeekBar) findViewById(R.id.seek1);
        tvbar = (TextView) findViewById(R.id.tbbar);
        btmenu01 = (Button) findViewById(R.id.btmenu01);
        btmenu02 = (Button) findViewById(R.id.btmenu02);
        btmenu01.setOnClickListener(this);
        btmenu02.setOnClickListener(this);

        seekbar.setMax(max);
        seep();

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                MyPaintView.miDrawFreq = progress * 2 / 4;
                miDraw.setMiDrawFreq(progress * 2 / 4);
                tvbar.setText("速度为：" + progress * 2 / 4);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // 停止刷新
                flag = false;
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                flag = true;
                handler.sendEmptyMessage(0);

            }
        });
    }

    private void seep() {
        DisThread dt = new DisThread();
        dt.start();
    }

    private class DisThread extends Thread {
        public void run() {
            while (flag && seekbar.getProgress() < max) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(0);
            }
        }
    }

    public void onClick(View v) {
        Intent intent = new Intent();
        if (v.getId() == R.id.btmenu01) {
            intent.setClass(MenuActivity.this,
                    ChoosefontActivity.class);
            MenuActivity.this.startActivity(intent);
        } else {
            seekbar.setProgress(100);
        }
    }

}
