package com.itheima.controller;


import com.itheima.domain.Account;
import com.itheima.domain.Course;
import com.itheima.domain.School;
import com.itheima.service.AccountService;
import com.itheima.service.CourseService;
import com.itheima.service.SchoolService;
import com.itheima.util.Constants.*;
import com.itheima.util.WebActions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.itheima.util.Constants.IMG_PATH;


@Controller
public class ExperimentFourController {
    @Autowired
    private SchoolService schoolService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private AccountService accountService;
    List<String> courseNameList;

    //跳转到登录页面
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    //登出操作
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:toLogin";
    }

    //判断是不是Email
    public static boolean isEmail(String email) {
        Pattern emailPattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        Matcher matcher = emailPattern.matcher(email);
        if(matcher.find()){
            return true;
        }
        return false;
    }

    //登录操作
    @RequestMapping("/Login")
    public String login(Account account, Model model, HttpSession session){
        String email = account.getEmail();
        String password = account.getPassword();
        System.out.println("E-mail：" + email + "，密码：" + password);
        Account account1 = accountService.findAccountByEmail(email);
        System.out.println("发现的账号为：" + account1.getEmail() + "，它的密码是"+account1.getPassword());
        if(account1.getEmail().equals(account.getEmail()) && account1.getPassword().equals(account.getPassword())
                && email != null && password != null){
            session.setAttribute("USER_SESSION", account);
            return "redirect:MainPage";
        }
        model.addAttribute("msg", "用户名或密码错误，请重试！");
        return "login";
    }
    //跳转到注册页面
    @RequestMapping("/toRegister")
    public String toRegister(){
        return "RegisterAccount";
    }

    //注册用户的操作
    @RequestMapping(value = "/Register", method = RequestMethod.POST)
    public String register(Account account){
        try{
            System.out.println("要添加的账号是：" + account.toString());
            if(isEmail(account.getEmail()) == true){
                Integer i = accountService.addAccount(account);
                if(i == 1)
                    System.out.println("操作成功！");
                return "Success";
            }
        }catch (Exception e){
            System.out.println("发现异常！");
            e.printStackTrace();
        }
        System.out.println("操作失败！");
        return "Failure";
    }

    //登陆以后
    //功能一：展示全体课程，并按学院类别进行排序
    @RequestMapping("/MainPage")
    public ModelAndView Hello(){
        courseNameList = new ArrayList<>();
        List<School> schoolList = schoolService.findAllSchool();
        List<Course> courseList1 = new ArrayList<>();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("MainPage");
        for(School a:schoolList){
            List<Course> courseList = a.getCourseList();
            for (Course b:courseList){
                b.setSchoolName(a.getSchoolname());
                courseList1.add(b);
                courseNameList.add(b.getName());
            }
        }
        modelAndView.addObject("courseList1", courseList1);
        return modelAndView;
    }

    //展示图片
    @RequestMapping("/showPic/{fileName}.{suffix}")
    public void showPicture(@PathVariable("fileName") String fileName,
                            @PathVariable("suffix") String suffix,
                            HttpServletResponse response){
        File imgFile = new File(IMG_PATH + fileName + "." + suffix);
        responseFile(response, imgFile);
    }

    //功能二：添加新课程
    @RequestMapping("/Addcourse")
    public ModelAndView Addcourse(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Addcourse");
        modelAndView.addObject("schoolList", schoolService.findAllSchool());
        return modelAndView;
    }

    @RequestMapping(value = "/setNewCourse", method = RequestMethod.POST)
    public String setNewCourse(Course course, @RequestParam("imgFile") MultipartFile file) {
        int i = courseService.getCourseCount();
        course.setId(i + 1);
        System.out.print("要添加的数据是：" + course.toString());
        if(courseNameList.contains(course.getName()) == true){
            System.out.println("课程名字出现重复啦！");
            return "Failure";
        }try{
            if(file != null &&!file.isEmpty()){
                if(courseService.saveFileName(course, file, WebActions.valueOf("Adding"))){
                    System.out.println("已添加文件！");
                    return "Success";
                }
            }i = courseService.addCourse(course);
            System.out.println("执行添加操作成功了吗？" + i);
            if(i != 0)
                System.out.println("操作成功！");
            return "Success";

        }catch (Exception e){
            System.out.println("发现异常！");
            e.printStackTrace();
        }
        System.out.println("数据处理失败！");
        return "Failure";
    }

    //功能三：编辑课程
    @RequestMapping("/getCourse")
    public ModelAndView getCourse(String action, Integer id){
        Course course = courseService.findCoursesById(id);
        System.out.print("要修改的课程数据是："+course);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(action);
        modelAndView.addObject("course", course);
        modelAndView.addObject("schoolList", schoolService.findAllSchool());
        return modelAndView;
    }

    @RequestMapping(value = "/modifyCourse", method = RequestMethod.POST)
    public String modifyCourse(Course course, @RequestParam("imgFile") MultipartFile file) {
        try{
            if(file != null && !file.isEmpty()){
                if(courseService.saveFileName(course, file, WebActions.valueOf("Modifying"))){
                    System.out.println("已修改文件！");
                }
            }else{
                System.out.println("文件未修改！");
                course.setspic(courseService.findCoursesById(course.getId()).getspic());
            }
            System.out.print("数据要修改为：" + course.toString());


            if(courseService.updateCourseByID(course) == 1)
                System.out.println("操作成功！");
            return "Success";


        }catch (Exception e){
            System.out.println("发现异常！");
            e.printStackTrace();
        }
        System.out.println("数据处理失败！");
        return "Failure";
    }

    //功能四：删除课程
    @RequestMapping("/Delete")
    public ModelAndView Delete(Integer id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Delete");
        modelAndView.addObject("id",id);
        return modelAndView;
    }

    @RequestMapping("/deleteCourse")
    public String deleteCourse(Integer id){
        Course course = courseService.findCoursesById(id);
        System.out.print("要删除的课程数据是："+course);
        try{
            int i = courseService.deleteCourse(course);
            if(i == 1)
                System.out.println("操作成功！");
            if(id > courseService.getCourseCount());
            else {
                System.out.println("这之后的课程被修改为：");
                for (i = id + 1; i <= courseService.getCourseCount() + 1; i++){
                    course = courseService.findCoursesById(i);
                    System.out.print("修改前的"+course);
                    course.setId(i - 1);
                    System.out.print("修改后的"+course);
                    int j = courseService.updateCourseId(course);
                    if(j == 1)
                        System.out.println("修改操作成功！");
                }
            }
            System.out.println("还剩下"+ courseService.getCourseCount()+"门课程。");
            return "Success";
        }catch (Exception e){
            System.out.println("发现异常！");
            e.printStackTrace();
        }
        System.out.println("数据处理失败！");
        return "Failure";
    }

    private void responseFile(HttpServletResponse response, File imgFile) {
        try(InputStream is = new FileInputStream(imgFile);
            OutputStream os = response.getOutputStream();){
            byte [] buffer = new byte[1024]; // 图片文件流缓存池
            while(is.read(buffer) != -1){
                os.write(buffer);
            }
            os.flush();
        } catch (Exception ioe){
            ioe.printStackTrace();
        }
    }
}
