package cn.projectivity.controller;

import cn.projectivity.entity.*;
import cn.projectivity.entity.Room;
import cn.projectivity.service.*;
import cn.projectivity.service.RoomService;
import cn.projectivity.util.UserUtil;
import com.gizwits.openapi.sdk.GizwitsException;
import com.gizwits.openapi.sdk.OpenApi;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/mp")
public class RoomController {
    @Autowired
    private UserService userService;
    @Autowired
    private HomeService homeService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private SysRoleService sysRoleService;

    @Value("${gizwits.gizwitsAppId}")
    private String gizwitsAppId;

    @RequiresPermissions("basic")
    @GetMapping("/home/{homeId}/rooms")
    public String viewRoom(Model model, @PathVariable String homeId) {
        Home home = homeService.findById(homeId);
        List<Room> roomList = home.getRoomList();
        model.addAttribute("roomList", roomList);
        model.addAttribute("home", home);
        return "air_quality/room_manage";
    }

    @RequiresPermissions("basic")
    @PostMapping("/home/{homeId}/room/new")
    public String newRoom(@PathVariable String homeId, @ModelAttribute Room room, Model model) {
        Home home = homeService.findById(homeId);
        room.setSequence(1);
        room.setHome(home);
        room.setCreateTime(new Date());
        room = roomService.save(room);
//        Device device = new Device();
//        device.setRoom(room);
//        device.setName("默认");
//        device.setSequence(1);
//        device.setCreateTime(new Date());
//        device = deviceService.save(device);
        return "redirect:/mp/home/"+homeId+"/rooms";
    }

    @RequiresPermissions("basic")
    @PostMapping("/room/{id}/edit")
    public String editRoom(@PathVariable String id, @ModelAttribute Room room, Model model) {
        Room _room = roomService.findById(id);
        _room.setName(room.getName());
        _room.setUpdateTime(new Date());
        if (_room.getHome().getUser().getId() == UserUtil.getLoginUser().getId()){
            roomService.save(_room);
        }
        return "redirect:/mp/home/"+_room.getHome().getId()+"/rooms";
    }

    @RequiresPermissions("basic")
    @RequestMapping("/room/{id}/delete")
    public String deleteRoom(@PathVariable String id, Model model) {
        Room room = roomService.findById(id);
        if (room.getHome().getUser().getId() == UserUtil.getLoginUser().getId()){
            roomService.delete(room);
        }
        //删除房间时解绑设备
        List<Device> deviceList = room.getDeviceList();
        if(deviceList.size() > 0){
            Device device = deviceList.get(0);
            try {
                Boolean isSuccess = OpenApi.unbindDevice(UserUtil.getLoginUser().getUsername(), gizwitsAppId, device.getGizwitsDid());
                if(isSuccess){
                    System.out.println("### 设备ID：" + device.getId() + "机智云解绑操作成功！");
                }else{
                    System.out.println("### 设备ID：" + device.getId() + "机智云解绑操作失败！！！");
                }
            } catch (GizwitsException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/mp/home/"+room.getHome().getId()+"/rooms";
    }

}