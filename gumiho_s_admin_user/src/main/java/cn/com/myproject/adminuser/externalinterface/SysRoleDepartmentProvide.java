package cn.com.myproject.adminuser.externalinterface;

import cn.com.myproject.adminuser.po.SysRoleDepartment;
import cn.com.myproject.adminuser.service.ISysRoleDepartmentService;
import cn.com.myproject.adminuser.vo.SysRoleDepartmentVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tianfusheng
 * @date 2018/3/14
 */
@RestController
@RequestMapping("/SysRoleDepartment")
public class SysRoleDepartmentProvide {
    @Autowired
    private ISysRoleDepartmentService service;

    @PostMapping("/addSysRoleDepartment")
    public void addSysRoleDepartment(@RequestBody SysRoleDepartmentVO sysRoleDepartmentVO){
            if(null != sysRoleDepartmentVO){
                SysRoleDepartment sysRoleDepartment = new SysRoleDepartment();
                BeanUtils.copyProperties(sysRoleDepartmentVO,sysRoleDepartment);
                sysRoleDepartment.setVersion(1);
                sysRoleDepartment.setStatus((short)1);
                sysRoleDepartment.setCreatTime(System.currentTimeMillis());
                service.addSysRoleDepartment(sysRoleDepartment);
            }
    }
    @PostMapping("/deleteByRoleId")
    public  void deleteByRoleId(@RequestBody String roleId){
            service.deleteByRoleId(roleId);
    }

}
