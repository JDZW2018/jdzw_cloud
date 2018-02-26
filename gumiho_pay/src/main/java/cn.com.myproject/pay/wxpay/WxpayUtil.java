package cn.com.myproject.pay.wxpay;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * (微信统一校验查询方法)
 * @author LeiJia
 * @version  v1.0
 * @since v1.0
 * 2018年02月06日 上午10:08
 */
@Component
public class WxpayUtil {


	private static final Logger logger = LoggerFactory.getLogger(WxpayUtil.class);

	/**
	 * 微信支付校验方法实现
	 * @return
	 */
	public static boolean check(Map<String, String> map, WXPayConfig config) {
		WXPay wxpay = new WXPay(config);
		try {
			if (wxpay.isPayResultNotifySignatureValid(map)  && map.get("result_code").equals("SUCCESS") ) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
     * 微信订单查询方法
	 * @param out_trade_no
     * @return
     */
	public static Map<String, String> orderQuery(String out_trade_no,WXPayConfig config){
		WXPay wxpay = new WXPay(config, WXPayConstants.SignType.MD5,false);
		Map<String, String> data = new HashMap<String, String>();
		data.put("out_trade_no", out_trade_no);

		try {
			Map<String, String> resp = wxpay.orderQuery(data);
			System.out.println(resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;

	}



}
