package android.ncvt.zxz;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class Papy extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏

		this.setContentView(R.layout.papymain);

		final Intent localIntent=new Intent(this,PracticeWriteboardActivity.class);
		Timer timer=new Timer();

		//设置定时器
		TimerTask tast=new TimerTask()
		{
			@Override
			public void run(){
				startActivity(localIntent);
				finish();//完成显示之后，关闭当前活动
			}
		};
		timer.schedule(tast,2000);

	}

}
