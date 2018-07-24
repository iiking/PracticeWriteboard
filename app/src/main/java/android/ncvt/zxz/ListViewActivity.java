package android.ncvt.zxz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.entity.User;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ListViewActivity extends Activity implements OnItemClickListener {

    //列表对话框的字符串数组数据源
    private String[] provinces = new String[]
            {
                    "一去二三里", "口耳目", "在家里", "操场上", "画", "四季",
                    "小小竹排画中游", "哪座房子最漂亮", "爷爷和小树", "静夜思",
                    "小小的船", "阳光", "影子", "比尾巴", "比一比", "自选商场",
                    "菜园里", "日月明", "我多想去看看", "雨点儿", "平平搭积木",
                    "自己去吧", "一次比一次有进步", "小松鼠找花生", "雪地里的小画家",
                    "借生日", "雪孩子", "小熊住山洞"
            };

    private User[] arr = new User[10];

    private ArrayList<Map<String, Object>> mData = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置活动的布局
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏

        setContentView(R.layout.userlist);

        arr[0] = new User();
        arr[0].setTitle("小学语文文字----自由练习");
        arr[0].setNews("一共112个字");

        arr[1] = new User();
        arr[1].setTitle("小学语文>>同步教学资源>>一年级上册");
        arr[1].setNews("一共99个字");

        arr[2] = new User();
        arr[2].setTitle("小学语文>>同步教学资源>>一年级下册");
        arr[2].setNews("一共112个字");

        arr[3] = new User();
        arr[3].setTitle("小学语文>>同步教学资源>>二年级上册");
        arr[3].setNews("一共112个字");

        arr[4] = new User();
        arr[4].setTitle("小学语文>>同步教学资源>>二年级下册");
        arr[4].setNews("一共112个字");

        arr[5] = new User();
        arr[5].setTitle("小学语文>>同步教学资源>>三年级上册");
        arr[5].setNews("一共80个字");

        arr[6] = new User();
        arr[6].setTitle("小学语文>>同步教学资源>>三年级下册");
        arr[6].setNews("一共112个字");

        arr[7] = new User();
        arr[7].setTitle("小学语文>>同步教学资源>>四年级上册");
        arr[7].setNews("一共112个字");

        arr[8] = new User();
        arr[8].setTitle("小学语文>>同步教学资源>>四年级下册");
        arr[8].setNews("一共112个字");

        arr[9] = new User();
        arr[9].setTitle("小学语文文字----自由练习");
        arr[9].setNews("一共112个字");

        //初始化要显示的列表
        mData = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < arr.length; i++) {
            Map<String, Object> mp = new HashMap<String, Object>();

            mp.put("Title", arr[i].getTitle());
            mp.put("news", arr[i].getNews());

            //这里是设置LIstViewActivity的图标
            if (i == 0) {
                mp.put("Pic", R.drawable.icon5);
            } else if (i == 1) {
                mp.put("Pic", R.drawable.icon1);
            } else if (i == 2) {
                mp.put("Pic", R.drawable.icon2);
            } else if (i == 3 || i == 4) {
                mp.put("Pic", R.drawable.icon3);
            } else if (i == 5) {
                mp.put("Pic", R.drawable.icon5);
            } else if (i == 6) {
                mp.put("Pic", R.drawable.icon6);
            } else if (i == 7) {
                mp.put("Pic", R.drawable.icon7);
            } else if (i == 8) {
                mp.put("Pic", R.drawable.icon5);
            } else {
                mp.put("Pic", R.drawable.icon);
            }
            mData.add(mp);
        }

        //生成迭代器
        MyAdapter adpter = new MyAdapter(this);
        //查找ListView
        ListView lvResult = (ListView) findViewById(R.id.lvResult);
        //设置迭代器关联
        lvResult.setAdapter(adpter);
        //设置点击事件的监听者
        lvResult.setOnItemClickListener(this);
    }

    //显示列表对话框
    private void showListDialog() {
        //切换到ChoosefontActivity活动
        final Intent intent = new Intent(this, ChoosefontActivity.class);

        new AlertDialog.Builder(this).setTitle("请选择课时").setItems(provinces, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                intent.putExtra("classname", provinces[which]);
                startActivity(intent);
            }

        }).show();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    //点击列表项的响应函数
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        switch (position) {
            case 1:

                showListDialog();
                break;
            default:
                Toast toast = Toast.makeText(getApplicationContext(), "该栏目的数据包正在制作中...",
                        Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                LinearLayout toastView = (LinearLayout) toast.getView();
                ImageView imageCodeProject = new ImageView(getApplicationContext());
                imageCodeProject.setImageResource(R.drawable.filemanage);
                toastView.addView(imageCodeProject, 0);
                toast.show();
        }

    }

    //内部类
    public class ViewHolder {
        public ImageView ivPic;
        public TextView tvTitle;
        public TextView tvnews;
    }

    //派生的迭代器，负责处理显示逻辑的
    public class MyAdapter extends BaseAdapter {

        private LayoutInflater mInflater;

        public MyAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }

        public int getCount() {
            return mData.size();
        }

        public Object getItem(int arg0) {
            return null;
        }

        public long getItemId(int arg0) {
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;

            //判断列表项的视图是否为空，如果为空，我们创建新的view
            if (convertView == null) {
                holder = new ViewHolder();

                //附加新的view
                convertView = mInflater.inflate(R.layout.listitem, null);

                holder.ivPic = (ImageView) convertView.findViewById(R.id.ivPic);
                holder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
                holder.tvnews = (TextView) convertView.findViewById(R.id.tvnews);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            //将数据显示到view
            holder.ivPic.setImageResource((Integer) mData.get(position).get("Pic"));
            holder.tvTitle.setText((String) mData.get(position).get("Title"));
            holder.tvnews.setText((String) mData.get(position).get("news"));
            return convertView;
        }

    }

}