package cn.com.myproject.service;

import cn.com.myproject.merchant.entity.VO.ShopInfoVO;
import cn.com.myproject.service.fallback.ShopInfoServiceFallbackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author tianfusheng
 * @date 2018/4/11
 */
@FeignClient(value = "gumiho-s-merchant",url = "${gumiho-s-merchant.url}",fallbackFactory = ShopInfoServiceFallbackFactory.class)
public interface IShopInfoService {
    /**
     * TerminalInfo
     * @param shopId
     * @return
     */
    @PostMapping("/o2oSupplier/terminalInfo")
    ShopInfoVO terminalInfo(@RequestParam("shopId") String shopId);

    /**
     * b2bNational
     * @param shopId
     * @return
     */
    @GetMapping("/nationalbusiness/b2bNationalInfo")
    ShopInfoVO b2bNationalInfo(String shopId);

    /**
     * b2b
     * @param shopId
     * @return
     */
    @GetMapping("/businesslist/b2bInfo")
    ShopInfoVO b2bInfo(String shopId);

    /**
     * b2c
     * @param shopId
     * @return
     */
    @GetMapping("/supplier/b2cInfo")
    ShopInfoVO b2cInfo(String shopId);

    /**
     * operatingInfo
     * @param shopId
     * @return
     */
    @GetMapping("/operating/operatingInfo")
    ShopInfoVO operatingInfo(String shopId);

    /**
     * serviceInfo
     * @param shopId
     * @return
     */
    @PostMapping("/serviceSupplier/serviceInfo")
    ShopInfoVO serviceInfo(String shopId);

}
