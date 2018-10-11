package cn.com.myproject.controller;

import cn.com.myproject.adminuser.vo.SysDepartmentVO;
import cn.com.myproject.adminuser.vo.SysUserShopVO;
import cn.com.myproject.adminuser.vo.SysUserVO;
import cn.com.myproject.merchant.entity.VO.ShopInfoVO;
import cn.com.myproject.redis.IRedisService;
import cn.com.myproject.service.IShopInfoService;
import cn.com.myproject.service.ISysUserShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tianfusheng
 * @date 2018/4/8
 */
@Controller
public class    BlacklistController {
    private static final Logger logger = LoggerFactory.getLogger(BlacklistController.class);
    private static final String USER_INFO = "user_info";
    @Autowired
    private IRedisService redisService;
    @Autowired
    private ISysUserShopService sysUserShopService;

    @Autowired
    private IShopInfoService shopInfoService;

    @RequestMapping("/blacklist")
    public ModelAndView blacklist(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();

        SecurityContext sc = (SecurityContext) request.getSession().getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
        String loginName = sc.getAuthentication().getPrincipal().toString();
        JdkSerializationRedisSerializer serializer = new JdkSerializationRedisSerializer();
        SysUserVO user = (SysUserVO) serializer.deserialize((byte[]) redisService.getHashValue(USER_INFO,loginName));
        SysUserShopVO sysUserShopVO = sysUserShopService.selectByUserId(user.getUserId());
        SysDepartmentVO sysDepartmentVO = sysUserShopService.selectDepartmentByUserId(user.getUserId());
        user.setDepartmentName(sysDepartmentVO.getDepartmentName());
        ShopInfoVO shopInfoVO = null;
        if (null != sysUserShopVO) {
            switch (sysUserShopVO.getShopType()){
                case 1:
                    shopInfoVO = shopInfoService.terminalInfo(sysUserShopVO.getShopId());
                    break;
                case 2:
                    shopInfoVO = shopInfoService.b2cInfo(sysUserShopVO.getShopId());
                    break;
                case 3:
                    shopInfoVO = shopInfoService.b2bInfo(sysUserShopVO.getShopId());
                    break;
                case 4:
                    shopInfoVO = shopInfoService.b2cInfo(sysUserShopVO.getShopId());
                    break;
                case 5:
                    shopInfoVO = shopInfoService.operatingInfo(sysUserShopVO.getShopId());
                    break;
                case 7:
                    shopInfoVO = shopInfoService.serviceInfo(sysUserShopVO.getShopId());
                    break;
                default:
                    break;
            }
        }
        modelAndView.addObject("userInfo",user);
        modelAndView.addObject("shopInfo",shopInfoVO);
        modelAndView.setViewName("blacklist");
        return modelAndView;
    }
}
