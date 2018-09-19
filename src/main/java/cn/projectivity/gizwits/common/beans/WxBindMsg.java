package cn.projectivity.gizwits.common.beans;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "xml")
public class WxBindMsg {
    @JacksonXmlProperty(localName = "ToUserName")
    private String toUserName;  //  是   接收方（公众号）的user name
    @JacksonXmlProperty(localName = "FromUserName")
    private String fromUserName; // 是   发送方（微信用户）的user name
    @JacksonXmlProperty(localName = "CreateTime")
    private String createTime;  //  是   消息创建时间，消息后台生成
    @JacksonXmlProperty(localName = "MsgType")
    private String msgType;     //是     消息类型：device_event
    @JacksonXmlProperty(localName = "Event")
    private String event;       //  是   事件类型，取值为bind/unbind bind：绑定设备 unbind：解除绑定
    @JacksonXmlProperty(localName = "DeviceType")
    private String deviceType;  //  是   设备类型，目前为“公众账号原始ID”
    @JacksonXmlProperty(localName = "DeviceID")
    private String deviceID;    //  是   设备ID，第三方提供
    @JacksonXmlProperty(localName = "Content")
    private String content;         //是     当Event为bind时，Content字段存放二维码中
    //  第三方追加的自定义的数据
    //  详情见1.5章节 获取设备二维码
    //  或 1.11.1章节 API：获取deviceid和二维码
    @JacksonXmlProperty(localName = "SessionID")
    private String sessionID;   //   是  微信客户端生成的session id，用于request和response对应，
    //  因此响应中该字段第三方需要原封不变的带回
    @JacksonXmlProperty(localName = "OpenID")
    private String openID;      //  是    微信账号的OpenID

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getOpenID() {
        return openID;
    }

    public void setOpenID(String openID) {
        this.openID = openID;
    }

    @Override
    public String toString() {
        return "WxBindMsg{" +
                "toUserName='" + toUserName + '\'' +
                ", fromUserName='" + fromUserName + '\'' +
                ", createTime='" + createTime + '\'' +
                ", msgType='" + msgType + '\'' +
                ", event='" + event + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", deviceID='" + deviceID + '\'' +
                ", content='" + content + '\'' +
                ", sessionID='" + sessionID + '\'' +
                ", openID='" + openID + '\'' +
                '}';
    }
}
