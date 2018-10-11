package cn.com.myproject.adminuser.service.impl;


import cn.com.myproject.adminuser.mapper.SysRegionMapper;
import cn.com.myproject.adminuser.po.SysRegion;
import cn.com.myproject.adminuser.service.ISysRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class SysRegionService implements ISysRegionService {

    @Autowired
    private SysRegionMapper sysRegionMapper;

    @Override
    public int deleteByPrimaryKey(String regionId) {
        return sysRegionMapper.deleteByPrimaryKey(regionId);
    }

    @Override
    public int insert(SysRegion record) {
        record.setCreateTime(System.currentTimeMillis());
        record.setVersion(1);
        record.setStatus((short)1);
        return sysRegionMapper.insert(record);
    }

    @Override
    public int insertSelective(SysRegion record) {
        record.setCreateTime(System.currentTimeMillis());
        record.setVersion(1);
        record.setStatus((short)1);
        return sysRegionMapper.insertSelective(record);
    }

    @Override
    public SysRegion selectByPrimaryKey(String regionId) {
        return sysRegionMapper.selectByPrimaryKey(regionId);
    }

    @Override
    public List<SysRegion> selectByPid(String pid) {
        return sysRegionMapper.selectByPid(pid);
    }

    @Override
    public int updateByPrimaryKeySelective(SysRegion record) {
        return sysRegionMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SysRegion record) {
        return sysRegionMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<SysRegion> select_countries_code() {
        return sysRegionMapper.select_countries_code();
    }
}
