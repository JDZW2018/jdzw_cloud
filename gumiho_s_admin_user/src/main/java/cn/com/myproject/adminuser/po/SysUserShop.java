package cn.com.myproject.adminuser.po;

public class SysUserShop {
    private Integer id;

    private String userId;

    private String shopId;

    private Long creatTime;

    private Short status;

    private Integer version;

    private Integer shopType;

    private Short userType;

    public Short getUserType() {
        return userType;
    }

    public void setUserType(Short userType) {
        this.userType = userType;
    }

    public Integer getShopType() {
        return shopType;
    }

    public void setShopType(Integer shopType) {
        this.shopType = shopType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId == null ? null : shopId.trim();
    }

    public Long getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Long creatTime) {
        this.creatTime = creatTime;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "SysUserShop{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", shopId='" + shopId + '\'' +
                ", creatTime=" + creatTime +
                ", status=" + status +
                ", version=" + version +
                ", shopType=" + shopType +
                ", userType=" + userType +
                '}';
    }
}