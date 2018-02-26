package cn.com.myproject.adminuser.po;


import cn.com.myproject.util.BasePO;

/**
 *
 */
public class SysRoleResource extends BasePO {

    private String resourceId;
    private String roleId;

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
