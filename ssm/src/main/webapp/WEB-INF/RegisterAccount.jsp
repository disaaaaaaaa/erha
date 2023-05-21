<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>注册账号</title>
</head>
<body>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<h1 style="font-size: 15px;color: darkblue">注册账号</h1>
<form action="${pageContext.request.contextPath}/Register"
      method="post">

  请&nbsp;输&nbsp;入&nbsp;E-mail：<input type="text" name="email"/><br/>
  请&nbsp;输&nbsp;入&nbsp;密&nbsp;&nbsp;&nbsp;码：<input type="text" name="password"/><br/>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="注册"/>
</form>
</body>
</html>