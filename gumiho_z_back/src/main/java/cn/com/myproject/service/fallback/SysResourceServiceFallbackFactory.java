package cn.com.myproject.service.fallback;

import cn.com.myproject.adminuser.vo.MenuVO;
import cn.com.myproject.adminuser.vo.SysResourceVO;
import cn.com.myproject.service.ISysResourceService;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;

/**
 * @author tianfusheng
 * @date 2018/4/2
 */
@Component
public class SysResourceServiceFallbackFactory implements FallbackFactory<ISysResourceService> {
    private static final Logger logger = LoggerFactory.getLogger(SysResourceServiceFallbackFactory.class);
    @Override
    public ISysResourceService create(Throwable throwable) {
        logger.info(throwable.getMessage());
        return new ISysResourceService() {
            @Override
            public LinkedHashSet<MenuVO> getMenu(String menuId, String loginName, String str) {
                return null;
            }

            @Override
            public SysResourceVO getSysResourceByResourceId(String resourceId) {
                return null;
            }
        };
    }
}
