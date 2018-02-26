package cn.com.myproject.adminuser.mapper;


import cn.com.myproject.adminuser.po.SysRoleResource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface SysRoleResourceMapper {

    List<SysRoleResource> getSysRoleResource(SysRoleResource sysRoleResource);

    List<SysRoleResource> getListByResourceIds(String resourceIds);

    void batchInsert(List<SysRoleResource> sysRoleResources);

    void batchDelete(List<SysRoleResource> sysRoleResources);

    int deleteByRoleId(String roleId);

}
