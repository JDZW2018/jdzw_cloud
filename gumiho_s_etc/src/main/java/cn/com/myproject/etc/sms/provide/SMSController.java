package cn.com.myproject.etc.sms.provide;

import cn.com.myproject.aliyun.sms.VO.SmsSendVO;
import cn.com.myproject.aliyun.sms.VO.SmsType;
import cn.com.myproject.etc.factory.IDSingleton;
import cn.com.myproject.etc.sms.mq.MyBean;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liyang-macbook on 2016/12/12.
 */
@RestController
@RequestMapping("/sms")
public class SMSController {

    @Autowired
    private MyBean myBean;

    @GetMapping("/add")
    public String add(String routingKey,String phoneNum){

        SmsSendVO vo = new SmsSendVO();
        vo.setOutId(IDSingleton.getInstance().nextId()+"");
        vo.setPhoneNum(phoneNum);
        vo.setSmsType(SmsType.SMS_128060044);
        vo.setTemplateParam("{\"code\":\"123456\"}");
        myBean.sendMessage(routingKey, vo);
        return "ok";
    }
    @GetMapping("/addr")
    public String add1(String routingKey,String phoneNum){

        SmsSendVO vo = new SmsSendVO();
        vo.setOutId(IDSingleton.getInstance().nextId()+"");
        vo.setPhoneNum(phoneNum);
        vo.setSmsType(SmsType.SMS_128060044);
        vo.setTemplateParam("{\"code\":\"123456\"}");
        myBean.sendAndReceiveMessage(routingKey, vo);
        return "ok";
    }

    /**
     *
     * @param routingKey 默认为 smsone.*
     * @param phone 手机号
     * @param  smsType 发送短信的模版
     * @param templateParam json 需要与 smsType中的参数对应。例如：{"code":"123456"}
     * @return
     */
    @GetMapping("/sendSMS")
    public Map<String ,Object> sendSMS(String routingKey, String phone, String templateParam , SmsType smsType){
        Map<String,Object> map = new HashMap<>();
        if(StringUtils.isBlank(routingKey)||StringUtils.isBlank(phone)||StringUtils.isBlank(templateParam)||null == smsType){
            map.put("status","error");
            map.put("message","参数有误");
            return map;
        }
        SmsSendVO vo = new SmsSendVO();
        vo.setOutId(IDSingleton.getInstance().nextId()+"");
        vo.setPhoneNum(phone);
        vo.setSmsType(smsType);
        vo.setTemplateParam(templateParam);

        boolean b =  myBean.sendMessage(routingKey, vo);
        if(b){
            map.put("status","success");
            map.put("message","发送成功");
            return map;
        }
        map.put("status","error");
        map.put("message","发送失败");
        return map;
    }



}
