package cn.com.myproject.adminuser.service.impl;


import cn.com.myproject.adminuser.mapper.SysResourceMapper;
import cn.com.myproject.adminuser.po.SysResource;
import cn.com.myproject.adminuser.service.ISysResourceService;
import cn.com.myproject.adminuser.util.ConvertPO2VO;
import cn.com.myproject.adminuser.vo.MenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by liyang-macbook on 2017/7/11.
 */
@Service
public class SysResourceService implements ISysResourceService {

    @Autowired
    private SysResourceMapper sysResourceMapper;

    @Override
    public LinkedHashSet<MenuVO> getMenu(String menuId, String userId) {
        LinkedHashSet<SysResource> set = sysResourceMapper.getMenu(userId);
        if(null == set || set.size() == 0){
            return null;
        }
        LinkedHashSet<MenuVO> _set = new LinkedHashSet<>();
        convet(set,_set,menuId);
        get(_set,userId,menuId);
        return _set;
    }

    @Override
    public List<SysResource> getSysResource(SysResource sysResource) {
        return sysResourceMapper.getSysResource(sysResource);
    }

    @Override
    public List<SysResource> getSysResourceTree(SysResource sysResource, String treeId) {
        List<SysResource> topSysResourceList = new ArrayList<>();
        List<SysResource> sysResourceList = sysResourceMapper.getSysResource(sysResource);
        if(null != sysResourceList && sysResourceList.size() >0 ){
            for(SysResource resource:sysResourceList){
                if(resource.getParentId().compareTo(treeId) == 0){
                    List<SysResource> children = this.getChildren(sysResourceList,resource.getResourceId());
                    resource.setChildren(children);
                    topSysResourceList.add(resource);
                }
            }
        }
        return topSysResourceList;
    }

    @Override
    public SysResource getSysResourceByResourceId(String resourceId) {
        return sysResourceMapper.getSysResourceByResourceId(resourceId);
    }

    @Override
    public int addSysResource(SysResource sysResource) {
        return sysResourceMapper.addSysResource(sysResource);
    }

    @Override
    public int updateSysResource(SysResource sysResource) {
        return sysResourceMapper.updateSysResource(sysResource);
    }

    @Override
    public int deleteSysResource(String resourceId) {
        return sysResourceMapper.deleteSysResource(resourceId);
    }

    private List<SysResource> getChildren(List<SysResource> sysResourceList, String parentid) {
        List<SysResource> children = new ArrayList<SysResource>();
        for (SysResource sysResource : sysResourceList) {
            if (sysResource.getParentId().compareTo(parentid) == 0) {
                sysResource.setChildren(this.getChildren(sysResourceList, sysResource.getResourceId()));
                children.add(sysResource);
            }
        }
        return children;
    }

    private  boolean get(Set<MenuVO> set, String userId, String menuId) {
        boolean b = false;
        for(MenuVO vo : set) {
            LinkedHashSet<SysResource> _set = this.sysResourceMapper.getMenuByResourceId(userId,vo.getId());
            if(null == _set || _set.size() == 0) {
                continue;
            }
            LinkedHashSet<MenuVO> __set = new LinkedHashSet<>();
            if(convet(_set,__set,menuId)) {
                b = true;
                vo.setOpen("open");
            }
            vo.setChilds(__set);
            if(get(__set,userId,menuId)) {
                b = true;
                vo.setOpen("open");
            }
        }

        return b;
    }

    private boolean convet(LinkedHashSet<SysResource> set, LinkedHashSet<MenuVO> _set, String menuId){
        boolean b = false;
        MenuVO vo = null;
        for(SysResource resource : set) {
            vo = new MenuVO();
            ConvertPO2VO.sysResourceToMenuVO(resource,vo);
            if(resource.getResourceId().equals(menuId)) {
                vo.setActive("active");
                b = true;
            }
            _set.add(vo);
        }
        return b;
    }



}
