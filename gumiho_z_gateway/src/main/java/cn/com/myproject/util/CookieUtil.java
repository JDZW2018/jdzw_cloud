package cn.com.myproject.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

    /**
     * 设置cookie
     *
     * @param name
     * @param value
     * @param response
     */
    public static void setCookie(String name, String value, HttpServletResponse response){
        Cookie cookie = new Cookie(name,value);// 声明cookie
        cookie.setMaxAge(3600*24*14);// 设置超时时间，默认两周超时
        cookie.setPath("/");// 设置全局
        response.addCookie(cookie);// 返回cookie
    }

    /**
     * 根据name获取value
     *
     * @param name
     * @param request
     * @return
     */
    public static String getCookie(String name, HttpServletRequest request){
        String returnVal = "";
        Cookie[] cookies = request.getCookies();// 获取cookie数组
        if(cookies != null && cookies.length > 0){
            for(Cookie cookie : cookies){// 循环遍历出对应name的value
                if(cookie.getName().equals(name)){
                    returnVal = cookie.getValue();
                }
            }
        }
        return  returnVal;
    }

}