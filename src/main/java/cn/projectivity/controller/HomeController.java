package cn.projectivity.controller;

import cn.projectivity.entity.Device;
import cn.projectivity.entity.Home;
import cn.projectivity.entity.Room;
import cn.projectivity.entity.User;
import cn.projectivity.service.HomeService;
import cn.projectivity.service.RoomService;
import cn.projectivity.service.SysRoleService;
import cn.projectivity.service.UserService;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/mp")
public class HomeController {
    @Autowired
    private UserService userService;
    @Autowired
    private HomeService homeService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private SysRoleService sysRoleService;

    @Value("${gizwits.gizwitsAppId}")
    private String gizwitsAppId;

    @RequiresPermissions("basic")
    @GetMapping("/home/view")
    public String viewHome(Model model) {
        //从session获取用户信息
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        User user = (User) session.getAttribute("user");
        user = userService.findById(user.getId()).get();
        List<Home> homeList = user.getHomeList();
        model.addAttribute("homeList", homeList);
        return "air_quality/home_manage";
    }

    @RequiresPermissions("basic")
    @PostMapping("/home/new")
    public String newHome(@ModelAttribute Home home, Model model) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        User user = (User) session.getAttribute("user");
        home.setSequence(1);
        home.setUser(user);
        home.setCreateTime(new Date());
        home = homeService.save(home);
        Room room = new Room();
        room.setHome(home);
        room.setName("房间");
        room.setSequence(1);
        room.setCreateTime(new Date());
        room = roomService.save(room);
        return "redirect:/home/view";
    }

    @RequiresPermissions("basic")
    @PostMapping("/home/{id}/edit")
    public String editHome(@PathVariable String id, @ModelAttribute Home home, Model model) {
        Home _home = homeService.findById(id);
        _home.setName(home.getName());
        _home.setUpdateTime(new Date());
        User user = UserUtil.getLoginUser();
        if (_home.getUser().getId() == user.getId()){
            homeService.save(_home);
        }
        return "redirect:/home/view";
    }

    @RequiresPermissions("basic")
    @RequestMapping("/home/{id}/delete")
    public String deleteHome(@PathVariable String id, Model model) {
        User user = UserUtil.getLoginUser();
        Home home = homeService.findById(id);
        if (home.getUser().getId() == user.getId()){
            homeService.delete(home);
        }
        //删除家庭时解绑所有设备
        List<Room> roomList = home.getRoomList();
        for(Room room: roomList){
            List<Device> deviceList = room.getDeviceList();
            if(deviceList.size() > 0){
                Device device = deviceList.get(0);
                try {
                    Boolean isSuccess = OpenApi.unbindDevice(user.getUsername(), gizwitsAppId, device.getGizwitsDid());
                    if(isSuccess){
                        System.out.println("### 设备ID：" + device.getId() + "机智云解绑操作成功！");
                    }else{
                        System.out.println("### 设备ID：" + device.getId() + "机智云解绑操作失败！！！");
                    }
                } catch (GizwitsException e) {
                    e.printStackTrace();
                }
            }
        }
        return "redirect:/home/view";
    }

}