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

            <form method="post" action="user" >
            <p>用户名：<input type="text" id="user" name="name"></p>
            <p>密码：<input type="text" id="passwd" name="password"></p>
            <p>确认密码：<input type="text" id="passwd2" name="password2"></p>
            <p>邮箱：<input type="text" id="email" name="email"></p>
                <input type="hidden" name="method" value="register">
                <p><input type="submit" value="同意协议并注册"><i>阅读协议</i></p>
        </form>

    </div>
</body>
</html>
