package cn.com.myproject.adminuser.feign.fallback;

import cn.com.myproject.adminuser.feign.IShopInfoService;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author tianfusheng
 * @date 2018/5/17
 */
@Component
public class ShopInfoServiceFallbackFactory  implements FallbackFactory<IShopInfoService>{
    private static final Logger logger = LoggerFactory.getLogger(ShopInfoServiceFallbackFactory.class);
    @Override
    public IShopInfoService create(Throwable throwable) {
        logger.warn(throwable.getMessage());
        return new IShopInfoService() {

            @Override
            public Boolean o2oStatusUpdate(String shopId, Integer status) {
                return null;
            }

            @Override
            public Boolean serviceStatusUpdate(String shopId, Integer status) {
                return null;
            }

            @Override
            public Boolean b2cStatusUpdate(String shopId, Integer status) {
                return null;
            }

            @Override
            public Boolean b2bStatusUpdate(String shopId, Integer status) {
                return null;
            }

            @Override
            public Boolean b2bNaStatusUpdate(String shopId, Integer status) {
                return null;
            }

            @Override
            public Boolean operatingStatusUpdate(String shopId, Integer status) {
                return null;
            }

            @Override
            public String findAdcodeByShopId(String shopId, Integer shopType) {
                return null;
            }
        };
    }
}
