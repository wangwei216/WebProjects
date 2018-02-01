<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018-02-02
  Time: 0:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册界面</title>
    <style>
        div{height: 600px; width: 500px; border: aquamarine;}
    </style>
</head>
<body>

    <div align="center">

            <form method="get" action="register.jsp">
            <p>账号：<input type="text"></p>
            <p>密码：<input type="text"></p>
            <p>确认密码：<input type="text"></p>
            <p>邮箱：<input type="text"></p>
            <p><input type="submit" value="同意协议并注册"><i>阅读协议</i></p>
        </form>

    </div>
</body>
</html>
