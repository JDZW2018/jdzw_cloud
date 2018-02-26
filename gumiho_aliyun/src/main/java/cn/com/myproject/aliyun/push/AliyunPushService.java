package cn.com.myproject.aliyun.push;

import cn.com.myproject.aliyun.push.prop.AliyunPushProp;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.push.model.v20160801.PushRequest;
import com.aliyuncs.push.model.v20160801.PushResponse;
import com.aliyuncs.utils.ParameterHelper;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class AliyunPushService {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AliyunPushService.class);

    private AliyunPushProp aliyunPushProp;

    public AliyunPushService() {

    }

    public AliyunPushService(AliyunPushProp aliyunPushProp) {
        this.aliyunPushProp = aliyunPushProp;
    }





    /**
     * 推送消息
     * @param title 标题
     * @param body 内容
     * @param target 推送目标 DEVICE:根据设备推送    ACCOUNT:根据账号推送    ALIAS:根据别名推送    TAG:根据标签推送    ALL:推送给全部设备
     * @param targetValue 根据Target来设定，多个值使用逗号分隔，最多支持100个，超过100分多次推送。
     * @param pushType 消息类型 MESSAGE NOTICE
     * @return
     */
    public Map<String, String> push(String title, String body,String target,String targetValue,String deviceType,String pushType,String extParameters) {
        Map<String,String> resultMap = new HashMap<>();
        String status = "fail";
        String message = "调用失败";
        IClientProfile profile = DefaultProfile.getProfile(aliyunPushProp.getRegionId(), aliyunPushProp.getAppKeyId(), aliyunPushProp.getAppSecret());
        DefaultAcsClient client = new DefaultAcsClient(profile);
        PushRequest pushRequest = new PushRequest();

        // 推送目标
        pushRequest.setAppKey(aliyunPushProp.getAppKey());
        pushRequest.setTarget(target); //推送目标: DEVICE:按设备推送 ALIAS : 按别名推送 ACCOUNT:按帐号推送  TAG:按标签推送; ALL: 广播推送
        pushRequest.setTargetValue(targetValue); //根据Target来设定，如Target=DEVICE, 则对应的值为 设备id1,设备id2. 多个值使用逗号分隔.(帐号与设备有一次最多100个的限制)

        pushRequest.setPushType(pushType); // 消息类型 MESSAGE NOTICE
        pushRequest.setDeviceType(deviceType); // 设备类型 ANDROID iOS ALL.

        // 推送配置
        pushRequest.setTitle(title == null?"":title); // 消息的标题
        pushRequest.setBody(body); // 消息的内容

        // 推送配置: iOS
        if("IOS".equals(deviceType) || "ALL".equals(deviceType)){
            pushRequest.setIOSBadge(5); // iOS应用图标右上角角标
            pushRequest.setIOSMusic("default"); // iOS通知声音
            pushRequest.setIOSSubtitle("");//iOS10通知副标题的内容
            pushRequest.setIOSNotificationCategory("iOS10 Notification Category");//指定iOS10通知Category
            pushRequest.setIOSMutableContent(true);//是否允许扩展iOS通知内容
            pushRequest.setIOSApnsEnv(aliyunPushProp.getiOSApnsEnv());//iOS的通知是通过APNs中心来发送的，需要填写对应的环境信息。"DEV" : 表示开发环境 "PRODUCT" : 表示生产环境
            pushRequest.setIOSRemind(true); // 消息推送时设备不在线（既与移动推送的服务端的长连接通道不通），则这条推送会做为通知，通过苹果的APNs通道送达一次。注意：离线消息转通知仅适用于生产环境
            pushRequest.setIOSRemindBody("iOSRemindBody");//iOS消息转通知时使用的iOS通知内容，仅当iOSApnsEnv=PRODUCT && iOSRemind为true时有效
            pushRequest.setIOSExtParameters(extParameters); //通知的扩展属性(注意 : 该参数要以json map的格式传入,否则会解析出错)
        }

        // 推送配置: ANDROID
        if("ANDROID".equals(deviceType) || "ALL".equals(deviceType)){
            pushRequest.setAndroidNotifyType("BOTH");//通知的提醒方式 "VIBRATE" : 震动 "SOUND" : 声音 "BOTH" : 声音和震动 NONE : 静音
            pushRequest.setAndroidNotificationBarType(1);//通知栏自定义样式0-100
            pushRequest.setAndroidNotificationBarPriority(1);//通知栏自定义样式0-100
            pushRequest.setAndroidOpenType("ACTIVITY"); //点击通知后动作 "APPLICATION" : 打开应用 "ACTIVITY" : 打开AndroidActivity "URL" : 打开URL "NONE" : 无跳转
            //pushRequest.setAndroidOpenUrl("http://www.aliyun.com"); //Android收到推送后打开对应的url,仅当AndroidOpenType="URL"有效
            //pushRequest.setAndroidActivity("com.alibaba.push2.demo.XiaoMiPushActivity"); // 设定通知打开的activity，仅当AndroidOpenType="Activity"有效
            pushRequest.setAndroidMusic("default"); // Android通知音乐
            pushRequest.setAndroidPopupActivity("com.ali.demo.PopupActivity");//设置该参数后启动辅助弹窗功能, 此处指定通知点击后跳转的Activity（辅助弹窗的前提条件：1. 集成第三方辅助通道；2. StoreOffline参数设为true）
            pushRequest.setAndroidPopupTitle("Popup Title");
            pushRequest.setAndroidPopupBody("Popup Body");
            pushRequest.setAndroidExtParameters(extParameters); //设定通知的扩展属性。(注意 : 该参数要以 json map 的格式传入,否则会解析出错)
        }

        // 推送控制
        Date pushDate = new Date(System.currentTimeMillis()) ; // 30秒之间的时间点, 也可以设置成你指定固定时间
        String pushTime = ParameterHelper.getISO8601Time(pushDate);
        pushRequest.setPushTime(pushTime); // 延后推送。可选，如果不设置表示立即推送
        String expireTime = ParameterHelper.getISO8601Time(new Date(System.currentTimeMillis() + 12 * 3600 * 1000)); // 12小时后消息失效, 不会再发送
        pushRequest.setExpireTime(expireTime);
        pushRequest.setStoreOffline(true); // 离线消息是否保存,若保存, 在推送时候，用户即使不在线，下一次上线则会收到
        PushResponse pushResponse = null;
        try {
            pushResponse = client.getAcsResponse(pushRequest);
            if(null != pushRequest){
                status = "success";
                message = "调用成功";
                logger.info("本次推送返回的requestId:[{}],messageId:[{}]",pushResponse.getRequestId(),pushResponse.getMessageId());
            }
        } catch (ClientException e) {
            logger.error("[{}]时，发生异常，异常信息为[{}]","执行推送服务",e.getMessage());
        }
        resultMap.put("status",status);
        resultMap.put("message",message);
        return resultMap;
    }



}
