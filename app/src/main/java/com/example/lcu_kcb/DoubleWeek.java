package com.example.lcu_kcb;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.j256.ormlite.dao.Dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DoubleWeek extends AppCompatActivity {
    //星期几
    private RelativeLayout day;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.double_week);
        createWeekLeftView();
        loadWeekData();
        Button backButton = (Button) findViewById(R.id.back_double);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //从数据库加载数据
    private void loadWeekData() {

        DbHelper helper = new DbHelper(this);
        try {
            Dao<Course,Integer> sdao = helper.getDao(Course.class);
            List<Course> sl = sdao.queryForAll();
            //使用从数据库读取出来的课程信息来加载课程表视图
            for (Course course : sl) {
                if(course.getWeek().equals("单周")==false)
                {createWeekCourseView(course);} }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //创建课程节数视图
    private void createWeekLeftView() {
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
    private void createWeekCourseView(final Course course_Week) {
        final int[] course_bj = {R.drawable.coursetable1, R.drawable.coursetable2,
                R.drawable.coursetable3, R.drawable.coursetable4, R.drawable.coursetable5,
                R.drawable.coursetable6, R.drawable.coursetable7, R.drawable.coursetable8,
                R.drawable.coursetable9, R.drawable.coursetable10};
        int height = 180;
        int getDay = course_Week.getDay();
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
            v.setY(height * (course_Week.getStart() - 1)); //设置开始高度,即第几节课开始
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
                    (ViewGroup.LayoutParams.MATCH_PARENT, (course_Week.getEnd() - course_Week.getStart() + 1) * height - 8); //设置布局高度,即跨多少节课
            v.setLayoutParams(params);
            TextView text = (TextView) v.findViewById(R.id.text_view);
            text.setBackgroundResource(course_bj[(int) (Math.random() * 10)]);
            text.setText(course_Week.getCourseName() + "\n\n" + course_Week.getClassRoom()); //显示课程名
            day.addView(v);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DoubleWeek.this, MessageCourseActivity.class);
                    intent.putExtra("course", (Serializable) course_Week);
                    startActivity(intent);
                }
            });
    }
}
