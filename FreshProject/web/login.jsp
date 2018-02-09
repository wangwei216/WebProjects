<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018-02-01
  Time: 23:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>这是登录界面</title>
</head>
<body>
<h1 align="center">生鲜管理系统<i><sup>2018</sup></i></h1>

<div class="login">
    <%--action后面的值是吧数据提交到的一个jsp登录界面--%>
    <form method="post" action="user">
        <%--z这个是从本地得到用户名和密码的cookie的值--%>
        <p>账号：<input type="text" id="id" name="name" value="${cookie.name.value}" ></p>
        <p>密码：<input type="password" id="p" name="password" value="${cookie.password.value}"></p>
            <input type="hidden" name="method" value="login">
        <input type="submit" value="登录">
       <p> <font size="2">记住密码：</font><input type="checkbox" name="remeber" value="yes"></p>
    </form>
</div>

</body>
</html>
