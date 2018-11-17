package cn.com.myproject.adminuser.externalinterface;

import cn.com.myproject.adminuser.po.SysUserDepartment;
import cn.com.myproject.adminuser.service.impl.SysUserDepartmentService;
import cn.com.myproject.adminuser.vo.SysUserDepartmentVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tianfusheng
 * @date 2018/3/15
 */
@RestController
@RequestMapping("/sysUserDepartment")
public class SysUserDepartmentProvide {
    @Autowired
    private SysUserDepartmentService service;
    @PostMapping("/addSysUserDepartment")
    public  void addSysUserDepartment(@RequestBody SysUserDepartmentVO sysUserDepartmentVO){
        if(null != sysUserDepartmentVO) {
            SysUserDepartment sysUserDepartment = new SysUserDepartment();
            BeanUtils.copyProperties(sysUserDepartmentVO,sysUserDepartment);
            sysUserDepartment.setCreatTime(System.currentTimeMillis());
            sysUserDepartment.setVersion(1);
            sysUserDepartment.setStatus((short)1);
            service.addSysUserDepartment(sysUserDepartment);
        }
    }
    @PostMapping("/deleteByUserId")
    public void deleteByUserId(@RequestBody String userId){
        service.deleteByUserId(userId);
    }

}
