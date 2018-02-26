package cn.com.myproject.pay.wxpay;

import com.github.wxpay.sdk.WXPayConfig;

import java.io.*;

/**
 * Created by liyang-macbook on 2017/9/7.
 */
public class MyAppWXPayConfig implements WXPayConfig {

    private byte[] certData;

    private String key = "12wsxju76glmbv89fdfghjkloiuytred";

    private String notifyUrl = "http://operation.playlounge.cn/api/paycallback/wxpay";

    public MyAppWXPayConfig() {
//        String certPath = "/data/service/unicorn-trade/cert1/apiclient_cert.p12";
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
        return "wx918e5ceaf6787656";
    }
    @Override
    public String getMchID() {
        return "1489219002";
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
