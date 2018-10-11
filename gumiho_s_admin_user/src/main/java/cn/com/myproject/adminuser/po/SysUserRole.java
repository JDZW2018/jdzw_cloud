package cn.com.myproject.adminuser.po;


import cn.com.myproject.util.po.BasePO;

/**
 *
 */
public class SysUserRole extends BasePO {
    private String userId;
    private String roleId;

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
}
