package cn.com.myproject.etc.push.entity;

/**
 * @author Tianfusheng
 * @date 2018/5/3 19:29
 * @desc
 **/
public class PushInfo {
    private Integer id;

    private Long outId;

    private Short messageStatus;

    private Integer version;

    private Long createTime;

    private String title;

    private String target;

    private String targetValue;

    private String deviceType;

    private String pushType;

    private String extParameters;

    private String body;

    private String apps;

    public String getApps() {
        return apps;
    }

    public void setApps(String apps) {
        this.apps = apps;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getOutId() {
        return outId;
    }

    public void setOutId(Long outId) {
        this.outId = outId;
    }

    public Short getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(Short messageStatus) {
        this.messageStatus = messageStatus;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target == null ? null : target.trim();
    }

    public String getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(String targetValue) {
        this.targetValue = targetValue == null ? null : targetValue.trim();
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType == null ? null : deviceType.trim();
    }

    public String getPushType() {
        return pushType;
    }

    public void setPushType(String pushType) {
        this.pushType = pushType == null ? null : pushType.trim();
    }

    public String getExtParameters() {
        return extParameters;
    }

    public void setExtParameters(String extParameters) {
        this.extParameters = extParameters == null ? null : extParameters.trim();
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body == null ? null : body.trim();
    }

    @Override
    public String toString() {
        return "PushInfo{" +
                "id=" + id +
                ", outId=" + outId +
                ", messageStatus=" + messageStatus +
                ", version=" + version +
                ", createTime=" + createTime +
                ", title='" + title + '\'' +
                ", target='" + target + '\'' +
                ", targetValue='" + targetValue + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", pushType='" + pushType + '\'' +
                ", extParameters='" + extParameters + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}