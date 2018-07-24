package iiking.ncvt.zxz;

import java.io.*;
import java.util.ArrayList;
import android.app.AlertDialog;
import iiking.base.BaseActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import iiking.database.DatabaseHelper;
import android.database.sqlite.SQLiteDatabase;
import iiking.model.Fonts;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class WriteboardActivity extends BaseActivity implements
        OnClickListener {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        checkDatabase();

        ImageButton imgbtn1 = (ImageButton) findViewById(R.id.imgbtn1);
        ImageButton imgbtn2 = (ImageButton) findViewById(R.id.imgbtn2);
        ImageButton imgbtn3 = (ImageButton) findViewById(R.id.imgbtn3);

        imgbtn1.setOnClickListener(this);
        imgbtn2.setOnClickListener(this);
        imgbtn3.setOnClickListener(this);

        // 查询数据
        DatabaseHelper dbHelper = new DatabaseHelper(
                WriteboardActivity.this, "Practicefont.db");

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
            String sdir = "/data/data/iiking.ncvt.zxz/databases";
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
        Intent intent = new Intent();
        if (view.getId() == R.id.imgbtn1) {
            intent.setClass(WriteboardActivity.this,
                    ChooseCourseActivity.class);
            WriteboardActivity.this.startActivity(intent);
        } else if (view.getId() == R.id.imgbtn2) {
            intent.setClass(WriteboardActivity.this,
                    ChoosefontActivity.class);
            WriteboardActivity.this.startActivity(intent);
        } else if (view.getId() == R.id.imgbtn3) {
            intent.setClass(WriteboardActivity.this, Imformation.class);
            WriteboardActivity.this.startActivity(intent);
        }
    }

}