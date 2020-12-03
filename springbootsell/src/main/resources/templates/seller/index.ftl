<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>卖家后台管理系统</title>
    <script src="/static/js/jq.js"></script>
    <script src="/static/js/bootstrap.min.js"></script>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <#--    <link rel="stylesheet" href="/sell/css/style.css">-->
    <link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h3 class="text-center">
                欢迎登陆卖家后台管理系统
            </h3>
            <form role="form" method="post" action="/seller/login">
                <div class="form-group">
                    <label for="exampleInputEmail1">账号</label>
                    <input name="Id"  type="text"  class="form-control" id="exampleInputEmail1" />
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">密码</label>
                    <input name="password" type="password" class="form-control" id="exampleInputPassword1" />
                </div>
                <button type="submit" class="btn btn-default">登陆</button>
                <a href="#">修改密码</a>
            </form>
        </div>
    </div>
</div>
</body>
</html>