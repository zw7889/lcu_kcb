package com.example.lcu_kcb;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.List;


@DatabaseTable(tableName = "Course")
public class Course implements Serializable {
    @DatabaseField
    private String courseName;
    @DatabaseField
    private String teacher;
    @DatabaseField
    private String classRoom;
    @DatabaseField
    private int day;
    @DatabaseField(generatedId  = true)
    private int id;
    @DatabaseField
    private int classStart;

    @DatabaseField
    private int classEnd;
    @DatabaseField
    private String week;

    public Course(){ }



    public Course(int id, String courseName, String teacher, String classRoom, int day, int classStart, int classEnd, String week) {
        this.id = id;
        this.courseName = courseName;
        this.teacher = teacher;
        this.classRoom = classRoom;
        this.day = day;
        this.classStart = classStart;
        this.classEnd = classEnd;
        this.week=week;
    }

    public Course(String courseName, String teacher, String classRoom, int day, int classStart, int classEnd, String week) {
        this.courseName = courseName;
        this.teacher = teacher;
        this.classRoom = classRoom;
        this.day = day;
        this.classStart = classStart;
        this.classEnd = classEnd;
        this.week=week;
    }


    public int getId() { return id; }

    public void setId(int id) { this.id = id; }


    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getStart() {
        return classStart;
    }

    public void setStart(int classStart) {
        this.classEnd = classStart;
    }

    public int getEnd() {
        return classEnd;
    }

    public void setEnd(int classEnd) {
        this.classEnd = classEnd;
    }
    public void setWeek(String week) {
        this.week = week;
    }

    public String getWeek() {
        return week;
    }

}
