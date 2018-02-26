package cn.com.myproject.adminuser.service.impl;


import cn.com.myproject.adminuser.mapper.SysRoleMapper;
import cn.com.myproject.adminuser.po.SysRole;
import cn.com.myproject.adminuser.service.ISysRoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by JYP on 2017/6/21.
 */
@Service
public class SysRoleService implements ISysRoleService {

    @Autowired
    private SysRoleMapper roleMapper;

    @Override
    public List<String> getAllRoleName() {
        return roleMapper.getAllRoleName();
    }

    @Override
    public List<String> getAllRoleId() {
        return roleMapper.getAllRoleId();
    }

    @Override
    public List<String> getAllUseRoleId() {
        return roleMapper.getAllUseRoleId();
    }

    @Override
    public List<String> getRoleIds(String userId) {
        return roleMapper.getRoleIds(userId);
    }

    @Override
    public PageInfo<SysRole> getPage(int pageNum, int pageSize){
        List<SysRole> list = this.roleMapper.getPage(pageNum,pageSize);
        return  new PageInfo(list);
    }

    @Override
    public void addRoles(SysRole sysRole) {
        roleMapper.addRoles(sysRole);
    }

    @Override
    public void updateRoles(SysRole sysRole) {
        roleMapper.updateRoles(sysRole);
    }

    @Override
    public Integer checkRoles(String roleName) {
        return roleMapper.checkRoles(roleName);
    }

    @Override
    public void delRoles(Integer id) {
        roleMapper.delRoles(id);
    }

    @Override
    public SysRole selectRoles(Integer id) {
        return roleMapper.selectRoles(id);
    }

    @Override
    public List<SysRole> getRole(){
        return roleMapper.getRole();
    };
}
