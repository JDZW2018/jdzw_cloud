package cn.com.myproject.adminuser.service;

import cn.com.myproject.adminuser.po.SysDepartment;

import java.util.List;

/**
 * @author tianfusheng
 * @date 2018/3/13
 */
public interface ISysDepartmentService {
    int addDepartments(SysDepartment sysDepartment);
    SysDepartment selectDepartment(Integer id);
    int delDepartments(Integer id);
    List<SysDepartment> selectAll();
    SysDepartment selectByDepartmentId(String departmentId);
    int updateDepartment(SysDepartment sysDepartment);

    /**
     * selectByUserId
     * @param userId
     * @return
     */
    SysDepartment selectByUserId(String userId);
}
