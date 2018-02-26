package cn.com.myproject.pay.alipay;

import cn.com.myproject.pay.IPayService;
import cn.com.myproject.pay.entity.VO.PayOrder;
import cn.com.myproject.pay.entity.VO.PayRefund;
import cn.com.myproject.pay.entity.VO.PayTransfer;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by liyang-macbook on 2017/8/17.
 */
@Service
public class AliPayService extends  AbstractAliPayService implements IPayService {

    private static final Logger logger = LoggerFactory.getLogger(AliPayService.class);


    @Autowired
    private AliPayProp aliPayProp;

    public  AliPayService(){

    }

    @Autowired
    public AliPayService(AliPayProp aliPayProp) {
        this.aliPayProp = aliPayProp;
    }


    @Override
    public Map<String, String> payOrder(PayOrder order) {

        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient(aliPayProp.getGatewayUrl(), aliPayProp.getAppId(), aliPayProp.getAppPrivateKey(),
                "json", "utf-8", aliPayProp.getAlipayPublicKey(), "RSA2");

        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();

        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody(order.getBody());
        model.setSubject(order.getSubject());
        model.setOutTradeNo(order.getOut_trade_no());
        model.setTimeoutExpress(order.getTimeout_express());
        model.setTotalAmount(order.getTotal_amount());
        model.setProductCode("QUICK_MSECURITY_PAY");
        model.setPassbackParams(order.getPassback_params());
        request.setBizModel(model);
        request.setNotifyUrl(aliPayProp.getNotifyUrl());
        Map<String,String> map = new LinkedHashMap<>();
        try {

            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            if(response.isSuccess()) {
                logger.info("code:"+response.getBody());
                map.put("sign",response.getBody());
                return map;
            }
        } catch (AlipayApiException e) {
            logger.error("调用支付宝支付失败",e);
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public Map<String, String> orderQuery(String out_trade_no) {
        return AlipayUtil.orderQuery(out_trade_no);
    }

}
