package cn.com.myproject.service.fallback;

import cn.com.myproject.adminuser.vo.SysDepartmentVO;
import cn.com.myproject.adminuser.vo.SysUserShopVO;
import cn.com.myproject.service.ISysUserShopService;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author tianfusheng
 * @date 2018/4/11
 */
@Component
public class SysUserShopServiceFallbackFactory implements FallbackFactory<ISysUserShopService> {
    private static final Logger logger = LoggerFactory.getLogger(SysUserShopServiceFallbackFactory.class);
    @Override
    public ISysUserShopService create(Throwable throwable) {
        logger.info(throwable.getMessage());
        return new ISysUserShopService() {
            @Override
            public SysDepartmentVO selectDepartmentByUserId(String userId) {
                return null;
            }

            @Override
            public SysUserShopVO selectByUserId(String userId) {
                return null;
            }
        };
    }
}
