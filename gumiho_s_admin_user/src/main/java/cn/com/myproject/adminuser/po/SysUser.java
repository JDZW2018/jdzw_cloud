package cn.com.myproject.adminuser.po;





import cn.com.myproject.util.po.BasePO;

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
    private String roleName;
    private String departmentId;
    private String departmentName;
    private Short recycleBinStatus;
    private String allinpayUserId;

    private List<SysUserRole> sysUserRoleList;

    public String getAllinpayUserId() {
        return allinpayUserId;
    }

    public void setAllinpayUserId(String allinpayUserId) {
        this.allinpayUserId = allinpayUserId;
    }

    public Short getRecycleBinStatus() {
        return recycleBinStatus;
    }

    public void setRecycleBinStatus(Short recycleBinStatus) {
        this.recycleBinStatus = recycleBinStatus;
    }

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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
