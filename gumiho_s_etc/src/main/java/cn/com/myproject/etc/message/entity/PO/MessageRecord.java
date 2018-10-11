package cn.com.myproject.etc.message.entity.PO;

import cn.com.myproject.etc.util.BasePO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "MessageRecord", description = "消息记录")
public class MessageRecord extends BasePO {

    @ApiModelProperty(value = "消息Id")
    private String messageId;
    @ApiModelProperty(value = "发送方")
    private String sendUserId;
    @ApiModelProperty(value = "接收方")
    private String receiveUserId;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "简介")
    private String intro;
    @ApiModelProperty(value = "消息内容")
    private String content;
    @ApiModelProperty(value = "类型 1、站内信 2、系统通知")
    private int classify;
    @ApiModelProperty(value = "关联会员（发送方与接收方 各关联一条数据）")
    private String relationId;
    @ApiModelProperty(value = "消息类型（1、用户反馈 2、短信 3、站内信 4、私信）")
    private int relationType;
    @ApiModelProperty(value = "消息状态（0、未读 1、已读  2、已撤回 3、已删除）")
    private int messageStatus;

    public String getAliPushExtParameters() {
        return aliPushExtParameters;
    }

    public void setAliPushExtParameters(String aliPushExtParameters) {
        this.aliPushExtParameters = aliPushExtParameters;
    }

    private String aliPushExtParameters;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(String sendUserId) {
        this.sendUserId = sendUserId;
    }

    public String getReceiveUserId() {
        return receiveUserId;
    }

    public void setReceiveUserId(String receiveUserId) {
        this.receiveUserId = receiveUserId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getClassify() {
        return classify;
    }

    public void setClassify(int classify) {
        this.classify = classify;
    }

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public int getRelationType() {
        return relationType;
    }

    public void setRelationType(int relationType) {
        this.relationType = relationType;
    }

    public int getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(int messageStatus) {
        this.messageStatus = messageStatus;
    }
}