<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
    <meta name = "format-detection" content = "telephone=no">
    <link href="/air_quality/css/style.css" rel="stylesheet" type="text/css">
    <title>我的家</title>
</head>
<body>
<header class="hbg clearfix text-center">
    <a class="goleft fl" href="/mp/airQuality/home"><i class="iconfont icon-goleft"></i></a>
    <span class="title">家庭管理</span>

    <a onclick="showPop();" class="addbtn" style="font-size:  12px;line-height: 22px; ">添加家庭</a>
</header>
<div class="middle">
    <div class="coup-list">
        <ul>
            <li class="clearfix">
                <a href="javascript:;" class="edit">编辑</a>
                <a href="javascript:;" class="edit-over">完成</a>
                <h1>我的家庭</h1>
            </li>
            <#list homeList as home>
            <li class="" onclick="editHome(${home.id}, '${home.name}');">
                <a class="delebtn" href="javascript:;"><i class="iconfont icon-lajitong" onclick="deleteHome(${home.id})"></i> </a>
                <a href="javascript:;" class="home-c item">
                    <p>${home.name}</p>
                    <p class="info"><span>个房间</span>|<span>个设备</span></p>
                </a>

            </li>
            </#list>
        </ul>
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

<div onclick="closePop();"  class="mask"></div>
<div  class=" pop-add">
    <div class="pop-con">
        <div class="title">请输入家庭名称</div>
        <form id="new_home_form" action="/mp/home/new" method="post">
            <div class="ipt-form">
                <input class="ipt-c" type="text" id="new_home_name" name="name" placeholder="">
            </div>
            <div class="btn-g">
                <a href="javascript:;" class="btn-un" onclick="closePop();">取消</a>
                <a href="javascript:;" class="btn-submit" id="new_home_btn">确定</a>
            </div>
        </form>
    </div>
</div>
<script src="/air_quality/js/jquery.min.js"></script>

<!-- Initialize Swiper -->
<script>
    $(function(){
        $(".edit").on("click",function(){
            $(this).hide();
            $(".delebtn").show();
            $(".edit-over").show();
            $(".coup-list .item").removeClass("home-c");

        });
        $(".edit-over").on("click",function(){
            $(this).hide();
            $(".delebtn").hide();
            $(".edit").show();
            $(".coup-list .item").addClass("home-c");

        });

        $("#new_home_btn").click(function(){
            $("#new_home_form").submit();
        });

        $("#edit_home_btn").click(function(){
            $("#edit_home_form").submit();
        });

    });

    var closePop = function(){
        $(".mask,.pop-add").hide();
    }
    var showPop = function(){
        $("#new_home_name").val("");
        $("#new_home_form").attr("action", "/mp/home/new");
        $(".mask,.pop-add").show();
    }

    function deleteHome(id){
        var r = confirm("删除后不可恢复，确认要删除？");
        if(r){
            window.location.href = "/mp/home/"+id+"/delete";
        }
    }

    function editHome(id, name) {
        $("#new_home_name").val(name);
        $("#new_home_form").attr("action", "/mp/home/"+id+"/edit");
        $(".mask,.pop-add").show();
    }

</script>

</body>
</html>