package cn.com.myproject.adminuser.service.impl;


import cn.com.myproject.adminuser.mapper.SysRoleDepartmentMapper;
import cn.com.myproject.adminuser.mapper.SysRoleMapper;
import cn.com.myproject.adminuser.mapper.SysUserRoleMapper;
import cn.com.myproject.adminuser.po.SysRole;
import cn.com.myproject.adminuser.service.ISysRoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JYP on 2017/6/21.
 */
@Service
public class SysRoleService implements ISysRoleService {

    @Autowired
    private SysRoleMapper roleMapper;
    @Autowired
    private SysRoleDepartmentMapper roleDepartmentMapper;
    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Override
    public List<String> getAllRoleName() {
        return roleMapper.getAllRoleName();
    }

    @Override
    public List<String> getAllRoleId() {
        return roleMapper.getAllRoleId();
    }

    @Override
    public List<String> getAllUseRoleId() {
        return roleMapper.getAllUseRoleId();
    }

    @Override
    public List<String> getRoleIds(String userId) {
        return roleMapper.getRoleIds(userId);
    }

    @Override
    public PageInfo<SysRole> getPage(int pageNum, int pageSize, String keyword, String departmentId) {
        List<SysRole> list = roleMapper.getPage(pageNum,pageSize,keyword,departmentId);
        return  convert(list);
    }

    /**
     * list转分页对象
     *
     * @param list
     * @return
     */
    private PageInfo<SysRole> convert(List<SysRole> list) {
        PageInfo<SysRole> info = new PageInfo(list);
        List<SysRole> _list = info.getList();
        info.setList(null);
        List<SysRole> __list = new ArrayList<>(10);
        PageInfo<SysRole> _info = new PageInfo();
        BeanUtils.copyProperties(info,_info);
        if(null !=_list && _list.size() != 0) {
            for(SysRole c : _list) {
                __list.add(c);
            }
            _info.setList(__list);
        }
        return _info;
    }

    @Override
    public void addRoles(SysRole sysRole) {
        roleMapper.addRoles(sysRole);
    }

    @Override
    public void updateRoles(SysRole sysRole) {
        roleMapper.updateRoles(sysRole);
    }

    @Override
    public Integer checkRoles(String roleName) {
        return roleMapper.checkRoles(roleName);
    }

    /**
     * @param id 主删除id
     * @author Tianfusheng
     * @date 2018/3/16 16:46
     * @desc 增加完善关联删除
     **/
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delRoles(Integer id) {
        String roleId =   roleMapper.selectRoles(id).getRoleId();
            roleDepartmentMapper.deleteByRoleId(roleId);
            userRoleMapper.deleteByRoleId(roleId);
            roleMapper.delRoles(id);
    }

    @Override
    public SysRole selectRoles(Integer id) {
        return roleMapper.selectRoles(id);
    }

    @Override
    public List<SysRole> getRole(){
        return roleMapper.getRole();
    }

    @Override
    public List<SysRole> selectRolesByDepartmentId(String departmentId) {
        return roleMapper.selectRolesByDepartmentId(departmentId);
    }
}
