<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>课程管理程序</title>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
</head>
<body>

<h1 style="color: skyblue;font-size: 15px;text-align: center">欢迎来到课程管理系统！</h1><br>
<h2 style="text-align: center"><a href = "${pageContext.request.contextPath}/MainPage">单击刷新列表</a></h2>
<br>
<h3 style="text-align: center"><a href = "${pageContext.request.contextPath}/logout">退出</a></h3><br>
<table id = "courseList" border="1" style="margin: auto;">
    <tr>
        <td>       </td>
        <td>课程名称</td>
        <td>学时</td>
        <td>授课学院名称</td>
        <td colspan = "2" text-align="center">操作</td>
    </tr>
    <c:forEach items="${courseList1}" var="course">

        <tr>
            <td><img src="showPic/${course.spic}" style="width:50px; height:50px;"/></td>
            <td>${course.name}</td>
            <td>${course.hours}</td>
            <td>${course.schoolName}</td>
            <td><a href = '${pageContext.request.contextPath}/getCourse?action=ModifyCourse&id=${course.id}'>编辑</a></td>
            <td><a href = '${pageContext.request.contextPath}/Delete?id=${course.id}'>删除</a></td>
        </tr>

    </c:forEach>

</table>

<h4 style="text-align: center"><a href = "${pageContext.request.contextPath}/Addcourse">添加新课程</a></h4>

</body>
</html>
