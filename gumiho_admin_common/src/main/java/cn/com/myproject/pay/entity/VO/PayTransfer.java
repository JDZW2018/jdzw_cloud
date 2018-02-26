package cn.com.myproject.pay.entity.VO;

import java.io.Serializable;

/**
 * Created by liyang-macbook on 2017/8/17.
 */
public class PayTransfer implements Serializable {

    private String out_biz_no;
    private String payee_account;
    private String amount;
    private String payer_show_name;
    private String payee_real_name;
    private String remark;
    private String payee_type = "ALIPAY_LOGONID";

    public String getOut_biz_no() {
        return out_biz_no;
    }

    public void setOut_biz_no(String out_biz_no) {
        this.out_biz_no = out_biz_no;
    }

    public String getPayee_account() {
        return payee_account;
    }

    public void setPayee_account(String payee_account) {
        this.payee_account = payee_account;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPayer_show_name() {
        return payer_show_name;
    }

    public void setPayer_show_name(String payer_show_name) {
        this.payer_show_name = payer_show_name;
    }

    public String getPayee_real_name() {
        return payee_real_name;
    }

    public void setPayee_real_name(String payee_real_name) {
        this.payee_real_name = payee_real_name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPayee_type() {
        return payee_type;
    }

    public void setPayee_type(String payee_type) {
        this.payee_type = payee_type;
    }
}
