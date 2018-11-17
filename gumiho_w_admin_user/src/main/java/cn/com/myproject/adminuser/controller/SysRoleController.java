package cn.com.myproject.adminuser.controller;

import cn.com.myproject.adminuser.service.ISysRoleDepartmentService;
import cn.com.myproject.adminuser.service.ISysRoleService;
import cn.com.myproject.adminuser.service.ISysUserRoleService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 角色管理
 *
 */
@Controller
@RequestMapping("/sysRole")
public class SysRoleController {
    private static final Logger logger = LoggerFactory.getLogger(SysRoleController.class);

    @Autowired
    private ISysRoleService sysRoleService;

    @Autowired
    private ISysRoleDepartmentService sysRoleDepartmentService;

    @Autowired
    private ISysUserRoleService sysUserRoleService;
    @ResponseBody
    @RequestMapping("/list")
    public PageInfo<SysRoleVO> getList(int rows, int page,String keyword,String departmentId){
        String keyword2 =null;
        if(null != keyword) {
            try {
                keyword2 = URLDecoder.decode(keyword, "UTF-8");
                keyword2 = keyword2.replace(" ", "");
            } catch (UnsupportedEncodingException e) {
                logger.error("[{}]-[{}]时，发生异常，异常信息为[{}]", "SysRoleController", "getList", e.getMessage());
            }
        }
        PageInfo<SysRoleVO> list = sysRoleService.getPage(page, rows,keyword2,departmentId);
        return list;
    }

    @ResponseBody
    @RequestMapping("/addRoles")
    public Map<String, Object> addRoles(SysRoleVO sysRole, @RequestParam("rolename") String rolename , @RequestParam("departmentId") String departmentId ) {
        Map<String,Object> result = new HashMap<String,Object>();
        try {
            if (StringUtils.isNotBlank(rolename) && StringUtils.isNotBlank(departmentId)) {
                sysRole.setRoleId(UUID.randomUUID().toString().replace("-", ""));
                sysRole.setRoleName(URLDecoder.decode(rolename, "UTF-8"));
                sysRoleService.addRoles(sysRole);
                SysRoleDepartmentVO sysRoleDepartmentVO = new SysRoleDepartmentVO();
                sysRoleDepartmentVO.setDepartmentId(departmentId);
                sysRoleDepartmentVO.setRoleId(sysRole.getRoleId());
                sysRoleDepartmentService.addSysRoleDepartment(sysRoleDepartmentVO);
            }
            result.put("status","success");
        } catch (Exception ex) {
            ex.printStackTrace();
            result.put("status","error");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/updateRoles")
    public String updateRoles(SysRoleVO sysRole) {
        String result = "";
        try {
            sysRoleService.updateRoles(sysRole);
            result = "修改成功!";
        } catch (Exception ex) {
            ex.printStackTrace();
            result = "修改失败！";
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/checkRoles")
    public Integer checkRoles(@RequestParam("roleName") String roleName) throws UnsupportedEncodingException {
        logger.info("修改的角色名称:" + URLDecoder.decode(roleName, "UTF-8"));

        Integer info = sysRoleService.checkRoles(roleName);

        if (1 == info) {
            return 1;
        }
        return 0;

    }

    @ResponseBody
    @RequestMapping(value = "/delRoles")
    public void delRoles(@RequestParam(value = "id") Integer id) {
        logger.info(id + "---删除");
        // String[] s = id.split(",");
        sysRoleService.delRoles(id);
    }


    @ResponseBody
    @RequestMapping("/selectRoles")
    public SysRoleVO selectRoles(@RequestParam("id") Integer id) {
        SysRoleVO role = new SysRoleVO();
        role = sysRoleService.selectRoles(id);
        logger.info(role + "对象");
        return role;
    }

    @ResponseBody
    @RequestMapping("/getRole")
    public Map<String,Object> getRole() {
        Map<String,Object> result = new HashMap<String,Object>();
        List<SysRoleVO> roleList = sysRoleService.getRole();
        result.put("status","success");
        result.put("roleList",roleList);
        return result;
    }

    @ResponseBody
    @RequestMapping("/getRolesByDepartmentId")
    public Map<String,Object> getRolesByDepartmentId(@RequestParam("userId") String userId,@RequestParam("departmentId") String departmentId){
        Map<String,Object> result = new HashMap<String,Object>();
        List<SysUserRoleVO> sysUserRoleVOList = null;

        if (null != userId) {
            sysUserRoleVOList = sysUserRoleService.findSysUserRoleByUserId(userId);
        }
        List<SysRoleVO> roleList = sysRoleService.selectRolesByDepartmentId(departmentId);
        result.put("status","success");
        result.put("roleList",roleList);
        result.put("userRoleList",sysUserRoleVOList);
        return result;
    }
}
