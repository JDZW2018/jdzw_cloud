package cn.com.myproject.adminuser.mapper;


import cn.com.myproject.adminuser.po.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserMapper {

    SysUser selectById(Long id);

    SysUser selectByLoginName(String loginName);

    SysUser selectByPhone(String Phone);

    SysUser selectByUserName(String userName);

    List<SysUser> getAll(@Param("pageNumKey") int pageNum,
                         @Param("pageSizeKey") int pageSize);

    List<SysUser> getPage(@Param("pageNumKey") int pageNum,
                          @Param("pageSizeKey") int pageSize,
                          @Param("keyword") String keyword,
                          @Param("departmentId") String departmentId);

    /**
     * 用户黑名单分页查询
     * @param pageNum
     * @param pageSize
     * @param keyword
     * @return
     */
    List<SysUser> userBlacklist(@Param("pageNumKey") int pageNum,
                                @Param("pageSizeKey") int pageSize,
                                @Param("keyword") String keyword,
                                @Param("departmentId") String departmentId);

    /**
     * 回收站用户分页查询
     * @param pageNum
     * @param pageSize
     * @param keyword
     * @return
     */
    List<SysUser> recycleBinUserList(@Param("pageNumKey") int pageNum,
                                     @Param("pageSizeKey") int pageSize,
                                     @Param("keyword") String keyword,
                                     @Param("departmentId") String departmentId);


    void addUsers(SysUser sysUser);

    void updateUser(SysUser sysUser);

    SysUser findByUserId(String userId);

    List<SysUser> findAll();

    Integer checkUsers(String userName);

    /**
     * 查询商户手机号在数据库中是否存在
     * @param phone
     * @return
     */
    Integer checkUsersForMerchant(String phone);

    SysUser selectUsers(Integer id);

    void delusers(String userId);


    /**
     * 检查用户手机号是否重复
     * @param phone
     * @return
     */
    Integer checkUsersByPhone(String phone);

    /**
     * checkUsersByLoginName
     * @param loginName
     * @return
     */
    Integer checkUsersByLoginName(String loginName);


    /**
     * 根据用户id批量删除回收站用户
     *
     * @param userIds
     * @param recycleBinStatus
     */
    void updateRecycleBinStatus(@Param("userIds") String[] userIds,
                                @Param("recycleBinStatus") Short recycleBinStatus);

    /**
     * 查询所有 关联shopd的员工帐号
     * @param shopId
     * @return
     */
    List<SysUser> selectStaffByShopId(String shopId);
}
