package com.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Tianfusheng
 * @date 2018/9/8
 */
@RestController
public class ForwardAndSendRedirectTestController {


    @RequestMapping("/forwardTest")
    public  void forwardTest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("forwardTest","requestForwardTest");
        request.getRequestDispatcher("/result").forward(request,response);
    }
    @RequestMapping("/sendRedirect")
    public void sendRedirect(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.sendRedirect("/result");
        return;

    }

    @RequestMapping("/result")
    public String result(HttpServletRequest request, HttpServletResponse response){
        String forwardTestResult = request.getParameter("forwardTest");
        return forwardTestResult != null? forwardTestResult:"sendRedirectTest";
    }

}
