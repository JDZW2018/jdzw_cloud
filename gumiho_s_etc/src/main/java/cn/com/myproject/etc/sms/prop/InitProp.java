package cn.com.myproject.etc.sms.prop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class InitProp {

    public static String singName;

    @Value("${aliyun.sms.signName}")
    public void setSingName(String singName) {
        InitProp.singName = singName;
    }
}
