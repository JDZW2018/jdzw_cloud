package cn.com.myproject.adminuser.service;


import cn.com.myproject.adminuser.po.SysUser;
import cn.com.myproject.adminuser.vo.SysUserVO;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface ISysUserService {

    SysUserVO getByLoginName(String loginName);

    SysUserVO getByPhone(String phone);

    SysUserVO getByUserName(String userName);

    List<SysUser> getAll();

    PageInfo<SysUserVO> getPage(int pageNum, int pageSize, String keyword, String departmentId);

    PageInfo<SysUserVO> userBlacklist(int pageNum, int pageSize, String keyword, String departmentId);

    PageInfo<SysUserVO> recycleBinUserList(int pageNum, int pageSize, String keyword, String departmentId);


    void addUsers(SysUser sysUser);

    void updateUsers(SysUser sysUser);

    void delusers(String userId) throws Exception;

    SysUser findByUserId(String userId);

    List<SysUser> findAll();

    Integer checkUsers(String userName);

    SysUser selectUsers(Integer id);

    SysUser getSysUserByLoginName(String loginName);

    SysUser getSysUserByUserName(String userName);

    Integer checkUsersByPhone(String phone);

    Integer checkUsersByLoginName(String loginName);

    /**
     * checkUsersForMerchant
     * @param phone
     * @return
     */
    Integer checkUsersForMerchant(String phone);

    void updateRecycleBinStatus(String[] userIds, Short recycleBinStatus) throws Exception;

    PageInfo<SysUserVO> selectStaffByShopId(String shopId);
}