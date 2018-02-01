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
<%--action后面的值是吧数据提交到的一个jsp登录界面--%>
    <form method="get" action="login.jsp">
      <p>账号：<input type="text" ></p>
        <p>密码：<input type="text"></p>
        <input type="submit" value="登录">
    </form>
<div class="login">

</div>

</body>
</html>
