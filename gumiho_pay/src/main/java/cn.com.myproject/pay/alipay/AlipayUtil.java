package cn.com.myproject.pay.alipay;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 
 * (支付宝统一校验查询方法)
 * @author LeiJia
 * @version  v1.0
 * @since v1.0
 * 2018年02月06日 上午10:08
 */
@Component
public class AlipayUtil {


	private static final Logger logger = LoggerFactory.getLogger(AlipayUtil.class);

	/**
	 * 支付宝校验方法实现
	 * @return
	 */
	public static boolean check(Map<String, String> map, AliPayProp aliPayProp) {
		try {
			return AlipaySignature.rsaCheckV1(map, aliPayProp.getAlipayPublicKey(), "utf-8","RSA2");
		} catch (AlipayApiException e) {
			e.printStackTrace();
			logger.error("回调验证错误",e);
			return false;
		}
	}

	/**
	 * 支付宝订单查询方法
	 * @param out_trade_no
	 * @return
	 */
	public static Map<String, String> orderQuery(String out_trade_no){
		return null;
	}



}
