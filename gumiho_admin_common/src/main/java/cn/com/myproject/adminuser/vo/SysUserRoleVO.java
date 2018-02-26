package cn.com.myproject.adminuser.vo;


import cn.com.myproject.util.BasePO;

import java.io.Serializable;

/**
 *
 */
public class SysUserRoleVO implements Serializable {
    private String userId;
    private String roleId;

    private Long createTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }


    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
