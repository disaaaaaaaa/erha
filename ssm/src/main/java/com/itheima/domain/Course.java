package com.itheima.domain;

import java.util.List;

public class Course {
    private int id;
    private String name;
    private int hours;
    private int sid;
    private String schoolName;
    private String spic = "moren.jpg";
    private List<School> schoolsList;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getHours() {
        return hours;
    }

    public int getSid() {
        return sid;
    }

    public String getSchoolName(){return schoolName;}

    public List<School> getSchoolsList() {
        return schoolsList;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public void setSchoolsList(List<School> schoolsList) {
        this.schoolsList = schoolsList;
    }

    public String toString() {
        return "课程信息 [id：" + id + ", 名称：" + name + ", 学时：" + hours + ", 学院id：" + sid + "]\n";
    }

    public String getspic() {
        return spic;
    }

    public void setspic(String spic) {
        this.spic = spic;
    }
}




