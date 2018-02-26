package cn.com.myproject.pay.wxpay;

import cn.com.myproject.pay.IPayService;
import cn.com.myproject.pay.entity.VO.PayOrder;
import cn.com.myproject.pay.entity.VO.PayRefund;
import cn.com.myproject.pay.entity.VO.PayTransfer;
import cn.com.myproject.util.MD5Util;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * 微信app支付
 */
@Service

public class WXAppPayService implements IPayService {

    @Autowired
    private WXAppPayProp wXAppPayProp;

    private static final Logger logger = LoggerFactory.getLogger(WXAppPayService.class);

    @Override
    public Map<String,String> payOrder(PayOrder order) {
        Map<String, String> resultMap=new LinkedHashMap<>();
        WXAppPayConfig config = new WXAppPayConfig();
        WXPay wxpay = null;
        Map<String, String> data = new HashMap<String, String>();
        data.put("body", order.getBody());
        data.put("out_trade_no", order.getOut_trade_no());
        data.put("fee_type", "CNY");
        data.put("total_fee",new BigDecimal(order.getTotal_amount()).multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP).toString());
        data.put("spbill_create_ip", order.getSpbill_create_ip());
        //回调地址
        data.put("notify_url", wXAppPayProp.getNotifyUrl());

        data.put("trade_type", "APP");

        for(String str:data.keySet()) {
            logger.info(str+",,"+data.get(str));
        }
        try {

            config.setKey(wXAppPayProp.getBusinessSecretKey());
            wxpay = new WXPay(config, WXPayConstants.SignType.MD5,false);
            Map<String, String> resp = wxpay.unifiedOrder(data);
            if(resp !=null && resp.containsKey("return_code") && resp.get("return_code").equals("SUCCESS")) {

                String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
                String nonceStr2 = resp.get("nonce_str");

                resultMap.put("appid",resp.get("appid"));
                resultMap.put("partnerid",resp.get("mch_id"));
                resultMap.put("prepayid",resp.get("prepay_id"));
                resultMap.put("package","Sign=WXPay");
                resultMap.put("noncestr",nonceStr2);
                resultMap.put("timestamp",timestamp);
                String signs = "appid=" + config.getAppID() + "&noncestr=" + nonceStr2 + "&package=Sign=WXPay&partnerid="
                        + resp.get("mch_id").toString() + "&prepayid=" + resp.get("prepay_id").toString() + "&timestamp=" + timestamp + "&key="
                        + config.getKey();
                resultMap.put("sign", MD5Util.MD5Encode(signs, "utf8").toUpperCase());
            }else {
                for(String str:resp.keySet()) {
                    logger.info(str+","+resp.get(str));
                    resultMap.put(str,resp.get("str"));
                }
                resultMap.put("return_msg",resp.get("return_msg"));
                resultMap.put("return_code",resp.get("return_code"));

            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("调用微信APP支付接口失败",e);
        }

        return resultMap;
    }
    @Override
    public String refundOrder(PayRefund refund) {
        WXAppPayConfig config = new WXAppPayConfig();
        config.setKey(wXAppPayProp.getBusinessSecretKey());
        return WxpayRefund.refundOrder(refund,config);
    }

    @Override
    public String transfer(PayTransfer transfer){
        WXAppPayConfig config = new WXAppPayConfig();
        config.setKey(wXAppPayProp.getBusinessSecretKey());
        return WxpayTransfer.transfer(transfer,config);

    }

    @Override
    public boolean check(Map<String, String> map) {
        WXAppPayConfig config = new WXAppPayConfig();
        config.setKey(wXAppPayProp.getBusinessSecretKey());
        return WxpayUtil.check(map,config);
    }

    @Override
    public Map<String, String> orderQuery(String out_trade_no) {

        WXAppPayConfig config = new WXAppPayConfig();
        config.setKey(wXAppPayProp.getBusinessSecretKey());
         return WxpayUtil.orderQuery(out_trade_no,config);
    }
}
