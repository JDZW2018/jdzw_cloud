package cn.com.myproject.adminuser.mapper;



import cn.com.myproject.adminuser.po.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface SysUserMapper {

    SysUser selectById(Long id);

    SysUser selectByLoginName(String loginName);

    SysUser selectByUserName(String userName);

    List<SysUser> getAll(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize);

    List<SysUser> getPage(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize, @Param("keyword") String keyword);

    void addUsers(SysUser sysUser) ;

    void updateUser(SysUser sysUser);

    SysUser findByUserId(String userId);

    List<SysUser>findAll();

    Integer checkUsers(String userName);

    SysUser selectUsers(Integer id);

    void delusers(Integer id);

}
