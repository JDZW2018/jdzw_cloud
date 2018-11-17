package cn.com.myproject.adminuser.service.impl;


import cn.com.myproject.adminuser.po.SysDepartment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


/**
 * @author tianfusheng
 * @date 2018/3/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SysDepartmentServiceTest {
    @Autowired
    SysDepartmentService service;

    @Test
    public void addDepartments() {
        SysDepartment sysDepartment = new SysDepartment();
        sysDepartment.setId(1);
        sysDepartment.setDepartmentName("1123");
        sysDepartment.setDepartmentId("123333");
        sysDepartment.setVersion(1);
        sysDepartment.setStatus((short)1);
        int i =service.addDepartments(sysDepartment);
        System.out.println(i);
    }

    @Test
    public void selectDepartment() {
        System.out.println(service.selectDepartment(1));
    }

    @Test
    public void delDepartments() {
        service.delDepartments(1);
    }
    @Test
    public void selectAll(){
       List<SysDepartment> list= service.selectAll();
        System.out.println(list.toString());
    }
}