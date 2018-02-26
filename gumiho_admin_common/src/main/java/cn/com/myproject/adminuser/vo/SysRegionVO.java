package cn.com.myproject.adminuser.vo;

import java.io.Serializable;

public class SysRegionVO implements Serializable {


    private String regionId;

    private String regionEn;

    private String regionName;

    private String pId;

    private String international;

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId == null ? null : regionId.trim();
    }

    public String getRegionEn() {
        return regionEn;
    }

    public void setRegionEn(String regionEn) {
        this.regionEn = regionEn == null ? null : regionEn.trim();
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName == null ? null : regionName.trim();
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId == null ? null : pId.trim();
    }

    public String getInternational() {
        return international;
    }

    public void setInternational(String international) {
        this.international = international == null ? null : international.trim();
    }
}