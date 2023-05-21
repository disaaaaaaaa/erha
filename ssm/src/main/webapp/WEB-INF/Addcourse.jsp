<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>添加新课程</title>
</head>
<body>
<h1 style="color: darkblue;font-size: 15px;">要添加的课程:</h1><br>
<form action="${pageContext.request.contextPath}/setNewCourse"
      method="post" enctype="multipart/form-data">

    课程名称：<input type="text" name="name"/><br/>
    课程学时：<input type="text" name="hours"/><br/>

    开课学院：
    <select id="sidList" name="sid" >
        <option value="NONE">--- 请选择 ---</option>

        <c:forEach items="${schoolList}" var="school">
            <option value=${school.id}>${school.schoolname}</option>
        </c:forEach>

    </select><br/>
    <br/>
    请设置课程封面：<input type="file" name="imgFile" />
    <br/>
    <input type="submit" value="提交"/>
</form>
</body>
</html>
