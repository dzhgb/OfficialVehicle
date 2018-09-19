package cn.projectivity.moji;

import cn.projectivity.aliyun.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

public class WeatherTest {
    public static void main(String[] args) {
        String host = "http://aliv11.data.moji.com";
        String path = "/whapi/json/alicityweather/aqi";
        String method = "POST";
        String appcode = "7eeb9015ce124c85b3bf2997c1b71025";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("cityId", "1061");
        bodys.put("token", "8b36edf8e3444047812be3a59d27bab9");


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            System.out.println(EntityUtils.toString(response.getEntity()));
//            {"code":0,"data":{"aqi":{"cityName":"无锡市","co":"8","coC":"0.8","no2":"10","no2C":"20.0","o3":"53","o3C":"162.0","pm10":"72","pm10C":"94.0","pm25":"89","pm25C":"66.0","pubtime":"1529899200000","rank":"545\/577","so2":"3","so2C":"8.0","value":"89"},"city":{"cityId":1061,"counname":"中国","name":"无锡市","pname":"江苏省","timezone":"8"}},"msg":"success","rc":{"c":0,"p":"success"}}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
