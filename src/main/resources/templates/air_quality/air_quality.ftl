<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
    <meta name = "format-detection" content = "telephone=no">
    <link href="/air_quality/swiper/css/swiper.min.css" rel="stylesheet" type="text/css">
    <link href="/air_quality/css/style.css" rel="stylesheet" type="text/css">
    <title>首页</title>
</head>
<body class="bg" style="padding-bottom: 53px;">
<header class="clearfix">
    <div class="myhome">
        <a class="homebtn" href="javascript:;">${room.home.name}<i class="iconfont icon-jiantou"></i> </a>
        <ul class="downmenu">
            <#list homeList as h>
                <li onclick="location.href='/mp/airQuality/home/${h.id}'">
                    <i class="iconfont icon-jiatingdizhi"></i> ${h.name}
                </li>
            </#list>
            <li onclick="location.href='/mp/home/view'">
                <i class="iconfont icon-shezhi"></i> 家庭管理
            </li>
        </ul>
    </div>

    <a onclick="showPop();" class="addbtn"><i class="iconfont icon-shebei"></i></a>
</header>
<div class="middle">
    <div class="weather">
        <div class="clearfix">
            <div class="fl du">
                <span id="live_temperature"></span>&nbsp;<i>℃</i>
            </div>
            <div class="fl info">
                <p id="live_city"></p>
                <p id="live_weather"></p>
            </div>
        </div>
        <div class="note">
            AQI指数<span id="live_aqi">0</span>|&nbsp;&nbsp;PM2.5<span id="live_pm25">0</span>
        </div>

    </div>

    <!-- Swiper -->
    <div class="swiper-container">
        <div class="swiper-wrapper" id="air_quality_zone">
            <#list roomList as r>
                <#if (r.deviceList?size>0)>
                <#assign d=r.deviceList[0]/>
                <div class="swiper-slide" device_mac="${d.mac}">
                    <div class="house">
                        <h1>风志清空气净化器</h1>
                        <p class="info">设备在线</p>
                        <div class="num">
                            <div class="num-con">
                                <p class="info">PM2.5 参考值</p>
                                <div class="counter-value shu">0</div>
                                <h1 class="msg">室内空气 优</h1>
                                <p class="tip">滤芯还剩85%</p>
                            </div>
                        </div>
                        <div class="info-list">
                            <div class="line">
                                <div class="col-6">
                                    <span><span class="counter-value" name="pm25">0</span> <i>μg/m³</i></span>
                                    <p><span>PM2.5</span><i class="dot red"></i></p>
                                </div>
                                <div class="col-6">
                                    <span><span class="counter-value" name="CH2O">0</span><i>mg/m³</i></span>
                                    <p><span>甲醛</span><i class="dot org"></i></p>
                                </div>
                            </div>
                            <div class="line">
                                <div class="col-4">
                                    <span><span class="counter-value" name="HUM">0</span><i>%</i></span>
                                    <p><span>湿度</span></p>
                                </div>
                                <div class="col-4">
                                    <span><span class="counter-value" name="TEM">0</span><i>℃</i></span>
                                    <p><span>温度</span></p>
                                </div>
                                <div class="col-4">
                                    <span><span class="counter-value" name="TVOC">0</span> <i>mg/m³</i></span>
                                    <p><span>TVOC</span></p>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
                <#else>
                <div class="swiper-slide">
                    <div class="house">
                        <h1>未绑定设备</h1>
                        <p class="info"></p>
                        <div class="num">
                            <div class="num-con">
                                <p class="info">PM2.5 参考值</p>
                                <div class="counter-value shu">0</div>
                                <h1 class="msg">室内空气 未知</h1>
                                <p class="tip">滤芯还剩0%</p>
                            </div>
                        </div>
                        <div class="info-list">
                            <div class="line">
                                <div class="col-6">
                                    <span><span class="counter-value">0</span> <i>μg/m³</i></span>
                                    <p><span>PM2.5</span><i class="dot red"></i></p>
                                </div>
                                <div class="col-6">
                                    <span><span class="counter-value">0</span><i>mg/m³</i></span>
                                    <p><span>甲醛</span><i class="dot org"></i></p>
                                </div>
                            </div>
                            <div class="line">
                                <div class="col-4">
                                    <span><span class="counter-value">0</span><i>%</i></span>
                                    <p><span>湿度</span></p>
                                </div>
                                <div class="col-4">
                                    <span><span class="counter-value">0</span><i>℃</i></span>
                                    <p><span>温度</span></p>
                                </div>
                                <div class="col-4">
                                    <span><span class="counter-value">0</span> <i>mg/m³</i></span>
                                    <p><span>CO</span></p>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
                </#if>
            </#list>
        </div>
    </div>


</div>
<footer class="clearfix footer">
    <ul class="fl tabs">
        <#list roomList as r>
        <li <#if r.id==room.id>class="active"</#if>><a href="javascript:;"><i class="iconfont icon-woshi1"></i> <span>${r.name}</span></a> </li>
        </#list>
    </ul>
    <div class="fr icon-set">
        <a href="/mp/home/${room.home.id}/rooms"><i class="iconfont icon-shezhi"></i></a>
    </div>
</footer>

<div onclick="closePop();"  class="mask"></div>
<div  class=" pop-add">
    <div class="pop-con">
        <div class="title">请输入新建家庭的名称</div>
        <div class="ipt-form">
            <input class="ipt-c" type="text" placeholder="">
        </div>
        <div class="btn-g">
            <a href="javascript:;" class="btn-un">取消</a>
            <a href="javascript:;" class="btn-submit">确定</a>
        </div>
    </div>
</div>
<!-- Swiper JS -->
<script src="/air_quality/swiper/js/swiper.min.js"></script>
<script src="/air_quality/js/jquery.min.js"></script>
<script src="/air_quality/gizwits/gizwits_ws_0.3.0.min.js"></script>
<!--高德地图Web端 JSAPI-->
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.4.6&key=a67d5e6cfe58c0b44a7b352972d8e5c1&plugin=AMap.Geolocation,AMap.Geocoder,AMap.Weather"></script>
<script type="text/javascript" >
    var weather = new AMap.Weather();
    var geolocation = new AMap.Geolocation({
        // 是否使用高精度定位，默认：true
        enableHighAccuracy: true,
        // 设置定位超时时间，默认：无穷大
        timeout: 10000,
        // 定位按钮的停靠位置的偏移量，默认：Pixel(10, 20)
        buttonOffset: new AMap.Pixel(10, 20),
        //  定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
        zoomToAccuracy: true,
        //  定位按钮的排放位置,  RB表示右下
        buttonPosition: 'RB'
    });

    geolocation.getCurrentPosition()
    AMap.event.addListener(geolocation, 'complete', onComplete)
    AMap.event.addListener(geolocation, 'error', onError)

    function onComplete (data) {
        // data是具体的定位信息
        var str=['定位成功'];
        str.push('经度：' + data.position.getLng());
        str.push('纬度：' + data.position.getLat());
        if(data.accuracy){
            str.push('精度：' + data.accuracy + ' 米');
        }//如为IP精确定位结果则没有精度信息
        str.push('是否经过偏移：' + (data.isConverted ? '是' : '否'));
        console.log(str);

        //逆向地理编码（坐标-地址）
        lnglatXY = [data.position.getLng(), data.position.getLat()]; //已知点坐标
        var geocoder = new AMap.Geocoder();
        geocoder.getAddress(lnglatXY, function(status, result) {
            if (status === 'complete' && result.info === 'OK') {
                var address = result.regeocode.formattedAddress; //返回地址描述
                console.log(address);
                var city = result.regeocode.addressComponent.city;  //TODO: 直辖市获取不到city信息
                console.log(city);
                //执行实时天气信息查询
                weather.getLive(city, function(err, data) {
                    console.log(err, data);
                    if (!err) {
                        $("#live_temperature").text(data.temperature);
                        $("#live_city").text(city);
                        $("#live_weather").text(data.weather);
                        //墨迹天气AQI
                        $.ajax({
                            url : "/mp/liveAQI/" + city,
                            type : "POST",
                            dataType: 'json',
                            contentType:'application/json;charset=UTF-8',
                            success : function(result) {
                                // console.log(result);
                                var aqiObj = JSON.parse(JSON.stringify(result));
                                $("#live_aqi").text(aqiObj.value);
                                $("#live_pm25").text(aqiObj.pm25);
                            }
                        });
                    }
                });
            }
        });
    }

    function onError (data) {
        // 定位出错
        console.log("定位失败！");
    }
</script>
<script type="text/javascript">
    var gizwitsws;

    if (gizwitsws != null) {
        console.log("对象已被初始化，如需改变参数，请刷新页面.");
    }else{
        var apiHost = "api.gizwits.com";
        var commType = "attrs_v4";
        // var wechatOpenId = "o4s0t1W9-sZ8cN3UHATL5OjZwLdo";
        var wechatOpenId = "${wechatOpenId!}";
        var gizwitsAppId = "${gizwitsAppId!}";
        gizwitsws = new GizwitsWS(apiHost, wechatOpenId, gizwitsAppId, commType);

        gizwitsws.onInit = onInit;
        gizwitsws.onConnected = onConnected;
        gizwitsws.onOnlineStatusChanged = onOnlineStatusChanged;
        gizwitsws.onReceivedRaw = onReceivedRaw;
        gizwitsws.onReceivedAttrs = onReceivedAttrs;
        gizwitsws.onError = onError;

        console.log("初始化对象成功!");

        init();
    }

    function init() {
        gizwitsws.init();
        conndids = [];
        console.log("######################################");
        console.log("已发送init指令!");
    }

    function connect(did) {
        gizwitsws.connect(did);
        console.log("已发送connect指令! did:"+did);
    }

    function read(did, names) {
        if (names == "") {
            gizwitsws.read(did, null);
        } else {
            gizwitsws.read(did, JSON.parse(names));
        };
        console.log("已发送read指令!");
    }

    function writeCommand() {
        var did = $('#writeDid').val();
        if ($('#commType').val() == "attrs_v4") {
            var attrs = $('#command').val();
            try {
                gizwitsws.write(did, JSON.parse(attrs));
                console.log("已对设备" + did + "发送write指令: " + attrs);
            } catch (e) {
                console.log("数据格式错误：" + e);
            }
        } else {
            var raw = $('#command').val();
            try {
                gizwitsws.send(did, JSON.parse(raw));
                console.log("已对设备" + did + "发送raw指令: " + raw);
            } catch (e) {
                console.log("数据格式错误：" + e);
            }

        }
    }

    //=========================================================
    // callback functions
    //=========================================================
    function onInit(devices) {
        console.log("当前设备mac: " + $(".swiper-slide-active").attr("device_mac"));
        if (devices.length == 0) {
            console.log("没有绑定的设备");
        } else {
            for (var i = 0; i < devices.length; i++) {
                console.log("==================================================");
                console.log("已绑定设备，did=" + devices[i].did);
                console.log("已绑定设备，mac=" + devices[i].mac);
                console.log("已绑定设备，product_key=" + devices[i].product_key);
                console.log("已绑定设备，is_online=" + devices[i].is_online);
                console.log("已绑定设备, dev_alias=" + devices[i].dev_alias);
                console.log("已绑定设备，remark=" + devices[i].remark);
                if($(".swiper-slide-active").attr("device_mac") == devices[i].mac){  //当前显示的device
                    connect(devices[i].did);
                }
            }
        }
    }

    function onConnected(did) {
        console.log("与设备:" + did + "连接成功!");
        read(did,"");
    }

    function onOnlineStatusChanged(value) {
        console.log("设备上下线通知，did=" + value.did);
        console.log("设备上下线通知，is_online=" + value.is_online);
    }

    function onReceivedRaw(value) {
        var str = "收到设备" + value.did + "的Raw: [";
        for (var i = 0; i < value.raw.length; i++) {
            str = str + value.raw[i] + ",";
        }
        str = str.substr(0, str.length - 1) + "]";
        console.log(str);
    }

    function onReceivedAttrs(value) {
        var str = "收到设备" + value.did + "的Attrs: ";
        for (var key in value.attrs) {
            str = str + key + ":" + value.attrs[key] + "; ";
            //将数据填入页面
            $(".swiper-slide-active").find("span[name="+key+"]").text(value.attrs[key]);
        }
        console.log(str);
    }

    function onError(value) {
        console.log(value.toString());
    }

<!-- Initialize Swiper -->
    var tabsSwiper = new Swiper('.swiper-container',{
        on: {
            slideChange: function () {
                $(".tabs .active").removeClass('active');
                $(".tabs li").eq(this.activeIndex).addClass('active');
                init();
            }
        }

    });

    $(".tabs li").on('touchstart mousedown', function(e) {
        e.preventDefault();
        $(".tabs .active").removeClass('active');
        $(this).addClass('active');
        tabsSwiper.slideTo($(this).index());
        $('.counter-value').each(function(){
            $(this).prop('Counter',0).animate({
                Counter: $(this).text()
            },{
                duration: 2000,
                easing: 'swing',
                step: function (now){
                    $(this).text(Math.ceil(now));
                }
            });
        });

    });

    $(function(){
        $(".myhome").on("click",function(){
           $(this).find(".downmenu").slideToggle();
        });

        $(document).mouseup(function(e){
            var _con = $('.myhome,.downmenu ');   // 设置目标区域
            if(!_con.is(e.target) && _con.has(e.target).length === 0){ // Mark 1
                $(".downmenu").hide();
            }
        });
    });

    var closePop = function(){
        $(".mask,.pop-add").hide();
    }
    var showPop = function(){
        $(".mask,.pop-add").show();
    }

    var getAirQuality = function(roomId){
        $.ajax({
            url: "/mp/roomAirQuality/" + roomId,    //请求的url地址
            // dataType: "json",   //返回格式为json
            // async: true, //请求是否异步，默认为异步，这也是ajax重要特性
            // data: { "id": "value" },    //参数值
            type: "GET",   //请求方式
            beforeSend: function(request) {
                //请求前的处理
                // request.setRequestHeader("Content-type","application/json");
                // request.setRequestHeader("Source","101");
                // request.setRequestHeader("Token","aaw--wssw-ss...");
            },
            success: function(data) {
                //请求成功时处理
                $("#air_quality_zone").append(data);
            },
            complete: function() {
                //请求完成的处理
            },
            error: function() {
                //请求出错处理
            }
        });

    }

</script>

<script type="text/javascript">
    $(document).ready(function(){
        $('.counter-value').each(function(){
            $(this).prop('Counter',0).animate({
                Counter: $(this).text()
            },{
                duration: 2000,
                easing: 'swing',
                step: function (now){
                    $(this).text(Math.ceil(now));
                }
            });
        });
    });
</script>
</body>
</html>