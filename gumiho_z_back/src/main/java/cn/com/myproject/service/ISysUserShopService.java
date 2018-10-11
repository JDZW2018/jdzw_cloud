package cn.com.myproject.service;

import cn.com.myproject.adminuser.vo.SysDepartmentVO;
import cn.com.myproject.adminuser.vo.SysUserShopVO;
import cn.com.myproject.service.fallback.SysUserShopServiceFallbackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author tianfusheng
 * @date 2018/4/11
 */
@FeignClient(value = "gumiho-s-admin-user",url = "${feignclient.url}",fallbackFactory = SysUserShopServiceFallbackFactory.class)
public interface ISysUserShopService {

    /**
     * selectByUserId
     * @param userId
     * @return
     */
    @GetMapping("/sysUserShop/selectByUserId")
    SysUserShopVO selectByUserId(@RequestParam("userId") String userId);

    /**
     * selectDepartmentByUserId
     * @param userId
     * @return
     */
    @GetMapping("/sysDepartment/selectByUserId")
    SysDepartmentVO selectDepartmentByUserId(@RequestParam("userId") String userId);

}
