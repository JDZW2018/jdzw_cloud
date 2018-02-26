package cn.com.myproject.adminuser.service;

import cn.com.myproject.adminuser.po.SysRole;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 *
 */
public interface ISysRoleService {

    List<String> getAllRoleName();

    List<String> getAllRoleId();

    List<String> getAllUseRoleId();

    List<String> getRoleIds(String userId);

    PageInfo<SysRole> getPage(int pageNum, int pageSize);

    void addRoles(SysRole sysRole);

    void updateRoles(SysRole sysRole);

    Integer checkRoles(String roleName);

    void delRoles(Integer id);

    SysRole selectRoles(Integer id);

    List<SysRole> getRole();

}
