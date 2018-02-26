package cn.com.myproject.pay.alipay;
import cn.com.myproject.pay.IPayService;
import cn.com.myproject.pay.entity.VO.PayRefund;
import cn.com.myproject.pay.entity.VO.PayTransfer;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by liyang-macbook on 2017/10/10.
 */
public abstract class AbstractAliPayService implements IPayService {

    private static final Logger logger = LoggerFactory.getLogger(AbstractAliPayService.class);

    private AliPayProp aliPayProp;

    public AbstractAliPayService() {

    }
    public AbstractAliPayService(AliPayProp aliPayProp) {
        this.aliPayProp = aliPayProp;
    }

    @Override
    public String refundOrder(PayRefund refund) {
        AlipayClient alipayClient = new DefaultAlipayClient(aliPayProp.getGatewayUrl(),aliPayProp.getAppId(),aliPayProp.getAppPrivateKey(),
                "json","utf-8",aliPayProp.getAlipayPublicKey(),"RSA2");
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        request.setBizContent(new Gson().toJson(refund));
        AlipayTradeRefundResponse response = null;
        try {
            response = alipayClient.execute(request);
            logger.info("code:"+response.getCode());
            logger.info("msg:"+response.getMsg());
            logger.info("sub_code:"+response.getSubCode());
            logger.info("sub_msg:"+response.getSubMsg());
            logger.info("body:"+response.getBody());
            if(response.isSuccess()){
                return "success"+","+response.getOutTradeNo();
            }else {
                return response.getSubCode()+" "+response.getSubMsg();
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
            logger.error("调用退款接口失败",e);
        }
        return "error";

    }



    @Override
    public String transfer(PayTransfer transfer) {

        AlipayClient alipayClient = new DefaultAlipayClient(aliPayProp.getGatewayUrl(),aliPayProp.getAppId(),aliPayProp.getAppPrivateKey(),
                "json","utf-8",aliPayProp.getAlipayPublicKey(),"RSA2");
        AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
        request.setBizContent(new Gson().toJson(transfer));
        AlipayFundTransToaccountTransferResponse response = null;
        try {
            response = alipayClient.execute(request);
            logger.info("code:"+response.getCode());
            logger.info("msg:"+response.getMsg());
            logger.info("sub_code:"+response.getSubCode());
            logger.info("sub_msg:"+response.getSubMsg());
            logger.info("body:"+response.getBody()+"");
            if(response.isSuccess()){
                return "success";
            }
        } catch (AlipayApiException e) {
            logger.error("调用转账接口失败",e);
            e.printStackTrace();
        }
        return "error";

    }

    @Override
    public boolean check(Map<String,String> map) {
        try {
            return AlipaySignature.rsaCheckV1(map, aliPayProp.getAlipayPublicKey(), "utf-8","RSA2");
        } catch (AlipayApiException e) {
            e.printStackTrace();
            logger.error("回调验证错误",e);
            return false;
        }

    }
    @Override
    public Map<String, String> orderQuery(String out_trade_no){
        return null;
    }
}
