package cn.projectivity.controller;

import cn.projectivity.entity.Device;
import cn.projectivity.entity.Home;
import cn.projectivity.entity.Room;
import cn.projectivity.entity.User;
import cn.projectivity.moji.AQI;
import cn.projectivity.moji.Moji;
import cn.projectivity.moji.MojiCity;
import cn.projectivity.service.*;
import cn.projectivity.util.UserUtil;
import com.gizwits.openapi.sdk.GizwitsException;
import com.gizwits.openapi.sdk.OpenApi;
import com.google.gson.Gson;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/mp")
public class AirQualityController {
    @Autowired
    private UserService userService;
    @Autowired
    private HomeService homeService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private MojiCityService mojiCityService;

    @Value("${weixin.mp.AppID}")
    private String wxAppId;
    @Value("${weixin.mp.AppSecret}")
    private String wxAppSecret;

    @Value("${gizwits.gizwitsAppId}")
    private String gizwitsAppId;
    @Value("${gizwits.gizwitsAppSecret}")
    private String gizwitsAppSecret;
    @Value("${gizwits.gizwitsProductKey}")
    private String gizwitsProductKey;
    @Value("${gizwits.gizwitsProductSecret}")
    private String gizwitsProductSecret;

//    @RequiresPermissions("basic")
//    @GetMapping( "/mobileBind")
//    public String mobileBind(String code, String state) {
//        return "air_quality/mobile_bind";
//    }

    @RequiresPermissions("basic")
    @RequestMapping("/airQuality/home")
    public String homeAirQuality() {
        User user = userService.findById(UserUtil.getLoginUser().getId()).get();
        List<Home> homeList = user.getHomeList();
        return "redirect:/mp/airQuality/home/"+homeList.get(0).getId();
    }

    @RequiresPermissions("basic")
    @RequestMapping("/airQuality/home/{homeId}")
    public String homeAirQuality(@PathVariable String homeId, Model model) {
        User user = userService.findById(UserUtil.getLoginUser().getId()).get();
        List<Home> homeList = user.getHomeList();
        Home home = homeService.findById(homeId);
        List<Room> roomList = home.getRoomList();
        Room room = new Room();
        if (roomList.size() > 0){
            room = roomList.get(0);
        }
        model.addAttribute("wechatOpenId",user.getUsername());
        model.addAttribute("gizwitsAppId",gizwitsAppId);
        model.addAttribute("homeList", homeList);
        model.addAttribute("room", room);
        model.addAttribute("roomList", roomList);
        return "air_quality/air_quality";
    }

    @RequiresPermissions("basic")
    @RequestMapping("/airQuality/room")
    public String roomAirQuality() {
        User user = userService.findById(UserUtil.getLoginUser().getId()).get();
        List<Home> homeList = user.getHomeList();
        List<Room> roomList = new ArrayList<>();
        if(homeList.size() > 0){
            roomList = homeList.get(0).getRoomList();
        }
        return "redirect:/mp/airQuality/room/"+roomList.get(0).getId();
    }

    @RequiresPermissions("basic")
    @RequestMapping("/airQuality/room/{roomId}")
    public String roomAirQuality(@PathVariable String roomId, Model model) {
        Room room = roomService.findById(roomId);
        User user = userService.findById(UserUtil.getLoginUser().getId()).get();
        List<Home> homeList = user.getHomeList();
        Home home = room.getHome();
        List<Room> roomList = home.getRoomList();
        model.addAttribute("wechatOpenId",user.getUsername());
        model.addAttribute("gizwitsAppId",gizwitsAppId);
        model.addAttribute("homeList", homeList);
        model.addAttribute("room", room);
        model.addAttribute("roomList", roomList);
        return "air_quality/air_quality";
    }

    @ResponseBody
    @RequiresPermissions("basic")
    @RequestMapping("/liveAQI/{cityName}")
    public String liveAQI(@PathVariable String cityName){
        System.out.println("cityName: "+cityName);
        MojiCity mojiCity = mojiCityService.findByName(cityName);
        if(mojiCity != null){
            AQI aqi = Moji.aqi(mojiCity.getId());
            Gson gson = new Gson();
            System.out.println(gson.toJson(aqi));
            return gson.toJson(aqi);
        }
        return null;
    }
}
