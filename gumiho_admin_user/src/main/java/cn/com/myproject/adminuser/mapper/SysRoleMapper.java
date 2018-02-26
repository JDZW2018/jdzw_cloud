package cn.com.myproject.adminuser.mapper;


import cn.com.myproject.adminuser.po.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by liyang-macbook on 2017/6/22.
 */
@Mapper
public interface SysRoleMapper {

    List<String> getAllRoleName();

    List<String> getAllRoleId();

    List<String> getAllUseRoleId();

    List<String> getRoleIds(String userId);

    List<SysRole> getPage(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize);

    void addRoles(SysRole sysRole);

    void updateRoles(SysRole sysRole);

    Integer checkRoles(String roleName);

    void delRoles(Integer id);

    SysRole selectRoles(Integer id);

    List<SysRole> getRole();

}
