package cn.com.myproject.adminuser.service.impl;

import cn.com.myproject.adminuser.dao.BatchSysUserRoleDao;
import cn.com.myproject.adminuser.service.ISysUserShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author tianfusheng
 * @date 2018/3/20
 */
@Service
public class SysUserShopService implements ISysUserShopService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SysUserShopService.class);
    @Autowired
    private SysUserShopMapper sysUserShopMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserDepartmentMapper sysUserDepartmentMapper;

    @Autowired
    private SysRoleDepartmentMapper sysRoleDepartmentMapper;

    @Autowired
    private BatchSysUserRoleDao batchSysUserRoleDao;

    @Autowired
    private SysUserService sysUserService;

    @Override
    public int deleteById(Integer id) {
        return sysUserShopMapper.deleteById(id);
    }

    @Override
    public int deleteByUserId(String userId) {
        return sysUserShopMapper.deleteByUserId(userId);
    }

    @Override
    public int deleteByShopId(String shopId) {
        return sysUserShopMapper.deleteByShopId(shopId);
    }

    @Override
    public int insert(SysUserShop sysUserShop) {
        return sysUserShopMapper.insert(sysUserShop);
    }

    @Override
    public int insertSelective(SysUserShop sysUserShop) {
        return sysUserShopMapper.insertSelective(sysUserShop);
    }

    @Override
    public SysUserShop selectById(Integer id) {
        return sysUserShopMapper.selectById(id);
    }

    @Override
    public SysUserShop selectByUserId(String userId) {
        return sysUserShopMapper.selectByUserId(userId);
    }


    @Override
    public int updateByIdSelective(SysUserShop sysUserShop) {
        return sysUserShopMapper.updateByIdSelective(sysUserShop);
    }

    @Override
    public int updateById(SysUserShop sysUserShop) {
        return sysUserShopMapper.updateById(sysUserShop);
    }

    /**
     * 建立用户关系
     *
     * @param sysUser
     * @param sysUserShop
     * @param sysUserDepartment
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addShopUser(SysUser sysUser, SysUserShop sysUserShop, SysUserDepartment sysUserDepartment, List<SysUserRole> sysUserRoleList) {
        batchSysUserRoleDao.saveBatch(sysUserRoleList);
        sysUserMapper.addUsers(sysUser);
        sysUserShopMapper.insert(sysUserShop);
        sysUserDepartmentMapper.insert(sysUserDepartment);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addShopStaff(SysUser sysUser, SysUserShop sysUserShop, SysUserDepartment sysUserDepartment) {
        sysUserMapper.addUsers(sysUser);
        sysUserShopMapper.insert(sysUserShop);
        sysUserDepartmentMapper.insert(sysUserDepartment);
    }

    @Override
    public int checkShopUserNumber(String shopId) {
        return sysUserShopMapper.checkShopUserNumber(shopId);
    }

    /**
     * 级联更新User以及关联表的状态，假删除
     *
     * @param userId
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delShopUser(String userId) throws Exception {

        try {
            sysUserShopMapper.deleteByUserId(userId);
            sysUserService.delusers(userId);
        } catch (Exception e) {
            LOGGER.error("[{}]-[{}]时，发生异常，异常信息为[{}]", "SysUserShopService", "delShopUser", e.getMessage());
            throw new Exception("delShopUser执行失败,已经回滚.userId:"+userId);
        }
    }

    @Override
    public SysUserShop selectUserByShopId(String shopId) {
        SysUserShop sysUserShop = new SysUserShop();
        try {
            sysUserShop = sysUserShopMapper.selectUserByShopId(shopId);
        }catch (Exception e) {
            LOGGER.error("[{}]-[{}]时，发生异常，异常信息为[{}]", "SysUserShopService", "selectUserByShopId", "查询的商户主体重复");
            e.printStackTrace();
        }
        return sysUserShop;
    }

    @Override
    public List<SysUserShop> selectStaffByShopId(String shopId) {
        return sysUserShopMapper.selectStaffByShopId(shopId);
    }

    @Override
    public List<SysUserShop> selectByShopId(String shopId) {
        return sysUserShopMapper.selectByShopId(shopId);
    }
}
