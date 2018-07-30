package iiking.ncvt.zxz;

import iiking.base.BaseActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.layout_start);

//        new Handler().postDelayed(new Runnable(){
//            public void run() {
//                Intent localIntent = new Intent(mBaseContext, WriteboardActivity.class);
//                startActivity(localIntent);
//                SplashActivity.this.finish();
//            }
//        }, 2000);


    }

}
