package cn.com.myproject.aliyun.push.prop;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 */
@ConfigurationProperties(prefix = "aliyun.push")
public class AliyunPushProp {

    private String regionId = "cn-hangzhou";

    private Long appKey;
    private String appKeyId;
    private String appSecret;

    //iOS的通知是通过APNs中心来发送的，需要填写对应的环境信息。"DEV" : 表示开发环境 "PRODUCT" : 表示生产环境
    private String iOSApnsEnv = "PRODUCT";


    public Long getAppKey() {
        return appKey;
    }

    public void setAppKey(Long appKey) {
        this.appKey = appKey;
    }

    public String getAppKeyId() {
        return appKeyId;
    }

    public void setAppKeyId(String appKeyId) {
        this.appKeyId = appKeyId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getiOSApnsEnv() {
        return iOSApnsEnv;
    }

    public void setiOSApnsEnv(String iOSApnsEnv) {
        this.iOSApnsEnv = iOSApnsEnv;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }
}
