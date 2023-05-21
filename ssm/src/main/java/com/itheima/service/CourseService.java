package com.itheima.service;

import com.itheima.domain.Course;
import com.itheima.util.WebActions;
import org.springframework.web.multipart.MultipartFile;

public interface CourseService {
    public int addCourse(Course course);
    public int updateCourseByID(Course course);
    public int deleteCourse(Course course);
    public int getCourseCount();
    public Course findCoursesById(int id);
    public int updateCourseId(Course course);
    public boolean saveFileName(Course course, MultipartFile file, WebActions actions);
}
