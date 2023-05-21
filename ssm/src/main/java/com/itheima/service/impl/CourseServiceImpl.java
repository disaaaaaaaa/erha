package com.itheima.service.impl;
import com.itheima.dao.courseMapper;
import com.itheima.domain.Course;
import com.itheima.service.CourseService;
import com.itheima.util.Constants;
import com.itheima.util.WebActions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private courseMapper courseMapper1;


    @Override
    public int addCourse(Course course) {
        return courseMapper1.addCourse(course);
    }

    @Override
    public int updateCourseByID(Course course) {
        return courseMapper1.updateCourseByID(course);
    }

    @Override
    public int updateCourseId(Course course){
        return courseMapper1.updateCourseId(course);
    }
    @Override
    public int deleteCourse(Course course) {
        return courseMapper1.deleteCourse(course);
    }

    @Override
    public int getCourseCount(){
        return courseMapper1.getCourseCount();
    }

    @Override
    public Course findCoursesById(int id){
        return courseMapper1.findCoursesById(id);
    }

    @Override
    public boolean saveFileName(Course course, MultipartFile file, WebActions action){
        if (file != null){
            // 原始文件名
            String originalFileName = file.getOriginalFilename();
            // 获取图片后缀
            String suffix = originalFileName.substring(originalFileName.lastIndexOf("."));
            // 生成图片存储的名称，UUID 避免相同图片名冲突，并加上图片后缀
            String fileName = UUID.randomUUID().toString() + suffix;
            // 图片存储路径
            String filePath = Constants.IMG_PATH + fileName;
            File saveFile = new File(filePath);
            try {
                // 将上传的文件保存到服务器文件系统
                file.transferTo(saveFile);
                // 记录服务器文件系统图片名称
                course.setspic(fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 持久化 course
        switch (action){
            case Adding:
                System.out.println("添加的课程信息是" + course.toString());
                return courseMapper1.addCourse(course) > 0;
            case Modifying:
                return courseMapper1.updateCourseByID(course) > 0;
            default:
                return false;
        }
    }



}
