package cn.com.myproject.pay.wxpay;

import com.github.wxpay.sdk.WXPayConfig;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Created by liyang-macbook on 2017/9/7.
 */
public class WXAppPayConfig implements WXPayConfig {

    private byte[] certData;

    /**
     * 公众号secret/商户号secret
     */
    private String key ;

    /**
     * 支付通知urls
     */
    private String notifyUrl ="";

    public WXAppPayConfig() {
//        String certPath = "/data/service/unicorn-trade/cert/apiclient_cert.p12";
//        File file = new File(certPath);
//        InputStream certStream = null;
//        try {
//            certStream = new FileInputStream(file);
//            this.certData = new byte[(int) file.length()];
//            certStream.read(this.certData);
//            certStream.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }


    @Override
    public String getAppID() {
        return "wx5a3468977fdf22c8" ;//"wx9f36964f4a86c49b";
    }
    @Override
    public String getMchID() {
        return "1488299762";//"1282175401";
    }
    @Override
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 8000;
    }
    @Override
    public int getHttpReadTimeoutMs() {
        return 10000;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }
}
