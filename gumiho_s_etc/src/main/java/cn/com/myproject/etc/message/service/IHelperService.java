package cn.com.myproject.etc.message.service;

import cn.com.myproject.helper.entity.VO.HelperVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author zyh
 * @description:
 * @createtime 2018/5/3 0003
 */
@FeignClient(name="gumiho-s-merchant",url="gumiho-s-merchant.url")
public interface IHelperService {
    @GetMapping("/helper/findHelper")
    HelperVO findHelper(String helperId);
}