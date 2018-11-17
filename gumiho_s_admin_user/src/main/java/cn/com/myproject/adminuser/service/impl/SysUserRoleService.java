package cn.com.myproject.adminuser.service.impl;

import cn.com.myproject.adminuser.dao.BatchSysUserRoleDao;
import cn.com.myproject.adminuser.mapper.SysUserRoleMapper;
import cn.com.myproject.adminuser.po.SysUserRole;
import cn.com.myproject.adminuser.service.ISysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserRoleService implements ISysUserRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private BatchSysUserRoleDao batchSysUserRoleDao;

    @Override
    public void addSysUserRole(SysUserRole sysUserRole) {
        sysUserRoleMapper.addSysUserRole(sysUserRole);
    }

    @Override
    public void updateSysUserRole(SysUserRole sysUserRole) {
        sysUserRoleMapper.updateSysUserRole(sysUserRole);
    }

    @Override
    public List<SysUserRole> findSysUserRoleByUserId(String userId) {
        List<SysUserRole> sysUserRoleList = sysUserRoleMapper.findSysUserRoleByUserId(userId);
        return  sysUserRoleList;
    }

    @Override
    public void deleteSysUserRole(String userId) {
        sysUserRoleMapper.deleteSysUserRole(userId);
    }

    @Override
    public int deleteByRoleId(String roleId) {
        return sysUserRoleMapper.deleteByRoleId(roleId);
    }

    @Override
    public void batchAddSysUserRole(List<SysUserRole> sysUserRoleList) {
        batchSysUserRoleDao.saveBatch(sysUserRoleList);
    }
}
