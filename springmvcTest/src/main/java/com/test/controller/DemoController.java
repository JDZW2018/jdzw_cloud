package com.test.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class DemoController {

    /**
     * 2秒后跳转到百度
     *
     * @param response
     * @return
     */
    @RequestMapping("/refesh")
    public String refesh(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Refresh", "2;URL=https://www.baidu.com");
        return "helloworld";
    }

    /**
     * request test method
     * @param request
     * @return
     */
    @RequestMapping(value = "/requestTest",method = RequestMethod.GET)
    public LinkedHashMap<String, String> requestTest(HttpServletRequest request) {
        LinkedHashMap<String, String> result = new LinkedHashMap<String, String>();
        result.put("requestMethod", request.getMethod());
        System.out.println(request.getParameterMap().toString());
        Map<String, String[]> parameterMap = request.getParameterMap();
        /*Set entrySet =  parameterMap.entrySet();
        Iterator iterator = entrySet.iterator();
        while (iterator.hasNext()){
            Map.Entry next = (Map.Entry) iterator.next();
            result.put(next.getKey().toString(),next.getValue().toString());
        }*/
        for (Map.Entry<String, String[]> stringEntry: parameterMap.entrySet()){
            String[] values = stringEntry.getValue();
            for(String val : values){
                result.put(stringEntry.getKey(),val);
            }
        }
        return result;
    }


}
