package cn.com.myproject.adminuser.mapper;

import cn.com.myproject.adminuser.po.SysUserShop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserShopMapper {
    int deleteById(Integer id);

    int deleteByUserId(String userId);

    int deleteByShopId(String shopId);

    int insert(SysUserShop sysUserShop);

    int insertSelective(SysUserShop sysUserShop);

    SysUserShop selectById(Integer id);

    SysUserShop selectByUserId(String userId);

    /**
     * 查询所有商户
     * @param shopId
     * @return
     */
    List<SysUserShop> selectByShopId(String shopId);

    /**
     * 查询商户主体
     * @param shopId
     * @return
     */
    SysUserShop selectUserByShopId(String shopId);

    /**
     *查询员工
     * @param shopId
     * @return
     */
    List<SysUserShop> selectStaffByShopId(String shopId);

    int updateByIdSelective(SysUserShop sysUserShop);

    int updateById(SysUserShop sysUserShop);

    Integer checkShopUserNumber(String shopId);

    void updateStatus(@Param("userIds") String[] userIds, @Param("status") Short status);
}