package cn.com.myproject.adminuser.service.impl;

import cn.com.myproject.adminuser.mapper.SysRoleDepartmentMapper;
import cn.com.myproject.adminuser.po.SysRoleDepartment;
import cn.com.myproject.adminuser.service.ISysRoleDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tianfusheng
 * @date 2018/3/14
 */
@Service
public class SysRoleDepartmentService implements ISysRoleDepartmentService {
    @Autowired
    private SysRoleDepartmentMapper sysRoleDepartmentMapper;
    @Override
    public int addSysRoleDepartment(SysRoleDepartment sysRoleDepartment) {
        return  sysRoleDepartmentMapper.insert(sysRoleDepartment);
    }

    @Override
    public void deleteByRoleId(String roleId) {
         sysRoleDepartmentMapper.deleteByRoleId(roleId);
    }

    @Override
    public List<SysRoleDepartment> selectByDepartmentId(String departmentId) {
        return sysRoleDepartmentMapper.selectByDepartmentId(departmentId);
    }
}
