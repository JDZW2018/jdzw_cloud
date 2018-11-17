package cn.com.myproject.adminuser.mapper;

import cn.com.myproject.adminuser.po.SysRoleDepartment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleDepartmentMapper {
    int deleteById(Integer id);

    void deleteByRoleId(String roleId);

    List<SysRoleDepartment> selectByDepartmentId(String departmentId);

    int insert(SysRoleDepartment sysRoleDepartment);

    int insertSelective(SysRoleDepartment sysRoleDepartment);

    SysRoleDepartment selectById(Integer id);

    int updateSelective(SysRoleDepartment sysRoleDepartment);

    int update(SysRoleDepartment sysRoleDepartment);
}