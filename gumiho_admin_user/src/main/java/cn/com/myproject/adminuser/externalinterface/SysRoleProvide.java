package cn.com.myproject.adminuser.externalinterface;


import cn.com.myproject.adminuser.po.SysRole;
import cn.com.myproject.adminuser.service.ISysRoleService;
import cn.com.myproject.adminuser.vo.SysRoleVO;
import cn.com.myproject.util.BeanCopyUtils;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 *
 */
@RestController
@RequestMapping("/sysRole")
public class SysRoleProvide {

    private static final Logger logger = LoggerFactory.getLogger(SysRoleProvide.class);

    @Autowired
    private ISysRoleService service;

    @GetMapping("/getPage")
    public PageInfo<SysRoleVO> getPage(int pageNum, int pageSize) {
        PageInfo<SysRole> pageInfo = service.getPage(pageNum, pageSize);
        return BeanCopyUtils.copyList(pageInfo,SysRoleVO.class);
    }

    @PostMapping("/addRoles")
    public int addRoles(@RequestBody SysRoleVO sysRoleVO) {
        int result = 0;
        try {
            if (org.apache.commons.lang.StringUtils.isNotBlank(sysRoleVO.getRoleName())) {
                SysRole sysRole = new SysRole();
                BeanUtils.copyProperties(sysRoleVO,sysRole);
                sysRole.setRoleId(UUID.randomUUID().toString().replace("-", ""));
                sysRole.setRoleName(sysRole.getRoleName());
                sysRole.setVersion(1);
                sysRole.setStatus((short) 1);
                service.addRoles(sysRole);
            }
            result = 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }
        return result;
    }

    @PostMapping("/updateRoles")
    public String updateRoles(@RequestBody SysRoleVO sysRoleVO) {
        String result = "";
        try {
            SysRole sysRole = new SysRole();
            BeanUtils.copyProperties(sysRoleVO,sysRole);
            service.updateRoles(sysRole);
            result = "修改成功!";
        } catch (Exception ex) {
            ex.printStackTrace();
            result = "修改失败！";
        }
        return result;
    }

    @PostMapping("/checkRoles")
    public Integer checkRoles(@RequestBody String roleName) {
        Integer info = service.checkRoles(roleName);
        if (1 == info) {
            return 1;
        }
        return 0;
    }

    @PostMapping("/delRoles")
    public void delRoles(@RequestBody Integer id) {
        service.delRoles(id);
    }

    @PostMapping("/selectRoles")
    public SysRoleVO selectRoles(@RequestBody Integer id) {
        SysRole role = service.selectRoles(id);
        if(null == role) {
            return null;
        }
        SysRoleVO vo = new SysRoleVO();
        BeanUtils.copyProperties(role,vo);
        return vo;
    }

    @GetMapping("/getRole")
    public List<SysRoleVO> getRole(){
        List<SysRole> list =  service.getRole();
        return BeanCopyUtils.copyList(list,SysRoleVO.class);
    }

}
