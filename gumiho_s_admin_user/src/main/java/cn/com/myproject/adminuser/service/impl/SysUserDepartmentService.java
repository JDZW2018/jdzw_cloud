package cn.com.myproject.adminuser.service.impl;

import cn.com.myproject.adminuser.mapper.SysUserDepartmentMapper;
import cn.com.myproject.adminuser.po.SysUserDepartment;
import cn.com.myproject.adminuser.service.ISysUserDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tianfusheng
 * @date 2018/3/14
 */
@Service
public class SysUserDepartmentService implements ISysUserDepartmentService {
    @Autowired
    private SysUserDepartmentMapper mapper;
    @Override
    public int addSysUserDepartment(SysUserDepartment sysUserDepartment) {
        return mapper.insert(sysUserDepartment);
    }

    @Override
    public int deleteByUserId(String userId) {
        return mapper.deleteByUserId(userId);
    }

    @Override
    public List<SysUserDepartment> selectByDepartmentId(String departmentId) {
        return mapper.selectByDepartmentId(departmentId);
    }

    @Override
    public SysUserDepartment selectByUserId(String userId) {
        return mapper.selectByUserId(userId);
    }
}
