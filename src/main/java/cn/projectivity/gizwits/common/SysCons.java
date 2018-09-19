package cn.projectivity.gizwits.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by matt on 16-5-11.
 */
@Component
public class SysCons {
    @Value("${weixin.mp.token}")
    public String TOKEN;
    @Value("${weixin.mp.AppID}")
    public String APPID;
    @Value("${weixin.mp.AppSecret}")
    public String APPSECRET;

    @Override
    public String toString() {
        return "SysCons{" +
                "TOKEN='" + TOKEN + '\'' +
                ", APPID='" + APPID + '\'' +
                ", APPSECRET='" + APPSECRET + '\'' +
                '}';
    }
}
