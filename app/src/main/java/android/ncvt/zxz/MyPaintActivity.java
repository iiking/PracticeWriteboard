package android.ncvt.zxz;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.entity.Fonts;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class MyPaintActivity extends Activity implements OnClickListener,
		OnMenuItemClickListener {


	public static int width;

	MyPaintView myPaintView;

	private int size;

	private ArrayList<Bitmap> aImage = new ArrayList<Bitmap>();

	LinearLayout fontlinearLayout;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);// 去掉信息栏

		//获取屏幕的大小

		width = this.getWindow().getWindowManager().getDefaultDisplay().getWidth();

		this.setContentView(R.layout.practicefont);

		Bundle bundle = getIntent().getExtras();

		Fonts font = (Fonts) bundle.getSerializable("font");

		AssetManager as =  this.getAssets();

		String spath= font.getUrl();

		size=font.getSize();

		String path = spath.substring(19);

		try {
			for(int i=0;i<=size;i++)
			{

				InputStream s = as.open(path+"/"+i+".png");

				aImage.add(android.graphics.BitmapFactory.decodeStream(s));

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 这里是对组件设置监听
		Button btnOneShot = (Button) findViewById(R.id.btnOneShot);
		Button btnStartAnim = (Button) findViewById(R.id.btnStartAnim);
		Button btnStopAnim = (Button) findViewById(R.id.btnretAnim);
		Button btnClear = (Button) findViewById(R.id.btnClear);

		btnOneShot.setOnClickListener(this);
		btnStartAnim.setOnClickListener(this);
		btnStopAnim.setOnClickListener(this);
		btnClear.setOnClickListener(this);

		fontlinearLayout = (LinearLayout) findViewById(R.id.fontlinearLayout);

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



	public void onClick(View view) {
		// TODO Auto-generated method stub
		// 这里是监听写字板上的按钮的

		Intent intent = new Intent();

		if (view.getId() == R.id.btnOneShot) {

			myPaintView.clearPens();

			myPaintView.startPlayOne();

		} else if (view.getId() == R.id.btnStartAnim) {

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

		} else if (view.getId() == R.id.btnClear) {

			myPaintView.clearPens();

		} else if (view.getId() == R.id.btnretAnim) {

			intent.setClass(MyPaintActivity.this, ChoosefontActivity.class);

			MyPaintActivity.this.startActivity(intent);

		}

	}
}