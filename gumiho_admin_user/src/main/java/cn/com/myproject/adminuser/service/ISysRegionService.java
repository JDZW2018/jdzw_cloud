package cn.com.myproject.adminuser.service;


import cn.com.myproject.adminuser.po.SysRegion;

import java.util.List;

/**
 *
 */
public interface ISysRegionService {

    int deleteByPrimaryKey(String regionId);

    int insert(SysRegion record);

    int insertSelective(SysRegion record);

    SysRegion selectByPrimaryKey(String regionId);

    List<SysRegion> selectByPid(String pid);

    int updateByPrimaryKeySelective(SysRegion record);

    int updateByPrimaryKey(SysRegion record);

    List<SysRegion> select_countries_code();

}
