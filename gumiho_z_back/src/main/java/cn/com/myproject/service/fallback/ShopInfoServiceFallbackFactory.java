package cn.com.myproject.service.fallback;

import cn.com.myproject.merchant.entity.VO.ShopInfoVO;
import cn.com.myproject.service.IShopInfoService;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author tianfusheng
 * @date 2018/4/11
 */
@Component
public class ShopInfoServiceFallbackFactory implements FallbackFactory<IShopInfoService> {
    private static final Logger logger = LoggerFactory.getLogger(ShopInfoServiceFallbackFactory.class);
    @Override
    public IShopInfoService create(Throwable throwable) {
        logger.info(throwable.getMessage());
        return new IShopInfoService() {
            @Override
            public ShopInfoVO terminalInfo(String supplierId) {
                return null;
            }

            @Override
            public ShopInfoVO b2bNationalInfo(String supplier_id) {
                return null;
            }

            @Override
            public ShopInfoVO b2bInfo(String supplier_id) {
                return null;
            }

            @Override
            public ShopInfoVO b2cInfo(String supplierId) {
                return null;
            }

            @Override
            public ShopInfoVO operatingInfo(String operationId) {
                return null;
            }

            @Override
            public ShopInfoVO serviceInfo(String shopId) {
                return null;
            }
        };
    }
}
