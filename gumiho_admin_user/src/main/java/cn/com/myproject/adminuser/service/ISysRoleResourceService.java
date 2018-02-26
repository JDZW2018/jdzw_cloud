package cn.com.myproject.adminuser.service;


import cn.com.myproject.adminuser.po.SysRoleResource;

import java.util.List;

public interface ISysRoleResourceService {

    List<SysRoleResource> getSysRoleResource(SysRoleResource sysRoleResource);

    List<SysRoleResource> getListByResourceIds(String resourceIds);

    void batchInsert(List<SysRoleResource> sysRoleResources);

    void batchDelete(List<SysRoleResource> sysRoleResources);

    boolean batchEditSysRoleResource(String resourceIds, String roleId);

}
