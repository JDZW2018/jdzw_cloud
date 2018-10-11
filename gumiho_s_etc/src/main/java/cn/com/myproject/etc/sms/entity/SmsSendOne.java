package cn.com.myproject.etc.sms.entity;

import java.io.Serializable;

public class SmsSendOne implements Serializable {

    private Integer id;
    private String phoneNum;
    private String signName;
    private String templateCode;
    private String templateParam;
    private Long outId;
    private Integer version;
    private Long createTime;

    private String requestId;
    private String code;
    private String message;
    private String bizId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getTemplateParam() {
        return templateParam;
    }

    public void setTemplateParam(String templateParam) {
        this.templateParam = templateParam;
    }

    public Long getOutId() {
        return outId;
    }

    public void setOutId(Long outId) {
        this.outId = outId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    @Override
    public String toString() {
        return "SmsSendOne{" +
                "id=" + id +
                ", phoneNum='" + phoneNum + '\'' +
                ", signName='" + signName + '\'' +
                ", templateCode='" + templateCode + '\'' +
                ", templateParam='" + templateParam + '\'' +
                ", outId=" + outId +
                ", version=" + version +
                ", createTime=" + createTime +
                ", requestId='" + requestId + '\'' +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", bizId='" + bizId + '\'' +
                '}';
    }
}
