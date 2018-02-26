package cn.com.myproject.adminuser.service;


import cn.com.myproject.adminuser.po.SysUser;
import cn.com.myproject.adminuser.po.SysUserRole;
import cn.com.myproject.adminuser.vo.SysUserVO;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Optional;


/**
 *
 */
public interface ISysUserService {

    SysUserVO getByLoginName(String loginName);

    SysUserVO getByUserName(String userName);

    List<SysUser> getAll();

    PageInfo<SysUserVO> getPage(int pageNum, int pageSize, String keyword);

    void addUsers(SysUser sysUser, List<SysUserRole> sysUserRoleList);

    void updateUsers(SysUser sysUser, List<SysUserRole> sysUserRoleList);

    void delusers(Integer id);

    SysUser findByUserId(String userId);

    List<SysUser> findAll();

    Integer checkUsers(String userName);

    SysUser selectUsers(Integer id);

    SysUser getSysUserByLoginName(String loginName);

    SysUser getSysUserByUserName(String userName);



}