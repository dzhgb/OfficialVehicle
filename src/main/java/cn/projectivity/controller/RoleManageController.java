package cn.projectivity.controller;

import cn.projectivity.entity.SysRole;
import cn.projectivity.entity.virtual.DatatablesViewPage;
import cn.projectivity.entity.virtual.RoleViewEntity;
import cn.projectivity.service.SysRoleService;
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
@RequestMapping("/admin/role")
public class RoleManageController {
    @Autowired
    private SysRoleService sysRoleService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String role_list(@PageableDefault(sort = {"id"})Pageable pageable, Model model){
        Page<SysRole> roleList = sysRoleService.findAll(pageable);
        model.addAttribute("roleList", roleList);
        return "admin/role/role_list";
    }

    @RequestMapping(value = "/list/data", method = RequestMethod.GET)
    @RequiresPermissions("userInfo:add")//权限管理;
    @ResponseBody
    public DatatablesViewPage<RoleViewEntity> userInfoAjax(HttpServletRequest request){
        //获取分页控件的信息
        String start = request.getParameter("start");
        String length = request.getParameter("length");
        //获取前台额外传递过来的查询条件
        String extra_search = request.getParameter("extra_search");
        //随便组织的查询结果
        Pageable pageable = PageRequest.of(Integer.parseInt(start)/Integer.parseInt(length),Integer.parseInt(length), Sort.Direction.DESC,"id");
        Page<SysRole> roleList = sysRoleService.findAll(pageable);
        List<RoleViewEntity> dtList = new ArrayList<>();
        for(SysRole s: roleList){
            RoleViewEntity dtRole = new RoleViewEntity();
            dtRole.setId(s.getId());
            dtRole.setRole(s.getRole());
            dtRole.setDescription(s.getDescription());
            dtRole.setAvailable(s.getAvailable());
            dtList.add(dtRole);
        }
        DatatablesViewPage<RoleViewEntity> view = new DatatablesViewPage<>();
        view.setiTotalDisplayRecords((int) roleList.getTotalElements());
        view.setiTotalRecords((int) roleList.getTotalElements());
        view.setAaData(dtList);
        return view;
    }
}
