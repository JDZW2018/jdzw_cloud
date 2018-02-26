package cn.com.myproject.service;


import cn.com.myproject.adminuser.vo.MenuVO;
import cn.com.myproject.adminuser.vo.SysResourceVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedHashSet;
import java.util.List;

/**
 *
 */
@FeignClient(value = "gumiho-admin-user",url = "${feignclient.url}")
public interface ISysResourceService {

    @GetMapping("/sysResource/getMenu")
    LinkedHashSet<MenuVO> getMenu(@RequestParam("menuId") String menuId, @RequestParam("userId") String userId);

    @PostMapping("/sysResource/getSysResource")
    List<SysResourceVO> getSysResouce(@RequestBody SysResourceVO sysResourceVO);

    @PostMapping("/sysResource/getSysResourceTree")
    List<SysResourceVO> getSysResourceTree(@RequestBody SysResourceVO sysResourceVO, @RequestParam("treeId") String treeId);

    @GetMapping("/sysResource/getSysResourceByResourceId")
    SysResourceVO getSysResourceByResourceId(@RequestParam("resourceId") String resourceId);

    @PostMapping("/sysResource/addSysResource")
    int addSysResource(@RequestBody SysResourceVO sysResource);

    @PostMapping("/sysResource/updateSysResource")
    int updateSysResource(@RequestBody SysResourceVO sysResource);

    @PostMapping("/sysResource/deleteSysResource")
    int deleteSysResource(@RequestBody String resourceId);

}


