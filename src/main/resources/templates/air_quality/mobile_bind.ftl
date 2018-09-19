<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
    <meta name = "format-detection" content = "telephone=no">
    <link href="css/style.css" rel="stylesheet" type="text/css">
    <title>绑定手机</title>
</head>
<body>
<header class="hbg clearfix text-center">
    <a class="goleft fl" href="javascript:;"><i class="iconfont icon-goleft"></i></a>
    <span class="title">绑定手机号码</span>
</header>
<div class="middle con-page">
    <div class="form-container">
        <div class="line clearfix">
            <div class="title fl">手机号：</div>
            <div class="con fl">
                <div class="ipt">
                    <input class="ipt-txt" type="text" placeholder="请输入手机号码">
                </div>

            </div>
        </div>
        <div class="line clearfix">
            <div class="title fl">验证码：</div>
            <div class="con fl">
                <div class="ipt w01" style="" >
                    <input class="ipt-txt" type="text" placeholder="6位数验证码">
                </div>
                <a class="yanz send" href="javascript:;">获取验证码</a>

            </div>
        </div>
        <div class="line">
            <a class="com-btn" href="javascript:;">
                确认
            </a>
        </div>
    </div>



</div>
<!--<footer class="clearfix footer">
    <ul class="fl tabs">
        <li class="active"><a href="javascritp:;"><i class="iconfont  icon-keting-xianxing"></i>
        <span>客厅</span></a> </li>
        <li><a href="javascritp:;"><i class="iconfont icon-woshi1"></i> <span>主卧</span></a> </li>
        <li><a href="javascritp:;"><i class="iconfont icon-woshi"></i> <span>次卧</span></a> </li>
        <li><a href="javascritp:;"><i class="iconfont icon-shugui"></i> <span>书房</span></a> </li>
    </ul>
    <div class="fr icon-set">
        <a href="javascript:;"><i class="iconfont icon-shezhi"></i></a>
    </div>
</footer>-->

<div onclick="closePop();"  style="display: block" class="mask"></div>
<div  class=" pop-add" style="display: block;">
    <div class="pop-con">
        <div class="title">您输入的验证码不正确</div>
    </div>
</div>

<script src="js/jquery.min.js"></script>

<!-- Initialize Swiper -->
<script>




    $(function(){



        // 重发验证码函数
        var handle = function(selecter, fun, time) {
            --time;
            selecter.html(time + '秒后重发');
            if(time > 0) {
                setTimeout(function() {
                    handle(selecter, fun, time)
                }, 1000);
            } else {
                fun();
            }
        }
        var $sendVerificationCode = $(".send");

        $sendVerificationCode.click(function(e) {
            e.stopPropagation();
            if(!$sendVerificationCode.hasClass('disable')) {
                handle($sendVerificationCode, function() {
                    $sendVerificationCode.removeClass('disable');
                    $sendVerificationCode.html('发送验证码');
                }, 60);
                $sendVerificationCode.addClass('disable');
            }
        });





    });

    var closePop = function(){
        $(".mask,.pop-add").hide();
    }
    var showPop = function(){
        $(".mask,.pop-add").show();
    }



</script>

</body>
</html>