package cn.com.myproject.adminuser.dao;


import cn.com.myproject.adminuser.po.SysUserRole;

import java.util.List;


public interface BatchSysUserRoleDao {
    void saveBatch(List<SysUserRole> list);
}
