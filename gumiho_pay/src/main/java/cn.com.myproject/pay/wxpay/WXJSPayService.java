package cn.com.myproject.pay.wxpay;

import cn.com.myproject.pay.IPayService;
import cn.com.myproject.pay.entity.VO.PayOrder;
import cn.com.myproject.pay.entity.VO.PayRefund;
import cn.com.myproject.pay.entity.VO.PayTransfer;
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
 * 微信公众号支付
 */
@Service
public class WXJSPayService implements IPayService {
    @Autowired
    private WXJspPayProp wXJspPayProp;

    private static final Logger logger = LoggerFactory.getLogger(WXJSPayService.class);

    @Override
    public Map<String,String> payOrder(PayOrder order) {
        Map<String, String> resultMap=new LinkedHashMap<>();
        WXJspPayConfig config = new WXJspPayConfig();
        WXPay wxpay = null;
        Map<String, String> data = new HashMap<String, String>();
        data.put("body", order.getBody());
        data.put("out_trade_no", order.getOut_trade_no());
        data.put("fee_type", "CNY");
        data.put("total_fee",new BigDecimal(order.getTotal_amount()).multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP).toString()); //元转换为分
        data.put("spbill_create_ip", order.getSpbill_create_ip());
        //回调地址
        data.put("notify_url", wXJspPayProp.getNotifyUrl());
        data.put("trade_type", "JSAPI");

//        获取openId
//        String openId = "";
//        String URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+config.getAppID()+"&secret="+config.getKey()+"&code="+order.getCode()+"&grant_type=authorization_code";
//
//        net.sf.json.JSONObject jsonObject = CommonUtil.httpsRequest(URL, "GET", null);
//        if (null != jsonObject &&   jsonObject.containsKey("openid")) {
//            openId = jsonObject.getString("openid");
//        }else {
//            logger.error("获取公众号openId失败");
//            resultMap.put("return_msg","获取公众号openID出错");
//            resultMap.put("return_code","1000");
//            return resultMap;
//        }

        data.put("openid",order.getCode());

        for(String str:data.keySet()) {
            logger.info(str+",,"+data.get(str));
        }
        try {

            config.setKey(wXJspPayProp.getBusinessSecretKey());//商户号私钥
            wxpay = new WXPay(config, WXPayConstants.SignType.MD5,false);
            Map<String, String> resp = wxpay.unifiedOrder(data);
            if(resp !=null && resp.containsKey("return_code") && resp.get("return_code").equals("SUCCESS")) {
                for(String str:resp.keySet()) {
                    logger.info(str+","+resp.get(str));
                    resultMap.put(str,resp.get(str));
                }
            }else {
                for(String str:resp.keySet()) {
                    logger.info(str+","+resp.get(str));
                    resultMap.put(str,resp.get(str));
                }
                resultMap.put("return_msg",resp.get("return_msg"));
                resultMap.put("return_code",resp.get("return_code"));

            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("调用微信公众号支付接口失败",e);
        }

        return resultMap;
    }

    @Override
    public String refundOrder(PayRefund refund) {
        WXJspPayConfig config = new WXJspPayConfig();
        config.setKey(wXJspPayProp.getBusinessSecretKey());//商户号私钥
        return WxpayRefund.refundOrder(refund,config);
    }

    @Override
    public String transfer(PayTransfer transfer){
        WXJspPayConfig config = new WXJspPayConfig();
        config.setKey(wXJspPayProp.getBusinessSecretKey());//商户号私钥
        return WxpayTransfer.transfer(transfer,config);
    }


    /**
     * 回调验证
     * @param map
     * @return
     */
    @Override
    public boolean check(Map<String, String> map) {
        WXJspPayConfig config = new WXJspPayConfig();
        config.setKey(wXJspPayProp.getBusinessSecretKey()); //商户号私钥
        return  WxpayUtil.check(map,config);
    }

    @Override
    public Map<String, String> orderQuery(String out_trade_no) {
        WXJspPayConfig config = new WXJspPayConfig();
        config.setKey(wXJspPayProp.getBusinessSecretKey());//商户号私钥
        return WxpayUtil.orderQuery(out_trade_no,config);
    }

}
