package cn.com.myproject.pay.entity.VO;

/**
 * 支付订单
 * Created by liyang-macbook on 2017/8/17.
 */
public class PayOrder {
    /**
     * 对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。
     * 是否必填：否
     * 最大长度：128
     * */
    private String body;

    /**
     *  商品的标题/交易标题/订单标题/订单关键字等。
     *  是否必填：是
     *  最大长度：256
     * */
    private String subject;

    /**
     * 商户网站唯一订单号
     * 是否必填：是
     * 最大长度：64
     * */
    private String out_trade_no;

    /**
     * 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。
     * 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
     * 注：若为空，则默认为15d。
     * 是否必填：否
     * 最大长度：6
     * */
    private String timeout_express = "24h";

    /**
     * 订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
     * 是否必填：是
     * 最大长度：64
     * */
    private String total_amount;

    /**
     *商品主类型：0—虚拟类商品，1—实物类商品
     * 注：虚拟类商品不支持使用花呗渠道
     * 是否必填：否
     * 最大长度：2
     * */
    private String goods_type;

    /**
     * 公用回传参数，如果请求时传递了该参数，则返回给商户时会回传该参数。
     * 支付宝会在异步通知时将该参数原样返回。本参数必须进行UrlEncode之后才可以发送给支付宝
     * 是否必填：否
     * 最大长度：512
     * */
    private String passback_params;

    private String spbill_create_ip;

    /**
     * 微信公众号支付参数
     * */
    private String code;


    /**
     * 支付宝支付成功返回路径
     */
    private String returnUrl;

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getTimeout_express() {
        return timeout_express;
    }

    public void setTimeout_express(String timeout_express) {
        this.timeout_express = timeout_express;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getGoods_type() {
        return goods_type;
    }

    public void setGoods_type(String goods_type) {
        this.goods_type = goods_type;
    }

    public String getPassback_params() {
        return passback_params;
    }

    public void setPassback_params(String passback_params) {
        this.passback_params = passback_params;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
