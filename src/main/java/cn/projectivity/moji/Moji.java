package cn.projectivity.moji;

import cn.projectivity.aliyun.HttpUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

public class Moji {
    private static final String APPCODE = "7eeb9015ce124c85b3bf2997c1b71025";

    public static AQI aqi(long cityId){
        String host = "http://aliv11.data.moji.com";
        String path = "/whapi/json/alicityweather/aqi";
        String method = "POST";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + APPCODE);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("cityId", cityId+"");
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
//            System.out.println(response.toString());
            //获取response的body
            String result = EntityUtils.toString(response.getEntity());
            System.out.println(result);

            JsonObject obj = new JsonParser().parse(result).getAsJsonObject();
            JsonObject dataObj = obj.get("data").getAsJsonObject();
            JsonObject aqiObj = dataObj.get("aqi").getAsJsonObject();

            Gson gson = new Gson();
            AQI aqi = gson.fromJson(aqiObj, AQI.class);
            return aqi;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
