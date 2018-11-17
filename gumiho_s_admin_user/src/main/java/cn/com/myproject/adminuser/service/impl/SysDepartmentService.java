package cn.com.myproject.adminuser.service.impl;

import cn.com.myproject.adminuser.mapper.SysDepartmentMapper;
import cn.com.myproject.adminuser.po.SysDepartment;
import cn.com.myproject.adminuser.service.ISysDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tianfusheng
 * @date 2018/3/13
 */
@Service
public class SysDepartmentService  implements ISysDepartmentService{
    @Autowired
    private SysDepartmentMapper sysDepartmentMapper;
    @Override
    public int addDepartments(SysDepartment sysDepartment) {
        return sysDepartmentMapper.insert(sysDepartment);
    }

    @Override
    public SysDepartment selectDepartment(Integer id) {
        return sysDepartmentMapper.selectById(id);
    }

    @Override
    public int delDepartments(Integer id) {
        return sysDepartmentMapper.deleteById(id);
    }

    @Override
    public List<SysDepartment> selectAll() {
        return sysDepartmentMapper.selectAll();
    }

    @Override
    public SysDepartment selectByDepartmentId(String departmentId) {
        return sysDepartmentMapper.selectByDepartmentId(departmentId);
    }

    @Override
    public int updateDepartment(SysDepartment sysDepartment) {
        return sysDepartmentMapper.updateByIdSelective(sysDepartment);
    }

    @Override
    public SysDepartment selectByUserId(String userId) {
        return sysDepartmentMapper.selectByUserId(userId);
    }
}
