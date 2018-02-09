<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>form</title>
    <link rel="stylesheet" href="static/css/bootstrap.min.css">
    <style type="text/css">
        body {
            font-family: 'Microsoft YaHei';
        }

        /*.panel-body{ padding: 0; }*/
    </style>
</head>
<body>
<div class="jumbotron">
    <div class="container">

        <h3>——生鲜管理系统</h3>

    </div>
</div>
<div class="container">
    <div class="main">
        <div class="row">
            <!-- 左侧内容 -->
            <div class="col-md-3">
                <div class="list-group">
                    <a href="${pageContext.request.contextPath}/category" class="list-group-item text-center ">生鲜列表</a>
                    <a href="${pageContext.request.contextPath}/layout-form.html"
                       class="list-group-item text-center active">新增生鲜</a>
                </div>
            </div>
            <!-- 右侧内容 -->
            <div class="col-md-9">
                <!-- 错误提示 -->
                <div style="display: none" class="alert alert-danger" role="alert">
                    <ul>
                        <li>姓名不能为空</li>
                        <li>年龄只能为整数</li>
                        <li>请选择性别</li>
                    </ul>
                </div>

                <!-- 自定义内容 -->
                <div class="panel panel-default">
                    <div class="panel-heading">新增生鲜种类</div>
                    <div class="panel-body">
                        <form action="addCategory" method="post" class="form-horizontal" role="form">
                            <div class="form-group">
                                <input type="hidden" name="method" val
                                       ue="addCategory">
                                <label class="col-sm-2 control-label">名称</label>
                                <div class="col-sm-5">
                                    <input type="text" name="c_name" class="form-control" placeholder="生鲜名称">
                                </div>
                                <div class="col-sm-5">
                                    <p class="form-control-static text-danger">名称不能为空</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">产地</label>
                                <div class="col-sm-5">
                                    <input type="text" name="place" class="form-control" placeholder="产地">
                                </div>
                                <div class="col-sm-5">
                                    <p class="form-control-static text-danger">产地不能为空</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">类别</label>
                                <div class="col-sm-5">
                                    <label class="radio-inline">
                                        <input type="radio" name="type" value="0">未知
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="type" value="1">猪牛羊肉
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="type" value="2">海鲜水产
                                    </label>
                                </div>
                                <div class="col-sm-5">
                                    <p class="form-control-static text-danger">请选择分类</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <button type="submit" class="btn btn-primary">提交</button>
                                </div>
                            </div>
                        </form>

                    </div>
                </div>


            </div>
        </div>
    </div>
</div>
<!-- 尾部 -->
<div class="jumbotron" style=" margin-bottom:0;margin-top:105px;">
    <div class="container">
        <span>&copy; 2016 Saitmob</span>
    </div>
</div>




</body>
</html>