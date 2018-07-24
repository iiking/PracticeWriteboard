package android.ncvt.zxz;

import java.util.Timer;
import java.util.TimerTask;

import android.base.BaseActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class StartActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_start);

        new Handler().postDelayed(new Runnable(){
            public void run() {
                Intent localIntent = new Intent(mBaseContext, WriteboardActivity.class);
                startActivity(localIntent);
                StartActivity.this.finish();
            }
        }, 2000);
    }

}
