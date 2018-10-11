package cn.com.myproject.adminuser.mapper;

import cn.com.myproject.adminuser.po.SysUserDepartment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Tianfusheng
 * @date 2018/3/16 16:28
 * @desc
 **/
@Mapper
public interface SysUserDepartmentMapper {
    int deleteById(Integer id);

    int deleteByUserId(String userId);

    int insert(SysUserDepartment sysUserDepartment);

    int insertSelective(SysUserDepartment sysUserDepartment);

    SysUserDepartment selectById(Integer id);

    int updateSelective(SysUserDepartment sysUserDepartment);

    int update(SysUserDepartment sysUserDepartment);

    List<SysUserDepartment> selectByDepartmentId(String departmentId);

    SysUserDepartment selectByUserId(String userId);

    void updateStatus(@Param("userIds") String[] userIds,
                      @Param("status") short status);
}