package com.example.lcu_kcb;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.Where;

public class MainActivity extends AppCompatActivity {

    //滑动菜单
    private DrawerLayout mDrawerLayout;
    //星期几
    private RelativeLayout day;
    //当前日期
    protected TextView dateTextView;
    //toast事件
    public static Toast mToast;
    //SQLite Helper类
    DbHelper helper = new DbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //左边侧滑菜单
        mDrawerLayout = (DrawerLayout)findViewById(R.id.draw);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        //工具条
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(MenuItem item){
                switch (item.getItemId()){
                    case R.id.add_courses:
                        Intent intent = new Intent(MainActivity.this, AddCourseActivity.class);
                        startActivityForResult(intent, 0);
                        break;

                    case R.id.menu_about:
                        Intent intent1 = new Intent(MainActivity.this, AboutActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.single_week:
                        Intent intent3 = new Intent(MainActivity.this,SingleWeek.class);
                        startActivityForResult(intent3, 0);
                        break;
                    case R.id.double_week:
                        Intent intent4 = new Intent(MainActivity.this,DoubleWeek.class);
                        startActivityForResult(intent4, 0);
                        break;
                    default:
                        System.out.println("错误");
                }
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
        //创建课程表左边视图(节数)
        createLeftView();
        //从数据库读取数据
        loadData();
        //获取系统时间
        dateTextView = (TextView) navView.getHeaderView(0).findViewById(R.id.Menu_main_textDate);

        //初始化日期与头像
        initDate();


    }

    //从数据库加载数据
    private void loadData() {
        //ArrayList<Course> coursesList = new ArrayList<>(); //课程列表
        DbHelper helper = new DbHelper(this);
        try {
            Dao<Course,Integer> sdao = helper.getDao(Course.class);
            List<Course> sl = sdao.queryForAll();
            //使用从数据库读取出来的课程信息来加载课程表视图
            for (Course course : sl) {
                createCourseView(course);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //保存数据到数据库
    private void saveData(Course course) {
        try {
            Dao<Course,Integer> sdao = helper.getDao(Course.class);
            sdao.create(course);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    //更新数据到数据库
    private void updataData(Course course) {
        try {
            Dao<Course,Integer> sdao = helper.getDao(Course.class);
            sdao.updateId(course,Integer.valueOf(course.getId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //删除数据到数据库
    private void delData(Course course) {
        try {
            Dao<Course,Integer> sdao = helper.getDao(Course.class);
            sdao.deleteById(Integer.valueOf(course.getId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //创建课程节数视图
    private void createLeftView() {
        for(int i=1;i<11;i++)
        {
            View view = LayoutInflater.from(this).inflate(R.layout.left_view, null);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(110,180);
            view.setLayoutParams(params);
            TextView text = (TextView) view.findViewById(R.id.class_number_text);
            text.setText(String.valueOf(i));
            LinearLayout leftViewLayout = (LinearLayout) findViewById(R.id.left_view_layout);
            leftViewLayout.addView(view);
        }
    }

    //创建课程视图
    private void createCourseView(final Course course) {
        final int[] course_bj = {R.drawable.coursetable1, R.drawable.coursetable2,
                R.drawable.coursetable3, R.drawable.coursetable4, R.drawable.coursetable5,
                R.drawable.coursetable6, R.drawable.coursetable7, R.drawable.coursetable8,
                R.drawable.coursetable9, R.drawable.coursetable10};
        int height = 180;
        int getDay = course.getDay();
        if ((getDay < 1 || getDay > 7) || course.getStart() > course.getEnd() || course.getEnd() > 10) {
            if (mToast == null) {
                mToast = Toast.makeText(this, "课程信息有误，请重新输入", Toast.LENGTH_SHORT);
            } else {
                mToast.setText("课程信息有误，请重新输入");
                mToast.setDuration(Toast.LENGTH_SHORT);
            }
            mToast.show();
        } else {
            switch (getDay) {
                case 1:
                    day = (RelativeLayout) findViewById(R.id.monday);
                    break;
                case 2:
                    day = (RelativeLayout) findViewById(R.id.tuesday);
                    break;
                case 3:
                    day = (RelativeLayout) findViewById(R.id.wednesday);
                    break;
                case 4:
                    day = (RelativeLayout) findViewById(R.id.thursday);
                    break;
                case 5:
                    day = (RelativeLayout) findViewById(R.id.friday);
                    break;
                case 6:
                    day = (RelativeLayout) findViewById(R.id.saturday);
                    break;
                case 7:
                    day = (RelativeLayout) findViewById(R.id.weekday);
                    break;
            }
            final View v = LayoutInflater.from(this).inflate(R.layout.course_card, null); //加载单个课程布局
            v.setY(height * (course.getStart() - 1));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
                    (ViewGroup.LayoutParams.MATCH_PARENT, (course.getEnd() - course.getStart() + 1) * height - 8); //设置布局高度,即跨多少节课
            v.setLayoutParams(params);
            TextView text = (TextView) v.findViewById(R.id.text_view);
            text.setBackgroundResource(course_bj[(int) (Math.random() * 10)]);
            text.setText(course.getCourseName() + "\n\n" + course.getClassRoom()); //显示课程名
            day.addView(v);
            //长按修改
            v.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    v.setVisibility(View.GONE);//先隐藏
                    day.removeView(v);
                    Intent intent = new Intent(MainActivity.this, Alter_Course.class);
                    intent.putExtra("course", course);

                    startActivityForResult(intent,1);
                    return true;
                }
            });
            //单击显示详情
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(MainActivity.this, MessageCourseActivity.class);
                    intent.putExtra("course", course);
                    startActivityForResult(intent,2);
                }
            });


        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == 0 && data != null) {
            Course course = (Course) data.getSerializableExtra("course");
            //创建课程表视图
            createCourseView(course);

            //存储数据到数据库
            saveData(course);
        }
        if (requestCode == 1 && resultCode == 1 && data != null) {
            Course course = (Course) data.getSerializableExtra("course");
            //创建课程表视图
            createCourseView(course);
            //更新数据到数据库
            updataData(course);
        }
        if (requestCode == 1 && resultCode == 2 && data != null) {
            Course course = (Course) data.getSerializableExtra("course");


            delData(course);
        }

    }
    //单机空白区域，显示添加窗口
    public void test_click(View v){
        Intent intent5 = new Intent(MainActivity.this, AddCourseActivity.class);
        startActivityForResult(intent5, 0);
    }
    //主页面侧滑按钮
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }
    @SuppressLint("SimpleDateFormat")
    private void initDate() {
        Date currentTime = new Date();
        String[] weekDays = {"日", "一", "二", "三", "四", "五", "六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentTime);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)  w = 0;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日  ");
        String dateString = formatter.format(currentTime);
        dateTextView.setText(dateString + "星期" + weekDays[w]);
    }
}


