<html>
<head>
    <meta charset="utf-8">
    <title>取消成功</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="alert alert-dismissable alert-success">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h4>
                    取消成功!
                </h4> <strong>${msg!""}</strong><a href="${url}" class="alert-link"><span id="Time">3</span>秒后自动跳转</a>
            </div>
        </div>
    </div>
</div>

</body>

<script>
    function countDown(){

        var time = document.getElementById("Time");
        if(time.innerHTML == 0){
            window.location.href="${url}";
        }else{
            time.innerHTML = time.innerHTML-1;
        }
    }
    //1000毫秒调用一次
    window.setInterval("countDown()",1000);
</script>

</html>