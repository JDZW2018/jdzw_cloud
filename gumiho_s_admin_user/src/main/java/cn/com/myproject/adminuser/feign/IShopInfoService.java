package cn.com.myproject.adminuser.feign;

import cn.com.myproject.adminuser.feign.fallback.ShopInfoServiceFallbackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author tianfusheng
 * @date 2018/5/17
 */
@FeignClient(value = "gumiho-s-merchant", url = "${gumiho-s-merchant.url}", fallbackFactory = ShopInfoServiceFallbackFactory.class)
public interface IShopInfoService {
    /**
     * o2oStatusUpdate o2o更新商户主体状态
     * @param supplierId
     * @param status
     * @return
     */
    @PostMapping("/o2oSupplier/deleteById")
    Boolean o2oStatusUpdate(@RequestParam("supplierId") String supplierId, @RequestParam("status") Integer status);

    /**
     * serviceStatusUpdate 服务商更新商户主题状态
     * @param shopId
     * @param status
     * @return
     */
    @PostMapping("/serviceSupplier/deleteById")
    Boolean serviceStatusUpdate(@RequestParam("supplierId") String shopId, @RequestParam("status") Integer status);

    /**
     * b2cStatusUpdate b2c更新商户主体状态
     * @param shopId
     * @param status
     * @return
     */
    @GetMapping("/supplier/updateBackList")
    Boolean b2cStatusUpdate(@RequestParam("shopId") String shopId, @RequestParam("status") Integer status);

    /**
     * b2bStatusUpdate b2b更新商户主体状态
     * @param shopId
     * @param status
     * @return
     */
    @GetMapping("/businesslist/updateBlack")
    Boolean b2bStatusUpdate(@RequestParam("shopId") String shopId, @RequestParam("status") Integer status);

    /**
     * b2bNaStatusUpdate b2b全国更新商户主体状态
     * @param shopId
     * @param status
     * @return
     */
    @GetMapping("/nationalbusiness/updateBlack")
    Boolean b2bNaStatusUpdate(@RequestParam("shopId") String shopId, @RequestParam("status") Integer status);

    /**
     * operatingStatusUpdate 运营中心更新商户主体状态
     * @param shopId
     * @param status
     * @return
     */
    @GetMapping("/operating/updateBackList")
    Boolean operatingStatusUpdate(@RequestParam("shopId") String shopId, @RequestParam("status") Integer status);

    /**
     * 查询区域code 锁定B2B本地商城显示内容
     * @param shopId
     * @param shopType
     * @return
     */
    @GetMapping("/backgroundsupperlier/searchAdcodeByShopId")
    String findAdcodeByShopId(@RequestParam("shopId") String shopId, @RequestParam("shopType") Integer shopType);
}
