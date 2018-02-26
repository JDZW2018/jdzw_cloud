package cn.com.myproject.adminuser.mapper;



import cn.com.myproject.adminuser.po.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface SysUserRoleMapper {


    void addSysUserRole(SysUserRole sysUserRole);

    void updateSysUserRole(SysUserRole sysUserRole);

    List<SysUserRole> findSysUserRoleByUserId(String userId);

    void deleteSysUserRole(String userId);

}
