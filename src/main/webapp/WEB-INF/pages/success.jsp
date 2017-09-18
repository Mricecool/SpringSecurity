<%--
  Created by IntelliJ IDEA.
  User: app
  Date: 2016/9/1
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>登陆成功</title>
</head>
<body>
<h2 align="center"><sec:authentication property="name"/>，欢迎您！</h2><br/>
<h2 align="center"><a href="/admin">管理员界面</a></h2>
<h2 align="center"><a href="/logout">注销</a></h2>
</body>
</html>
