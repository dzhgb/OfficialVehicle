package cn.projectivity.controller;

import cn.projectivity.entity.User;
import cn.projectivity.entity.virtual.UserViewEntity;
import cn.projectivity.entity.virtual.DatatablesViewPage;
import cn.projectivity.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/admin/user")
public class UserManageController {
    @Autowired
    private UserService userService;

    /**
     * 用户查询.
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    @RequiresPermissions("admin:*")//权限管理;
    @RequiresPermissions("userInfo:add")//权限管理;
    public String userInfo(@PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC)Pageable pageable, Model model){
        Page<User> userList = userService.findAll(pageable);
        model.addAttribute("userList", userList);
        return "admin/user_list";
    }

    /**
     * 用户查询.
     * @return
     */
    @RequestMapping(value = "/list/ajax", method = RequestMethod.GET)
    @RequiresPermissions("userInfo:add")//权限管理;
    @ResponseBody
    public DatatablesViewPage<UserViewEntity> userInfoAjax(HttpServletRequest request){
        //获取分页控件的信息
        String start = request.getParameter("start");
        String length = request.getParameter("length");
        //获取前台额外传递过来的查询条件
        String extra_search = request.getParameter("extra_search");
        //随便组织的查询结果
        Pageable pageable = PageRequest.of(Integer.parseInt(start)/Integer.parseInt(length),Integer.parseInt(length), Sort.Direction.DESC,"id");
        Page<User> userList = userService.findAll(pageable);
        List<UserViewEntity> dtList = new ArrayList<>();
        for(User u: userList){
            UserViewEntity dtUser = new UserViewEntity();
            dtUser.setId(u.getId());
            dtUser.setUsername(u.getUsername());
            dtUser.setName(u.getName());
            dtUser.setOpenid(u.getOpenid());
            dtUser.setNickname(u.getNickname());
            dtList.add(dtUser);
        }
        DatatablesViewPage<UserViewEntity> view = new DatatablesViewPage<>();
        view.setiTotalDisplayRecords((int) userList.getTotalElements());
        view.setiTotalRecords((int) userList.getTotalElements());
        view.setAaData(dtList);
        return view;
    }

    /**
     * 用户添加;
     * @return
     */
    @RequestMapping("/userAdd")
    @RequiresPermissions("userInfo:add")//权限管理;
    public String userInfoAdd(){
        return "admin/userInfoAdd";
    }

    /**
     * 用户删除;
     * @return
     */
    @RequestMapping("/userDel")
    @RequiresPermissions("userInfo:del")//权限管理;
    public String userDel(){
        return "admin/userInfoDel";
    }
}