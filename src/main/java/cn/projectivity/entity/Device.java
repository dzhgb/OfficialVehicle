package cn.projectivity.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Device extends IdEntity {
    private String name;
    private int sequence;
    @ManyToOne
    private Room room;
    private String gizwitsDid; //设备在机智云平台的注册id
    private String wechatOpenId; //微信中的OpenId，也就是机智云中注册的匿名用户
    private String gizwitsAppId;    //机智云产品所绑定的APP的id
    private String gizwitsProductKey;   //机智云的产品key
    private String gizwitsProductSecret;    //机智云的产品Secret
    private String mac; //设备的Mac地址
    private String deviceAlias; //设备的别名
    private String deviceRemark;    //设备的备注
    @ManyToMany
    @JoinTable(name="DeviceSharedUser",joinColumns={@JoinColumn(name="deviceId")},inverseJoinColumns={@JoinColumn(name="userId")})
    private List<User> sharedUsers;  //设备共享的用户

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getDeviceAlias() {
        return deviceAlias;
    }

    public void setDeviceAlias(String deviceAlias) {
        this.deviceAlias = deviceAlias;
    }

    public String getDeviceRemark() {
        return deviceRemark;
    }

    public void setDeviceRemark(String deviceRemark) {
        this.deviceRemark = deviceRemark;
    }

    public String getGizwitsDid() {
        return gizwitsDid;
    }

    public void setGizwitsDid(String gizwitsDid) {
        this.gizwitsDid = gizwitsDid;
    }

    public String getWechatOpenId() {
        return wechatOpenId;
    }

    public void setWechatOpenId(String wechatOpenId) {
        this.wechatOpenId = wechatOpenId;
    }

    public String getGizwitsAppId() {
        return gizwitsAppId;
    }

    public void setGizwitsAppId(String gizwitsAppId) {
        this.gizwitsAppId = gizwitsAppId;
    }

    public String getGizwitsProductKey() {
        return gizwitsProductKey;
    }

    public void setGizwitsProductKey(String gizwitsProductKey) {
        this.gizwitsProductKey = gizwitsProductKey;
    }

    public String getGizwitsProductSecret() {
        return gizwitsProductSecret;
    }

    public void setGizwitsProductSecret(String gizwitsProductSecret) {
        this.gizwitsProductSecret = gizwitsProductSecret;
    }

    public List<User> getSharedUsers() {
        return sharedUsers;
    }

    public void setSharedUsers(List<User> sharedUsers) {
        this.sharedUsers = sharedUsers;
    }
}
