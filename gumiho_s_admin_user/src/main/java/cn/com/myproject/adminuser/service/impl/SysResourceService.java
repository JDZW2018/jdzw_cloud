package cn.com.myproject.adminuser.service.impl;


import cn.com.myproject.adminuser.mapper.SysResourceMapper;
import cn.com.myproject.adminuser.mapper.SysRoleResourceMapper;
import cn.com.myproject.adminuser.mapper.SysUserMapper;
import cn.com.myproject.adminuser.po.SysResource;
import cn.com.myproject.adminuser.service.ISysResourceService;
import cn.com.myproject.adminuser.util.ConvertPO2VO;
import cn.com.myproject.adminuser.vo.MenuVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private SysRoleResourceMapper sysRoleResourceMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Override
    public LinkedHashSet<MenuVO> getMenu(String menuId, String loginName ,String str) {
        String userId = sysUserMapper.selectByLoginName(loginName).getUserId();
        LinkedHashSet<SysResource> sets = sysResourceMapper.getMenu(userId);
        if(null == sets || sets.size() == 0){
            return null;
        }
        LinkedHashSet<SysResource> set = new LinkedHashSet<>();
        for(SysResource sysResource:sets){
           if(StringUtils.contains(sysResource.getValue(),str)){
               set.add(sysResource);
           }
        }

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

    /**
     * 级联删除关联信息
     * @param resourceId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteSysResource(String resourceId) {
        sysRoleResourceMapper.deleteByResourceId();
        int i = sysResourceMapper.deleteSysResource(resourceId);
        return i;
    }

    @Override
    public Integer checkValue(String value) {
        return sysResourceMapper.checkValue(value);
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
          /*  for (SysResource re: _set){
               // System.out.println("/"+);
                re.setValue("/"+StringUtils.substringAfter(StringUtils.substringAfter(re.getValue(),"/"),"/"));
            }*/
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
