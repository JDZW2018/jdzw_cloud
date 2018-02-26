package cn.com.myproject.adminuser.po;



import cn.com.myproject.util.BasePO;

import java.util.List;

/**
 *
 */
public class SysUser extends BasePO {
    private String userId;
    private String loginName;
    private String userName;
    private String nickName;
    private String password;
    private String phone;
    private String linkUserId;
    private String roleName;

    private List<SysUserRole> sysUserRoleList;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<SysUserRole> getSysUserRoleList() {
        return sysUserRoleList;
    }

    public void setSysUserRoleList(List<SysUserRole> sysUserRoleList) {
        this.sysUserRoleList = sysUserRoleList;
    }

    public String getLinkUserId() {
        return linkUserId;
    }

    public void setLinkUserId(String linkUserId) {
        this.linkUserId = linkUserId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
