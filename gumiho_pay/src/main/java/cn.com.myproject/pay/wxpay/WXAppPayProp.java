package cn.com.myproject.pay.wxpay;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by liyang-macbook on 2017/8/17.
 */
@Component
@ConfigurationProperties(prefix = "wxAppPay")
public class WXAppPayProp {

    private String secret; //公众号密钥
    private String businessSecretKey; //商户秘钥
    private String notifyUrl;//app支付回调通知路径
    private String wapNotifyUrl; //H5支付回调通知路径

    public String getWapNotifyUrl() {
        return wapNotifyUrl;
    }

    public void setWapNotifyUrl(String wapNotifyUrl) {
        this.wapNotifyUrl = wapNotifyUrl;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getBusinessSecretKey() {
        return businessSecretKey;
    }

    public void setBusinessSecretKey(String businessSecretKey) {
        this.businessSecretKey = businessSecretKey;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }
}
