package cn.com.myproject.adminuser.service;

import cn.com.myproject.adminuser.service.fallback.SysDepartmentServiceFallbackFactory;
import cn.com.myproject.adminuser.vo.SysDepartmentVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author tianfusheng
 * @date 2018/3/14
 */
@FeignClient(name = "gumiho-s-admin-user",url = "${feignclient.url}",fallbackFactory = SysDepartmentServiceFallbackFactory.class)
public interface ISysDepartmentService {

    @GetMapping("/sysDepartment/getAll")
    List<SysDepartmentVO> getAll();
    @GetMapping("/sysDepartment/selectByUserId")
    SysDepartmentVO selectByUserId(@RequestParam("userId") String userId);
}
