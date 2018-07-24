package android.ncvt.zxz;

import java.io.*;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseHelper;
import android.database.sqlite.SQLiteDatabase;
import android.entity.Fonts;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class PracticeWriteboardActivity extends Activity implements
        OnClickListener {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        checkDatabase();

        // 这里是实现全屏
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 去掉信息栏

        setContentView(R.layout.main);

        ImageButton imgbtn1 = (ImageButton) findViewById(R.id.imgbtn1);
        ImageButton imgbtn2 = (ImageButton) findViewById(R.id.imgbtn2);
        ImageButton imgbtn3 = (ImageButton) findViewById(R.id.imgbtn3);

        imgbtn1.setOnClickListener(this);
        imgbtn2.setOnClickListener(this);
        imgbtn3.setOnClickListener(this);

        // 查询数据
        DatabaseHelper dbHelper = new DatabaseHelper(
                PracticeWriteboardActivity.this, "Practicefont.db");

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query("fontpacket", new String[]{"id", "name",
                "url"}, null, null, null, null, null);
        int n = cursor.getCount();
        cursor.moveToFirst();
        ArrayList<Fonts> al = new ArrayList<Fonts>();
        if (n > 0) {
            do {
                Fonts font = new Fonts();
                int id = cursor.getInt(0);
                font.setId(id);
                font.setName(cursor.getString(1));
                al.add(font);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
    }

    private void checkDatabase() {
        try {
            String sdir = "/data/data/android.ncvt.zxz/databases";
            File dir = new File(sdir);
            // 如果不存在，创建这个目录
            if (!dir.exists())
                dir.mkdir();

            String databaseFilename = sdir + "/Practicefont.db";
            if (!(new File(databaseFilename)).exists()) {
                InputStream is = getResources().openRawResource(R.raw.db);
                FileOutputStream fos = new FileOutputStream(databaseFilename);
                byte[] buffer = new byte[8192];
                int count = 0;
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }

                fos.close();
                is.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // <--判断是否退出
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 按下键盘上返回按钮
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            new AlertDialog.Builder(this)
                    .setTitle("退出软件")
                    .setMessage("确认退出安卓练字板？")
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub
                        }
                    })
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {
                            finish();
                        }

                    }).show();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    // -->退出登录
    public void onClick(View view) {
        // TODO Auto-generated method stub
        Intent intent = new Intent();
        if (view.getId() == R.id.imgbtn1) {

            intent.setClass(PracticeWriteboardActivity.this,
                    ListViewActivity.class);
            PracticeWriteboardActivity.this.startActivity(intent);

        } else if (view.getId() == R.id.imgbtn2) {
            intent.setClass(PracticeWriteboardActivity.this,
                    ChoosefontActivity.class);

            PracticeWriteboardActivity.this.startActivity(intent);

        } else if (view.getId() == R.id.imgbtn3) {
            intent.setClass(PracticeWriteboardActivity.this, Imformation.class);
            PracticeWriteboardActivity.this.startActivity(intent);
        }
    }
}