package cn.com.myproject.adminuser.service;

import cn.com.myproject.adminuser.po.SysUser;
import cn.com.myproject.adminuser.po.SysUserDepartment;
import cn.com.myproject.adminuser.po.SysUserRole;
import cn.com.myproject.adminuser.po.SysUserShop;

import java.util.List;

/**
 * @author tianfusheng
 * @date 2018/3/20
 */
public interface ISysUserShopService {
    int deleteById(Integer id);

    int deleteByUserId(String userId);

    int deleteByShopId(String shopId);

    int insert(SysUserShop sysUserShop);

    int insertSelective(SysUserShop sysUserShop);

    SysUserShop selectById(Integer id);

    SysUserShop selectByUserId(String userId);

    int updateByIdSelective(SysUserShop sysUserShop);

    int updateById(SysUserShop sysUserShop);

    /**
     * addShopUser
     * @param sysUser
     * @param sysUserShop
     * @param sysUserDepartment
     * @param sysUserRoleList
     */
    void addShopUser(SysUser sysUser, SysUserShop sysUserShop, SysUserDepartment sysUserDepartment, List<SysUserRole> sysUserRoleList) ;

    /**
     * addShopStaff
     * @param sysUser
     * @param sysUserShop
     * @param sysUserDepartment
     */
    void addShopStaff(SysUser sysUser, SysUserShop sysUserShop, SysUserDepartment sysUserDepartment) ;

    int checkShopUserNumber(String shopId);

    /**
     * 真删除 商户下的员工
     * @param userId
     * @throws Exception
     */
    void delShopUser(String userId) throws Exception;

    /**
     * selectUserByShopId通过shopId查找商户主体
     * @param shopId
     * @return
     */
    SysUserShop selectUserByShopId(String shopId);

    /**
     * selectStaffByShopId 通过shopId查找属于商户主体的员工
     * @param shopId
     * @return
     */
    List<SysUserShop> selectStaffByShopId(String shopId);


    /**
     * selectByShopId
     * @param shopId
     * @return
     */
    List<SysUserShop> selectByShopId(String shopId);

}
