package cn.com.myproject.controller;

import cn.com.myproject.adminuser.vo.MenuVO;
import cn.com.myproject.service.ISysResourceService;
import cn.com.myproject.utils.Message;
import cn.com.myproject.utils.MessageUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * @author Tianfusheng
 * @date 2018/3/22 11:54
 * @desc 转发到对应的子系统，统一管理menu,loginname
 **/
@RestController
public class BaseResourceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseResourceController.class);

    @Autowired
    private ISysResourceService sysResourceService;

    @RequestMapping("/")
    public String login(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        String serverName = request.getRequestURL().toString();
        String uri = StringUtils.substringBefore(request.getServerName(), ".");
        resp.sendRedirect(serverName + uri + "/");
        return "";
    }



    @RequestMapping("/menu/get")
    @ResponseBody
    public Message<LinkedHashSet<MenuVO>> getMenus(String menuId, HttpServletRequest request) {
        SecurityContext sc = (SecurityContext) request.getSession().getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
        String loginName = sc.getAuthentication().getPrincipal().toString();
        if (null == loginName) {
            return MessageUtils.getFail("请登录");
        }
        String str = StringUtils.substringBefore(request.getServerName(), ".");
        return MessageUtils.getSuccess(sysResourceService.getMenu(menuId, loginName, str));
    }

    @RequestMapping("/menu/getLoginName")
    @ResponseBody
    public Map<String, Object> getLoginName(HttpServletRequest request) {
        SecurityContext sc = (SecurityContext) request.getSession().getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
        String loginName = sc.getAuthentication().getPrincipal().toString();
        Map<String, Object> result = new HashMap<String, Object>();
        if (null != loginName) {
            result.put("status", "success");
            result.put("loginName", loginName);
        } else {
            result.put("status", "error");
        }
        return result;
    }

    @RequestMapping("/title/getTitleName")
    public Map<String, Object> getSysResourceByResourceId(String resourceId) {
        Map<String, Object> result = new HashMap<String, Object>();
        String resourceName = sysResourceService.getSysResourceByResourceId(resourceId).getResourceName();
        if (null != resourceName) {
            result.put("status", "success");
            result.put("titlename", resourceName);
        } else {
            result.put("status", "error");
        }
        return result;
    }
}