package cn.com.myproject.adminuser.util;


import java.util.regex.Pattern;

/**
 * @author tianfusheng
 * @date 2018/3/22
 */
public class Validation {
    /**
     * 手机号验证
     *
     * @param str
     * @return 验证通过返回true
     */
    /**
     * PERMITNAME_PATTERN 验证用户名,数字字母汉字，不可包含特殊字符
     */
    private static Pattern PERMITNAME_PATTERN = Pattern.compile("[\u4e00-\u9fa5\\w]+");
    /**
     * MOBILE_PATTERN 手机号验证
     */
    private static Pattern MOBILE_PATTERN = Pattern.compile("^[1][3,4,5,6,7,8,9][0-9]{9}$");

    /**
     * PERMITPASSWORD_PATTERN 密码验证：6到16位,必须包含字母和数字,不可含有汉字和特殊字符
     */
    private static Pattern PERMITPASSWORD_PATTERN = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$");



    public static boolean isMobile(final String str) {
        return MOBILE_PATTERN.matcher(str).matches();
    }
/*    public static boolean isPhone(String str) {
        Pattern p1 = null, p2 = null;
        Matcher m  = null;
        boolean b  = false;
        p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$");  // 验证带区号的
        p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");         // 验证没有区号的
        if (str.length() > 9) {
            m = p1.matcher(str);
            b = m.matches();
        } else {
            m = p2.matcher(str);
            b = m.matches();
        }
        return b;
    }*/
    /**
     * 用户名验证
     *
     * @param str
     * @return 验证通过返回true
     */
    public static boolean isPermitName(final String str) {
        return PERMITNAME_PATTERN.matcher(str).matches();
    }

    /**
     * 密码验证
     *
     * @param str
     * @return 验证通过返回true
     */
    public static boolean isPermitPassword(final String str) {
        return PERMITPASSWORD_PATTERN.matcher(str).matches();
    }
}
