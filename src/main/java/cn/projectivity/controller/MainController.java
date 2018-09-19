package cn.projectivity.controller;

import cn.projectivity.entity.Home;
import cn.projectivity.entity.Room;
import cn.projectivity.entity.SysRole;
import cn.projectivity.entity.User;
import cn.projectivity.service.HomeService;
import cn.projectivity.service.RoomService;
import cn.projectivity.service.SysRoleService;
import cn.projectivity.service.UserService;
import cn.projectivity.util.UserUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import weixin.popular.api.SnsAPI;
import weixin.popular.bean.sns.SnsToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private UserService userService;
    @Autowired
    private HomeService homeService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private SysRoleService sysRoleService;

    @Value("${weixin.mp.AppID}")
    private String wxAppId;
    @Value("${weixin.mp.AppSecret}")
    private String wxAppSecret;

    @RequestMapping({"/","/index"})
    public String index(){
        return"admin/index";
    }

    @RequestMapping("/admin/login")
    public String login(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map, @RequestParam(required = false) String app, Model model) throws Exception{
        if(app != null && app.equals("weixin")){
            String redirectUri = "http://www.projectivity.cn/wechatLogin";
            System.out.println("### 用户同意授权，获取code ###");
            response.sendRedirect(SnsAPI.connectOauth2Authorize(wxAppId, redirectUri, true, "STATE")); //第一步：用户同意授权，获取code
        }else{
//        System.out.println("HomeController.login()");
            // 登录失败从request中获取shiro处理的异常信息。
            // shiroLoginFailure:就是shiro异常类的全类名.
            String exception = (String) request.getAttribute("shiroLoginFailure");
//        System.out.println("exception=" + exception);
            String msg = "";
            if (exception != null) {
                if (UnknownAccountException.class.getName().equals(exception)) {
                    System.out.println("UnknownAccountException -- > 账号不存在：");
                    msg = "UnknownAccountException -- > 账号不存在：";
                } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                    System.out.println("IncorrectCredentialsException -- > 密码不正确：");
                    msg = "IncorrectCredentialsException -- > 密码不正确：";
                } else if ("kaptchaValidateFailed".equals(exception)) {
                    System.out.println("kaptchaValidateFailed -- > 验证码错误");
                    msg = "kaptchaValidateFailed -- > 验证码错误";
                } else {
                    msg = "else >> "+exception;
                    System.out.println("else -- >" + exception);
                }
            }
            map.put("msg", msg);
            // 此方法不处理登录成功,由shiro进行处理
        }
        return "admin/login";
    }

    @RequestMapping("/wechatLogin")
    public String wechatLogin(String code, Model model) throws Exception{
        System.out.println("### CODE: " + code + " ###");
        SnsToken token = SnsAPI.oauth2AccessToken(wxAppId, wxAppSecret, code);  //第二步：通过code换取网页授权access_token
        System.out.println("###WX_OPENID: " + token.getOpenid() + "###");
        if(token.getAccess_token() != null && token.getExpires_in() == null && token.getExpires_in().intValue() < 600){
            token = SnsAPI.oauth2RefreshToken(wxAppId, token.getRefresh_token());   //第三步：刷新access_token（如果需要）
        }
        weixin.popular.bean.user.User wxUserInfo = SnsAPI.userinfo(token.getAccess_token(), token.getOpenid(), "zh_CN"); //第四步：拉取用户信息(需scope为 snsapi_userinfo)
        User user = userService.findByUsername(token.getOpenid());
        if(user == null){   //创建新用户
            user = new User();
            user.setUsername(token.getOpenid());
            user.setPassword("123456");
            user.setName(wxUserInfo.getNickname());
            String salt = UserUtil.generateSalt();
            user.setSalt(salt);
            user.setPassword(UserUtil.encodePasswordWithSalt(user.getUsername(), user.getPassword(), salt));
            user.setCreateTime(new Date());
            //设置为普通用户角色user
            SysRole sysRole = sysRoleService.findByRole("user");
            List<SysRole> sysRoleList = new ArrayList<>();
            sysRoleList.add(sysRole);
            user.setRoleList(sysRoleList);
            user = updateWxUserInfoToUser(wxUserInfo, user);
            user = userService.save(user);
            //创建默认HOME和ROOM
            Home home = new Home();
            home.setName("默认");
            home.setSequence(0);
            home.setUser(user);
            home = homeService.save(home);
            Room room = new Room();
            room.setName("默认");
            room.setSequence(0);
            room.setHome(home);
            room = roomService.save(room);
        }else{  //更新用户信息
            user.setUpdateTime(new Date());
            user = updateWxUserInfoToUser(wxUserInfo, user);
            user = userService.save(user);
        }
        model.addAttribute("username", user.getUsername());
//        System.out.println("### WX_LOGIN_USER: " + user.getUsername() + " ###");
//        model.addAttribute("password", "123456");
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUsername(), "123456".toCharArray());
        SecurityUtils.getSubject().login(usernamePasswordToken);
        return "redirect:/mp/airQuality/home";
    }

    private User updateWxUserInfoToUser(weixin.popular.bean.user.User wxUserInfo, User user){
        user.setSubscribe(wxUserInfo.getSubscribe() == null ? 0 : wxUserInfo.getSubscribe());
        user.setOpenid(wxUserInfo.getOpenid());
        user.setNickname(wxUserInfo.getNickname());
        user.setSex(wxUserInfo.getSex() == null ? 0 : wxUserInfo.getSex());
//        user.setCity(wxUserInfo.getCity());
//        user.setCountry(wxUserInfo.getCountry());
//        user.setProvince(wxUserInfo.getProvince());
//        user.setLanguage(wxUserInfo.getLanguage());
        user.setHeadimgurl(wxUserInfo.getHeadimgurl());
        user.setSubscribe_time(wxUserInfo.getSubscribe_time() == null ? 0 : wxUserInfo.getSubscribe_time());
//        user.setPrivilege(Arrays.toString(wxUserInfo.getPrivilege()));
//        user.setUnionid(wxUserInfo.getUnionid());
//        user.setRemark(wxUserInfo.getRemark());
//        user.setGroupid(wxUserInfo.getGroupid() == null ? 0 : wxUserInfo.getGroupid());
//        user.setTagid_list(Arrays.toString(wxUserInfo.getTagid_list()));
        return user;
    }

    @RequestMapping("/403")
    public String unauthorizedRole(){
        System.out.println("------没有权限-------");
        return "admin/403";
    }

}