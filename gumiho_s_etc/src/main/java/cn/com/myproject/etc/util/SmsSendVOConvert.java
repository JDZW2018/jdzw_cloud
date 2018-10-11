package cn.com.myproject.etc.util;

import cn.com.myproject.aliyun.sms.VO.SmsSendVO;
import cn.com.myproject.etc.sms.entity.SmsSendOne;
import cn.com.myproject.etc.sms.prop.InitProp;

public class SmsSendVOConvert {

    public static SmsSendOne convertOne(SmsSendVO vo) {
        if(null == vo) {
            return null;
        }
        SmsSendOne one  = new SmsSendOne();
        one.setPhoneNum(vo.getPhoneNum());
        one.setSignName(InitProp.singName);
        one.setTemplateCode(vo.getSmsType().getCode());
        one.setTemplateParam(vo.getTemplateParam());
        one.setVersion(1);
        one.setOutId(Long.valueOf(vo.getOutId()));
        one.setCreateTime(System.currentTimeMillis());
        return one;
    }
}
