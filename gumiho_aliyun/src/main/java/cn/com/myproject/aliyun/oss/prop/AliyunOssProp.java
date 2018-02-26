package cn.com.myproject.aliyun.oss.prop;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by liyang-macbook on 2017/7/24.
 */
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliyunOssProp {
    /**
     *
     * */
    private String endpoint;

    private String accessKeyId;

    private String accessKeySecret;

    private String bucketName;

    /**
     * 返回url的前缀域名
     * */
    private String returnUrlPrefix;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getReturnUrlPrefix() {
        return returnUrlPrefix;
    }

    public void setReturnUrlPrefix(String returnUrlPrefix) {
        this.returnUrlPrefix = returnUrlPrefix;
    }
}
