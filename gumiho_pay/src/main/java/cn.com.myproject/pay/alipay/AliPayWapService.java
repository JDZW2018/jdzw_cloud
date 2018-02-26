package cn.com.myproject.pay.alipay;

import cn.com.myproject.pay.IPayService;
import cn.com.myproject.pay.entity.VO.PayOrder;
import cn.com.myproject.pay.entity.VO.PayRefund;
import cn.com.myproject.pay.entity.VO.PayTransfer;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayResponse;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by liyang-macbook on 2017/10/10.
 */
@Service
public class AliPayWapService extends AbstractAliPayService implements IPayService {

    private static final Logger logger = LoggerFactory.getLogger(AliPayService.class);


    private AliPayProp aliPayProp;

    public AliPayWapService(){}
    @Autowired
    public AliPayWapService(AliPayProp aliPayProp) {
        this.aliPayProp = aliPayProp;
    }

    @Override
    public Map<String, String> payOrder(PayOrder order) {
        // 商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = order.getOut_trade_no();
        // 订单名称，必填
        String subject = order.getSubject();

        // 付款金额，必填
        String total_amount = order.getTotal_amount();
        // 商品描述，可空
        String body = order.getBody();
        // 超时时间 可空
        String timeout_express = order.getTimeout_express();
        // 销售产品码 必填
        String product_code="QUICK_WAP_PAY";
        /**********************/
        // SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签
        //调用RSA签名方式
        AlipayClient client = new DefaultAlipayClient(aliPayProp.getGatewayUrl(), aliPayProp.getAppId(), aliPayProp.getAppPrivateKey(),
                "json", "utf-8", aliPayProp.getAlipayPublicKey(), "RSA2");
        AlipayTradeWapPayRequest alipay_request=new AlipayTradeWapPayRequest();

        // 封装请求支付信息
        AlipayTradeWapPayModel model=new AlipayTradeWapPayModel();
        model.setOutTradeNo(out_trade_no);
        model.setSubject(body);
        model.setTotalAmount(total_amount);
        model.setBody(body);
        model.setTimeoutExpress(timeout_express);
        model.setProductCode(product_code);
        alipay_request.setBizModel(model);
        // 设置异步通知地址
        alipay_request.setNotifyUrl(aliPayProp.getNotifyUrl());
        // 设置同步地址
        alipay_request.setReturnUrl(order.getReturnUrl());

        Map<String,String> map = new LinkedHashMap<>();
        // form表单生产
        String form = "";
        try {
            // 调用SDK生成表单
            AlipayResponse response = client.pageExecute(alipay_request);
            form = response.getBody();
            map.put("sign",form);
            return map;
        } catch (AlipayApiException e) {
            logger.error("调用支付宝h5支付出错",e);
            e.printStackTrace();

        }
        return null;
    }
    @Override
    public Map<String, String> orderQuery(String out_trade_no) {
        return AlipayUtil.orderQuery(out_trade_no);
    }

}
