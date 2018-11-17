package cn.com.myproject.adminuser.service.impl;

import cn.com.myproject.adminuser.po.SysResource;
import cn.com.myproject.adminuser.service.ISysResourceService;
import cn.com.myproject.adminuser.vo.MenuVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedHashSet;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author tianfusheng
 * @date 2018/3/7
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SysResourceServiceTest {
    @Autowired
    private ISysResourceService service;
    @Test
    public void getMenu() {
/*        LinkedHashSet<MenuVO> _set = service.getMenu("11","6804f23b79094806911d45e67fc292f5");

        for(MenuVO key:_set){

        }
        System.out.println(_set.toString());*/
    }
    @Test
    public void getMenu1(){
        LinkedHashSet<MenuVO> set =service.getMenu("11","ddada5845b2e47859664aacc16e74ed6","wau");
        System.out.println(set.toString());
    }

    @Test
    public void getSysResource() {
    }

    @Test
    public void getSysResourceTree() {
        SysResource sysResource = new SysResource();
        List<SysResource> sysResourceArrayList = service.getSysResourceTree(sysResource,"0");
        System.out.println(sysResourceArrayList);
    }

    @Test
    public void getSysResourceByResourceId() {
    }

    @Test
    public void addSysResource() {
    }

    @Test
    public void updateSysResource() {
    }

    @Test
    public void deleteSysResource() {
    }
}
