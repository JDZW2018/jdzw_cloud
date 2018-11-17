package cn.com.myproject.adminuser.service;


import cn.com.myproject.adminuser.po.SysResource;
import cn.com.myproject.adminuser.vo.MenuVO;

import java.util.LinkedHashSet;
import java.util.List;

/**
 *
 */
public interface ISysResourceService {

    /**
     *根据用户ID获取用户对应的菜单
     * */
    LinkedHashSet<MenuVO> getMenu(String menuId, String loginName, String str);

    List<SysResource> getSysResource(SysResource sysResource);

    List<SysResource> getSysResourceTree(SysResource sysResource, String treeId);

    SysResource getSysResourceByResourceId(String resourceId);

    int addSysResource(SysResource sysResource);

    int updateSysResource(SysResource sysResource);

    int deleteSysResource(String resourceId);

    Integer checkValue(String value);
}
