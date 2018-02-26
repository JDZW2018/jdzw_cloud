package cn.com.myproject.pay.wxpay;
import cn.com.myproject.pay.entity.VO.PayRefund;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * (支付宝统一退款方法)
 * @author zjp
 * @version  v6.3
 * @since v6.3
 * 2017年6月28日 下午2:43:29
 */
@Component
public class WxpayRefund {


	private static final Logger logger = LoggerFactory.getLogger(WxpayRefund.class);

	/**
	 * 支付宝退款方法实现
	 * @return
	 */
	public static String refundOrder(PayRefund refund ,  WXPayConfig config) {
		WXPay wxpay = null;
		Map<String, String> data = new HashMap<String, String>();
		data.put("transaction_id",refund.getTrade_no());
		data.put("out_refund_no",refund.getOut_request_no());
		data.put("total_fee",new BigDecimal(refund.getTotal_amount()).multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP).toString());
		data.put("refund_fee",new BigDecimal(refund.getRefund_amount()).multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP).toString());
		try {
			wxpay = new WXPay(config, WXPayConstants.SignType.MD5,false);
			Map<String, String> resp = wxpay.refund(data);

			if(resp !=null && resp.containsKey("return_code") && resp.get("return_code").equals("SUCCESS") && resp.get("result_code").equals("SUCCESS")) {
				String refunId = "未知";
				if(resp.containsKey("refund_id_0")) {
					refunId = resp.get("refund_id_0");
				}
				return "success,"+refunId;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error";
	}



}
