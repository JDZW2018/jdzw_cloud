package cn.com.myproject.adminuser.service.impl;


import cn.com.myproject.adminuser.po.SysUser;
import cn.com.myproject.adminuser.po.SysUserShop;
import cn.com.myproject.adminuser.service.ISysUserService;
import cn.com.myproject.adminuser.vo.SysUserVO;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
public class SysUserService implements ISysUserService {
    private static final Logger logger = LoggerFactory.getLogger(SysUserService.class);
    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysUserDepartmentMapper sysUserDepartmentMapper;


    @Autowired
    private SysUserShopMapper sysUserShopMapper;


    @Override
    public SysUserVO getByLoginName(String loginName) {

        SysUser user =  sysUserMapper.selectByLoginName(loginName);

        SysUserVO vo = null;

        if(null != user){
            vo = new SysUserVO();
            BeanUtils.copyProperties(user,vo);
            SysUserShop sysUserShop=sysUserShopMapper.selectByUserId(user.getUserId());
            if(null != sysUserShop){
                vo.setShopId(sysUserShop.getShopId());
                vo.setShopType(sysUserShop.getShopType());
            }
            vo.setRoleIds(sysRoleMapper.getRoleIds(user.getUserId()));
        }
        return vo;
    }

    @Override
    public SysUserVO getByPhone(String phone) {
        SysUser user = null;
        try {
            user = sysUserMapper.selectByPhone(phone);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SysUserVO vo = null;
        if(null != user){
            vo = new SysUserVO();
            BeanUtils.copyProperties(user,vo);
            SysUserShop sysUserShop=sysUserShopMapper.selectByUserId(user.getUserId());
            if(null != sysUserShop){
                vo.setShopId(sysUserShop.getShopId());
                vo.setShopType(sysUserShop.getShopType());
            }
            vo.setRoleIds(sysRoleMapper.getRoleIds(user.getUserId()));
        }
        return vo;
    }

    @Override
    public SysUserVO getByUserName(String userName) {
        SysUser user =  sysUserMapper.selectByUserName(userName);
        SysUserVO vo = null;
        if(null != user){
            vo = new SysUserVO();
            BeanUtils.copyProperties(user,vo);
            vo.setRoleIds(sysRoleMapper.getRoleIds(user.getUserId()));
        }
        return vo;
    }

    @Override
    public List<SysUser> getAll() {
        return null;
    }

    @Override
    public PageInfo<SysUserVO> getPage(int pageNum, int pageSize, String keyword,String departmentId) {
        List<SysUser> list = this.sysUserMapper.getPage(pageNum,pageSize,keyword,departmentId);
        return convert(list);
    }

    @Override
    public PageInfo<SysUserVO> userBlacklist(int pageNum, int pageSize, String keyword,String departmentId) {
        List<SysUser> list  =  sysUserMapper.userBlacklist(pageNum,pageSize,keyword,departmentId);
        return convert(list);
    }

    @Override
    public PageInfo<SysUserVO> recycleBinUserList(int pageNum, int pageSize, String keyword,String departmentId) {
        List<SysUser> list  = sysUserMapper.recycleBinUserList(pageNum,pageSize,keyword,departmentId);
        return convert(list);
    }


    private PageInfo<SysUserVO> convert(List<SysUser> list) {
        PageInfo<SysUser> info = new PageInfo(list);

        List<SysUser> _list = info.getList();
        info.setList(null);
        List<SysUserVO> __list = new ArrayList<>(10);

        PageInfo<SysUserVO> _info = new PageInfo();
        BeanUtils.copyProperties(info,_info);
        if(null !=_list && _list.size() != 0) {
            SysUserVO vo = null;
            for(SysUser user : _list) {
                vo = new SysUserVO();
                BeanUtils.copyProperties(user,vo);
                __list.add(vo);
            }
            _info.setList(__list);
        }

        return _info;
    }

    @Override
    public void addUsers(SysUser sysUser) {
        sysUserMapper.addUsers(sysUser);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUsers(SysUser sysUser) {
        try {
            sysUserMapper.updateUser(sysUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<SysUser> findAll() {
        List<SysUser> findAllList =sysUserMapper .findAll();
        return findAllList;
    }

    @Override
    public SysUser findByUserId(String userId) {
        SysUser user = sysUserMapper.findByUserId(userId);
        return user;
    }

    @Override
    public Integer checkUsers(String userName) {
        return sysUserMapper.checkUsers(userName);
    }

    @Override
    public SysUser selectUsers(Integer id) {
        return sysUserMapper.selectUsers(id);
    }

    /**
     * @param userId
     * @author Tianfusheng
     * @date 2018/3/16 17:05
     * @desc 完善级联删除方法。
     **/
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delusers(String userId) throws Exception {
        try {
            sysUserRoleMapper.deleteSysUserRole(userId);
            sysUserDepartmentMapper.deleteByUserId(userId);
            sysUserMapper.delusers(userId);
        } catch (Exception e) {
            e.printStackTrace();
            throw  new Exception("删除用户失败");
        }
    }

    @Override
    public SysUser getSysUserByLoginName(String loginName){
        return sysUserMapper.selectByLoginName(loginName);
    }

    @Override
    public SysUser getSysUserByUserName(String userName){
        return sysUserMapper.selectByUserName(userName);
    }

    @Override
    public Integer checkUsersByPhone(String phone) {
        return sysUserMapper.checkUsersByPhone(phone);
    }

    @Override
    public Integer checkUsersByLoginName(String loginName) {
        return sysUserMapper.checkUsersByLoginName(loginName);
    }

    @Override
    public Integer checkUsersForMerchant(String phone) {
        return sysUserMapper.checkUsersForMerchant(phone);
    }

    /**
     * 修改用户回收站状态,主体账户修改,员工帐号级联修改。
     * @param userIds
     * @param recycleBinStatus
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRecycleBinStatus(String[] userIds, Short recycleBinStatus) throws Exception {

        try {
            for(String userId :userIds){
                SysUserShop sysUserShop =  sysUserShopMapper.selectByUserId(userId);
                if(null != sysUserShop){
                    List<SysUserShop> sysUserShopList = sysUserShopMapper.selectByShopId(sysUserShop.getShopId());
                    if (null != sysUserShopList && sysUserShopList.size() != 0) {
                        String[] staffIds = new String[sysUserShopList.size()];
                        int i = 0;
                        for(SysUserShop shop :sysUserShopList){
                            staffIds[i] = shop.getUserId();
                            i++;
                        }
                        sysUserRoleMapper.updateStatus(staffIds,recycleBinStatus);
                        sysUserDepartmentMapper.updateStatus(staffIds,recycleBinStatus);
                        sysUserShopMapper.updateStatus(staffIds,recycleBinStatus);
                        sysUserMapper.updateRecycleBinStatus(staffIds,recycleBinStatus);
                    }
                }
            }
            sysUserRoleMapper.updateStatus(userIds,recycleBinStatus);
            sysUserDepartmentMapper.updateStatus(userIds,recycleBinStatus);
            sysUserMapper.updateRecycleBinStatus(userIds,recycleBinStatus);
        } catch (Exception e) {
            logger.error("[{}]-[{}]时，发生异常，异常信息为[{}]","SysUserService","updateRecycleBinStatus",e.getMessage());
            throw  new Exception("SysUserService-updateRecycleBinStatus方法执行异常");
        }
    }

    @Override
    public PageInfo<SysUserVO> selectStaffByShopId(String shopId) {
        return convert(sysUserMapper.selectStaffByShopId(shopId));
    }

}