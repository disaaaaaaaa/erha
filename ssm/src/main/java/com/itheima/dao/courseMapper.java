package com.itheima.dao;
import com.itheima.domain.Course;

public interface courseMapper {

    public int updateCourseByID(Course course);
    public int addCourse(Course course);
    public int deleteCourse(Course course);
    public int getCourseCount();
    public int updateCourseId(Course course);
    public Course findCoursesById(int id);
}