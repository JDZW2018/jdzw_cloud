package cn.com.myproject.adminuser.controller;

import cn.com.myproject.adminuser.vo.MenuVO;
import cn.com.myproject.security.SecurityUser;
import cn.com.myproject.service.ISysResourceService;
import cn.com.myproject.util.Message;
import cn.com.myproject.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashSet;

/**
 *
 */
@Controller
@RequestMapping("/menu")
public class MenuController {


    @Autowired
    private ISysResourceService sysResourceService;

    @RequestMapping("/get")
    @ResponseBody
    public Message<LinkedHashSet<MenuVO>> getMenus(String menuId, HttpServletRequest request, HttpServletResponse response) {
        //获取当前登录用户
        SecurityContext context = (SecurityContext) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        if(null == context) {
            return MessageUtils.getFail("请登录");
        }
        SecurityUser user = (SecurityUser)context.getAuthentication().getPrincipal();
        if(null == user) {
            return MessageUtils.getFail("请登录");
        }
        return MessageUtils.getSuccess(sysResourceService.getMenu(menuId,user.getUserId()));
    }
}
