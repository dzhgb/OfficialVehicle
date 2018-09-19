package cn.projectivity.controller;

import cn.projectivity.entity.Device;
import cn.projectivity.entity.Home;
import cn.projectivity.entity.Room;
import cn.projectivity.entity.User;
import cn.projectivity.gizwits.common.beans.WxBindMsg;
import cn.projectivity.gizwits.common.gizwits.App;
import cn.projectivity.gizwits.common.utils.XMLUtil;
import cn.projectivity.gizwits.service.WxDeviceService;
import cn.projectivity.service.DeviceService;
import cn.projectivity.service.HomeService;
import cn.projectivity.service.RoomService;
import cn.projectivity.service.UserService;
import com.gizwits.openapi.sdk.DeviceInfo;
import com.gizwits.openapi.sdk.GizwitsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/iot")
public class WeixinIOTController {
    @Autowired
    private UserService userService;
    @Autowired
    private HomeService homeService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private WxDeviceService wxDeviceService;
    @Autowired
    private App gizwitsWechatApp;

    @Value("${gizwits.gizwitsAppId}")
    private String gizwitsAppId;
    @Value("${gizwits.gizwitsAppSecret}")
    private String gizwitsAppSecret;
    @Value("${gizwits.gizwitsProductKey}")
    private String gizwitsProductKey;
    @Value("${gizwits.gizwitsProductSecret}")
    private String gizwitsProductSecret;

    @RequestMapping(value = "/deviceBind", headers = {"content-type=application/xml","content-type=text/xml"})
    public String deviceBind(@RequestBody WxBindMsg xml) {
        System.out.println(XMLUtil.toXML(xml));
        if (xml != null){
            DeviceInfo deviceInfo = null;
            try {
                gizwitsWechatApp.setApp_id(gizwitsAppId);
                gizwitsWechatApp.setProduct_key(gizwitsProductKey);
                gizwitsWechatApp.setProduct_Secret(gizwitsProductSecret);
                deviceInfo = gizwitsWechatApp.bindDevice(xml.getOpenID(), xml.getDeviceID());   //deviceID必须为Mac地址才能绑定成功，否则会报com.gizwits.openapi.sdk.GizwitsException: Gizwits open api fault
                System.out.println(" ======> 绑定成功！did: " + deviceInfo.getDid() + ", mac:" + deviceInfo.getMac());
                //在数据库中新增device
                User user = userService.findByOpenid(xml.getOpenID());
                if(user != null){
                    List<Home> homeList = user.getHomeList();
                    if (homeList.size() > 0){
                        Home home = homeList.get(0);
                        Room room = null;
                        if(home.getRoomList().size() > 0 && home.getRoomList().get(0).getDeviceList().size() == 0){
                            room = home.getRoomList().get(0);
                        }else{
                            //新建ROOM存放DEVICE
                            room = new Room();
                            room.setHome(home);
                            room.setName("房间");
                            room.setSequence(1);
                            room.setCreateTime(new Date());
                            room = roomService.save(room);
                        }
                        Device device = new Device();
                        device.setRoom(room);
                        device.setName("空气净化器");
                        device.setSequence(0);
                        device.setGizwitsDid(xml.getDeviceID());
                        device.setWechatOpenId(xml.getOpenID());
                        device.setGizwitsAppId(gizwitsAppId);
                        device.setGizwitsProductKey(gizwitsProductKey);
                        device.setGizwitsProductSecret(gizwitsProductSecret);
                        device.setMac(xml.getDeviceID());
                        device.setDeviceAlias("");
                        device.setDeviceRemark("");
                        device.setCreateTime(new Date());
                        device = deviceService.save(device);
                        System.out.println("### 在数据库中新增device ###");
                        return "redirect:/airQuality/room/" + room.getId();
                    }
                }
            } catch (GizwitsException e) {
                e.printStackTrace();
                System.out.println(" <<<<<<<<<< 设备绑定出错：\n" + e.toString());
            }
        }
        return null;
    }
}
