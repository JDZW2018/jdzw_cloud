package cn.com.myproject.adminuser.mapper;

import cn.com.myproject.adminuser.po.SysDepartment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysDepartmentMapper {
    int deleteById(Integer id);

    int insert(SysDepartment record);

    int insertSelective(SysDepartment record);

    SysDepartment selectById(Integer id);

    int updateByIdSelective(SysDepartment record);

    int updateById(SysDepartment record);

    List<SysDepartment> selectAll();

    SysDepartment selectByDepartmentId(String departmentId);

    SysDepartment selectByUserId(String userId);
}