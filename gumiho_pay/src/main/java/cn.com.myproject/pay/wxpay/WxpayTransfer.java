package cn.com.myproject.pay.wxpay;
import cn.com.myproject.pay.entity.VO.PayTransfer;
import com.github.wxpay.sdk.WXPayConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 
 * (微信转账方)
 * @author LeiJia
 * @version  v1.0
 * @since v1.0
 * 2018年02月06日 上午10:08
 */
@Component
public class WxpayTransfer {


	private static final Logger logger = LoggerFactory.getLogger(WxpayTransfer.class);

	/**
	 * 微信app转账方法实现
	 * @return
	 */
	public static String transfer(PayTransfer transfer,WXPayConfig config) {
		return "error";
	}



}
