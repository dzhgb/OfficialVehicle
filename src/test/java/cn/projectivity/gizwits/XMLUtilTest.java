package cn.projectivity.gizwits;

import cn.projectivity.gizwits.common.beans.WxEventDeviceBind;
import cn.projectivity.gizwits.common.utils.XMLUtil;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by matt on 16-5-25.
 */
public class XMLUtilTest {

    String xml;

    @Before
    public void setUp() throws Exception {
        xml  =
            "<xml>" +
                "<ToUserName><![CDATA[gh_12019f374fd0]]></ToUserName>" +
                "<FromUserName><![CDATA[o4s0t1W9-sZ8cN3UHATL5OjZwLdo]]></FromUserName>" +
                "<CreateTime>1457337567</CreateTime>" +
                "<MsgType><![CDATA[device_event]]></MsgType>" +
                "<Event><![CDATA[bind]]></Event>" +
                "<DeviceType><![CDATA[gh_12019f374fd0]]></DeviceType>" +
                "<DeviceID><![CDATA[YTQBRV6NJNvgawUL6JoWju]]></DeviceID>" +
                "<Content><![CDATA[123456]]></Content>" +
                "<SessionID>0</SessionID>" +
                "<OpenID><![CDATA[o4s0t1W9-sZ8cN3UHATL5OjZwLdo]]></OpenID>" +
            "</xml>";

//        xml =
//            "<xml>" +
//                "<ToUserName><![CDATA[gh_12019f374fd0]]></ToUserName>" +
//                "<FromUserName><![CDATA[o4s0t1W9-sZ8cN3UHATL5OjZwLdo]]></FromUserName>" +
//                "<CreateTime>1457337567</CreateTime>" +
//                "<MsgType><![CDATA[device_event]]></MsgType>" +
//                "<Event><![CDATA[unsubscribe_status]]></Event>" +
//                "<DeviceType><![CDATA[gh_1ad37453ece0]]></DeviceType>" +
//                "<DeviceID><![CDATA[5CCF7F800F63]]></DeviceID>" +
//                "<OpType>0</OpType>" +
//                "<OpenID><![CDATA[o9S82ww4Ur79p8xDFNHk06fvs_ew]]></OpenID>" +
//            "</xml>";
    }

    @Test
    public void getWxMsg() throws Exception {
        WxEventDeviceBind deviceInfo = (WxEventDeviceBind) XMLUtil.getWxMsg(WxEventDeviceBind.class, xml);
        System.out.println(deviceInfo);
        System.out.println(XMLUtil.toXML(deviceInfo));
    }

    @Test
    public void XML2Map() throws Exception {
        System.out.println(XMLUtil.XML2Map(xml));
    }
}