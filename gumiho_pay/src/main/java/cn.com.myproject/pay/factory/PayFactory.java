package cn.com.myproject.pay.factory;

import cn.com.myproject.pay.IPayService;
import cn.com.myproject.pay.SpringUtil;

public class PayFactory {
    public static IPayService getPay(String type) {
        IPayService payService = (IPayService) SpringUtil.getBean(type);
        return payService;
    }
}
