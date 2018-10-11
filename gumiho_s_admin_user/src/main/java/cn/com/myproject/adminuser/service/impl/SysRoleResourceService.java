package cn.com.myproject.adminuser.service.impl;

import cn.com.myproject.adminuser.mapper.SysRoleResourceMapper;
import cn.com.myproject.adminuser.po.SysRoleResource;
import cn.com.myproject.adminuser.service.ISysRoleResourceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysRoleResourceService implements ISysRoleResourceService {

    @Autowired
    private SysRoleResourceMapper sysRoleResourceMapper;

    @Override
    public List<SysRoleResource> getSysRoleResource(SysRoleResource sysRoleResource) {
        return sysRoleResourceMapper.getSysRoleResource(sysRoleResource);
    }

    @Override
    public List<SysRoleResource> getListByResourceIds(String resourceIds) {
        return sysRoleResourceMapper.getListByResourceIds(resourceIds);
    }

    @Override
    public void batchInsert(List<SysRoleResource> sysRoleResources) {
        sysRoleResourceMapper.batchInsert(sysRoleResources);
    }

    @Override
    public void batchDelete(List<SysRoleResource> sysRoleResources) {
        sysRoleResourceMapper.batchDelete(sysRoleResources);
    }

    @Override
    @Transactional
    public boolean batchEditSysRoleResource(String resourceIds,String roleId){
        boolean sign = false;
        if(StringUtils.isNotBlank(roleId)){

            sysRoleResourceMapper.deleteByRoleId(roleId);

            if(StringUtils.isNotBlank(resourceIds)){
                String[]  rids = resourceIds.split(",");
                List<SysRoleResource> addRoleResouces = new ArrayList<>();
                for(String str:rids){
                    if(StringUtils.isNotBlank(str)){
                        SysRoleResource sysRoleResource = new SysRoleResource();
                        sysRoleResource.setRoleId(roleId);
                        sysRoleResource.setResourceId(str);
                        sysRoleResource.setCreateTime(System.currentTimeMillis());
                        sysRoleResource.setStatus((short)1);
                        sysRoleResource.setVersion(1);
                        addRoleResouces.add(sysRoleResource);
                    }
                }
                sysRoleResourceMapper.batchInsert(addRoleResouces);
            }

            sign = true;
        }
        return sign;
    }

}
