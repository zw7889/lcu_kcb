package com.example.lcu_kcb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Serializable;


public class Alter_Course extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alter_course);
        setFinishOnTouchOutside(false);
        final EditText inputCourseName = (EditText) findViewById(R.id.course_name);
        final EditText inputTeacher = (EditText) findViewById(R.id.teacher_name);
        final EditText inputClassRoom = (EditText) findViewById(R.id.class_room);
        final EditText inputDay = (EditText) findViewById(R.id.day);
        final EditText inputBeginTime = (EditText) findViewById(R.id.classes_begin);
        final EditText inputEndTime = (EditText) findViewById(R.id.classes_ends);
        final EditText inputTypeWeek = (EditText) findViewById(R.id.type_week);
        Intent intent = getIntent();
        final Course course = (Course) intent.getSerializableExtra("course");
        inputCourseName.setText(course.getCourseName());//课程名
        inputTeacher.setText(course.getTeacher());//老师
        inputClassRoom.setText(course.getClassRoom());//教室
        inputTypeWeek.setText(course.getWeek());//单双周
        inputDay.setText(String.valueOf(course.getDay()));
        inputBeginTime.setText(String.valueOf(course.getStart()));
        inputEndTime.setText(String.valueOf(course.getEnd()));
        //返回按钮
        Button backButton = (Button) findViewById(R.id.back_add);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String courseName = inputCourseName.getText().toString();
                String teacher = inputTeacher.getText().toString();
                String classRoom = inputClassRoom.getText().toString();
                String day = inputDay.getText().toString();
                String start = inputBeginTime.getText().toString();
                String end = inputEndTime.getText().toString();
                String week = inputTypeWeek.getText().toString();
                int id=course.getId();
                if (courseName.equals("") || day.equals("") || start.equals("") || end.equals("")) {
                    Toast.makeText(Alter_Course.this, "基本课程信息未填写", Toast.LENGTH_SHORT).show();
                }
                else {
                    Course course = new Course(Integer.valueOf(id),courseName, teacher, classRoom,
                            Integer.valueOf(day), Integer.valueOf(start), Integer.valueOf(end),
                            week);
                    Intent intent = new Intent(Alter_Course.this, MainActivity.class);
                    intent.putExtra("course", course);

                    setResult(1, intent);
                    finish();
                }
            }
        });

        Button okButton = (Button) findViewById(R.id.button);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String courseName = inputCourseName.getText().toString();
                String teacher = inputTeacher.getText().toString();
                String classRoom = inputClassRoom.getText().toString();
                String day = inputDay.getText().toString();
                String start = inputBeginTime.getText().toString();
                String end = inputEndTime.getText().toString();
                String week = inputTypeWeek.getText().toString();
                int id=course.getId();
                if (courseName.equals("") || day.equals("") || start.equals("") || end.equals("")) {
                    Toast.makeText(Alter_Course.this, "基本课程信息未填写", Toast.LENGTH_SHORT).show();
                }
                else {
                    Course course = new Course(Integer.valueOf(id),courseName, teacher, classRoom,
                            Integer.valueOf(day), Integer.valueOf(start), Integer.valueOf(end),
                            week);
                    Intent intent = new Intent(Alter_Course.this, MainActivity.class);
                    intent.putExtra("course", course);

                    setResult(1, intent);
                    finish();
                }
            }
        });
        Button delButton = (Button) findViewById(R.id.delbutton);
        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String courseName = inputCourseName.getText().toString();
                String teacher = inputTeacher.getText().toString();
                String classRoom = inputClassRoom.getText().toString();
                String day = inputDay.getText().toString();
                String start = inputBeginTime.getText().toString();
                String end = inputEndTime.getText().toString();
                String week = inputTypeWeek.getText().toString();
                int id=course.getId();
                if (courseName.equals("") || day.equals("") || start.equals("") || end.equals("")) {
                    Toast.makeText(Alter_Course.this, "基本课程信息未填写", Toast.LENGTH_SHORT).show();
                }
                else {
                    Course course = new Course(Integer.valueOf(id),courseName, teacher, classRoom,
                            Integer.valueOf(day), Integer.valueOf(start), Integer.valueOf(end),
                            week);
                    Intent intent = new Intent(Alter_Course.this, MainActivity.class);
                    intent.putExtra("course",course);

                    setResult(2, intent);
                    finish();
                }
            }
        });
    }

//    //将数字转化为文字显示
//    static public String getDayString(int day) {
//        String dayString;
//        switch (day){
//            case 1:
//                dayString = "一";
//                break;
//            case 2:
//                dayString = "二";
//                break;
//            case 3:
//                dayString = "三";
//                break;
//            case 4:
//                dayString = "四";
//                break;
//            case 5:
//                dayString = "五";
//                break;
//            case 6:
//                dayString= "六";
//                break;
//            case 7:
//                dayString= "日";
//                break;
//            default:
//                dayString= "";
//                break;
//        }
//        return dayString;
//    }
}
