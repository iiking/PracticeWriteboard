package iiking.ncvt.zxz;

import iiking.base.BaseActivity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import iiking.database.DatabaseHelper;
import android.database.sqlite.SQLiteDatabase;
import iiking.model.Fonts;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;
import java.io.IOException;
import java.util.ArrayList;

public class ChoosefontActivity extends BaseActivity implements OnClickListener {

	ArrayList<Fonts> al = new ArrayList<Fonts>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_choose_font);

		Bundle bundle = getIntent().getExtras();

		String classname = "";

		if (bundle != null)
			classname = bundle.getString("classname");

		// 查询数据
		DatabaseHelper dbHelper = new DatabaseHelper(ChoosefontActivity.this,"Practicefont.db");

		// 要执行这个才会创建数据库
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor;

		if (classname == null || classname.equals(""))
			cursor = db.query("fontpacket", new String[] { "id", "name", "url",
					"size" }, null, null, null, null, null);
		else
			cursor = db.query("fontpacket", new String[] { "id", "name", "url",
							"size" }, "classname=?", new String[] { classname }, null,
					null, null);

		cursor.moveToFirst();

		do {
			Fonts font = new Fonts();
			int id = cursor.getInt(0);
			font.setId(id);
			font.setName(cursor.getString(1));
			font.setUrl(cursor.getString(2));
			font.setSize(cursor.getInt(3));
			al.add(font);
		} while (cursor.moveToNext());

		cursor.close();
		db.close();

		TableLayout layout = (TableLayout) findViewById(R.id.TableLayout1);
		layout.removeAllViews();
		int k = 0, m = al.size() / 3, n = al.size() - m * 3;
		for (int i = 0; i < 3; i++) {
			TableRow row = new TableRow(this);

			for (int j = 0; j < m; j++) {
				Button btn = new Button(this);
				btn.setText(al.get(k).getName());
				int id = al.get(k).getId();
				int newid = 0x7f050000 + id;
				btn.setId(newid);
				btn.setBackgroundResource(R.drawable.fontbotton);
				btn.setTextSize(30);
				btn.setTag(al.get(k));
				k++;
				btn.setOnClickListener(this);

				row.addView(btn);
			}
			if ((n == 1 || n == 2) && i == 0) {
				Button btn = new Button(this);
				btn.setText(al.get(k).getName());
				int id = al.get(k).getId();
				int newid = 0x7f050000 + id;
				btn.setId(newid);
				btn.setBackgroundResource(R.drawable.fontbotton);
				btn.setTextSize(30);
				btn.setTag(al.get(k));
				k++;
				btn.setOnClickListener(this);

				row.addView(btn);
			} else if (n == 2 && i == 1) {
				Button btn = new Button(this);
				btn.setText(al.get(k).getName());
				int id = al.get(k).getId();
				int newid = 0x7f050000 + id;
				btn.setId(newid);
				btn.setBackgroundResource(R.drawable.fontbotton);
				btn.setTextSize(30);
				btn.setTag(al.get(k));
				k++;
				btn.setOnClickListener(this);
				row.addView(btn);
			}
			layout.addView(row);
		}

	}

	public void onClick(View v) {

		Fonts font = (Fonts) v.getTag();
		AssetManager as =  this.getAssets();

		try {
			as.open(font.getUrl().substring(19)+"/"+0+".png");
		} catch (IOException e) {
			e.printStackTrace();
			Toast toast = Toast.makeText(getApplicationContext(), "该字的数据正在制作中,请关注软件更新！",
					Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER, 0, 0);
			LinearLayout toastView = (LinearLayout) toast.getView();
			ImageView imageCodeProject = new ImageView(getApplicationContext());
			imageCodeProject.setImageResource(R.drawable.suo);
			toastView.addView(imageCodeProject, 0);
			toast.show();
			return;
		}
		Intent intent = new Intent();
		intent.setClass(ChoosefontActivity.this, MyPaintActivity.class);
		intent.putExtra("font", font);
		this.startActivity(intent);
	}

}