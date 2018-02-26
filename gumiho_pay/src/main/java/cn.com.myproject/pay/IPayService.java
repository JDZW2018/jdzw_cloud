package cn.com.myproject.pay;


import cn.com.myproject.pay.entity.VO.PayOrder;
import cn.com.myproject.pay.entity.VO.PayRefund;
import cn.com.myproject.pay.entity.VO.PayTransfer;

import java.util.Map;

/**
 * Created by liyang-macbook on 2017/8/17.
 */
public interface IPayService {


    Map<String, String> payOrder(PayOrder order);

    String refundOrder(PayRefund refund);

    String transfer(PayTransfer transfer);

    boolean check(Map<String, String> map);

    Map<String, String> orderQuery(String out_trade_no);

}
