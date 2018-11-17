package cn.com.myproject.adminuser.mapper;



import cn.com.myproject.adminuser.po.SysResource;
import org.apache.ibatis.annotations.Mapper;

import java.util.LinkedHashSet;
import java.util.List;

@Mapper
public interface SysResourceMapper {

    List<SysResource> getValueByRoleId(String roleId);

    List<String> getAllValue();

    List<String> getAllUseValue(String type);

    LinkedHashSet<SysResource> getMenu(String userId);

    LinkedHashSet<SysResource> getMenuByResourceId(String userId, String resourceId);

    List<SysResource> getSysResource(SysResource sysResource);

    SysResource getSysResourceByResourceId(String resourceId);

    int addSysResource(SysResource sysResource);

    int updateSysResource(SysResource sysResource);

    int deleteSysResource(String resourceId);

    Integer checkValue(String value);
}
