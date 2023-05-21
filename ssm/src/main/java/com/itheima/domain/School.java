package com.itheima.domain;

import java.util.List;

public class School {
    private int id;
    private String schoolname;
    private List<Course> courseList;

    public int getId() {
        return id;
    }

    public String getSchoolname() {
        return schoolname;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    @Override
    public String toString() {

        if(courseList != null && courseList.size() != 0)
            return "学院{" + "id：" + id + ", 学院名称：" + schoolname + ", 授课课程：" + courseList + "}\n";
        else
            return "学院{" + "id：" + id + ", 学院名称：" + schoolname + ", 授课课程：无}\n";
    }
}
