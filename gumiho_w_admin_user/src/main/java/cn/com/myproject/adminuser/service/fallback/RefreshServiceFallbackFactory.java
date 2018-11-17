package cn.com.myproject.adminuser.service.fallback;

import cn.com.myproject.adminuser.service.IRefreshService;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tianfusheng
 * @date 2018/4/2
 */
//@Component
public class RefreshServiceFallbackFactory implements FallbackFactory<IRefreshService> {
    private static final Logger logger = LoggerFactory.getLogger(RefreshServiceFallbackFactory.class);

    @Override
    public IRefreshService create(Throwable throwable) {
        logger.info(throwable.getMessage());
        return new IRefreshService() {
            @Override
            public String resource() {
                return null;
            }
        };
    }
}
