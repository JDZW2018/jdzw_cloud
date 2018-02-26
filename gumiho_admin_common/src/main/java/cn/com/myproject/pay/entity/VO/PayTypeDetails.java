package cn.com.myproject.pay.entity.VO;


/**
 * Created by liyang-macbook on 2017/9/19.
 */
public enum PayTypeDetails {

    ALIPAYMOBILE("alipayMobile","支付宝app支付"),

    WECHATMOBILE("wechatMobile","微信app支付"),

    ALIPAYWAP("alipaywap","支付宝H5支付"),

    WECHATWAP("wechatwap","微信H5支付"),

    WECHATG("wechatg","微信公众号支付");

    private String en;
    private String zh;
    private PayTypeDetails(String en, String zh) {
        this.en = en;
        this.zh = zh;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getZh() {
        return zh;
    }

    public void setZh(String zh) {
        this.zh = zh;
    }

    public static PayTypeDetails getByEn(String en) {
        switch (en) {
            case "alipayMobile" : return ALIPAYMOBILE;
            case "wechatMobile" : return WECHATMOBILE;
            case "alipaywap" : return ALIPAYWAP;
            case "wechatwap" : return WECHATWAP;
            case "wechatg" : return WECHATG;
            default: return ALIPAYMOBILE;
        }

    }
}
