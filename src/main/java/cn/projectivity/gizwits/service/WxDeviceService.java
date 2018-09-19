package cn.projectivity.gizwits.service;

import cn.projectivity.gizwits.common.beans.WxEventDeviceBind;
import cn.projectivity.gizwits.common.gizwits.App;
import cn.projectivity.gizwits.common.utils.XMLUtil;
import com.gizwits.openapi.sdk.DeviceInfo;
import com.gizwits.openapi.sdk.GizwitsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by matt on 16-5-25.
 */
@Service
public class WxDeviceService {

//    private final Logger logger = LoggerFactory.getLogger(DeviceService.class);

    @Autowired
    App gizwitsWechatApp;

    public void handle(String xml) {
//        logger.info(" -------->  开始处理设备事件");
        System.out.println(" -------->  开始处理设备事件");
        WxEventDeviceBind device = (WxEventDeviceBind) XMLUtil.getWxMsg(WxEventDeviceBind.class, xml);
//        logger.info(" -------->  解析成功：\n{}\n", device);
        System.out.println(" -------->  解析成功：\n{}\n" + device.toString());
        switch (device.getEvent()) {
            case "bind":
                bindDevice(device);
                break;
            case "unbind":
                unbindDevice(device);
                break;
            default:
//                logger.info(" -------->  发现事件：\n{}\n", device);
                System.out.println(" -------->  发现事件：\n{}\n" + device.toString());
        }
    }

    public void unbindDevice(WxEventDeviceBind device) {
        try {
            gizwitsWechatApp.unbindDeviceByMac(device.getOpenID(), device.getDeviceID());
//            logger.info(" ======> 解绑成功！");
            System.out.println(" ======> 解绑成功！");
        } catch (GizwitsException e) {
//            logger.error(" <<<<<<<<<< 设备解绑失败：\n", e);
            System.out.println(" <<<<<<<<<< 设备解绑失败：\n" + e.toString());
        }
    }

    public void bindDevice(WxEventDeviceBind device) {
        DeviceInfo deviceInfo = null;
        try {
            deviceInfo = gizwitsWechatApp.bindDevice(device.getOpenID(), device.getDeviceID());
//            logger.info(" ======> 绑定成功！\ndid:{},mac{}\n", deviceInfo.getDid(), deviceInfo.getMac());
            System.out.println(" ======> 绑定成功！\ndid:{},mac{}\n" + deviceInfo.getDid() + ", " + deviceInfo.getMac());
        } catch (GizwitsException e) {
            e.printStackTrace();
//            logger.error(" <<<<<<<<<< 设备绑定出错：\n", e);
            System.out.println(" <<<<<<<<<< 设备绑定出错：\n" + e.toString());
        }
    }

    public List<DeviceInfo> getDeviceList(String openId) {
        List<DeviceInfo> deviceList = null;
        try {
            deviceList = gizwitsWechatApp.getBoundDevices(openId);
            // 打印查询数据
            for (DeviceInfo deviceInfo : deviceList) {
//                logger.info("  -----> 查询列表列表：\nalias:{}, did:{};, mac:{}, status:{}\n",
//                        deviceInfo.getAlias(),
//                        deviceInfo.getDid(),
//                        deviceInfo.getMac(),
//                        deviceInfo.getIsOnline());
                System.out.println("  -----> 查询列表列表：\nalias:{}, did:{};, mac:{}, status:{}\n" +
                        deviceInfo.getAlias() + ", " +
                        deviceInfo.getDid() + ", " +
                        deviceInfo.getMac() + ", " +
                        deviceInfo.getIsOnline());
            }
        } catch (GizwitsException e) {
            e.printStackTrace();
        }
        return deviceList;
    }
}
