package cn.com.myproject.adminuser.externalinterface;



import cn.com.myproject.adminuser.po.SysRoleResource;
import cn.com.myproject.adminuser.service.ISysRoleResourceService;
import cn.com.myproject.adminuser.vo.SysRoleResourceVO;
import cn.com.myproject.util.BeanCopyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sysRoleResource")
public class SysRoleResourceProvide {

    @Autowired
    private ISysRoleResourceService sysRoleResourceService;

    @PostMapping("/getSysRoleResource")
    public List<SysRoleResourceVO> getSysRoleResource(@RequestBody SysRoleResourceVO sysRoleResourceVO) {
        if(null == sysRoleResourceVO) {
            sysRoleResourceVO = new SysRoleResourceVO();
        }
        SysRoleResource sysRoleResource = new SysRoleResource();
        BeanUtils.copyProperties(sysRoleResourceVO,sysRoleResource);
        List<SysRoleResource> result = sysRoleResourceService.getSysRoleResource(sysRoleResource);
        return BeanCopyUtils.copyList(result,SysRoleResourceVO.class);
    }

    @PostMapping("/getListByResourceIds")
    public List<SysRoleResourceVO> getListByResource(@RequestBody String resourceIds) {
        List<SysRoleResource> result = sysRoleResourceService.getListByResourceIds(resourceIds);
        return BeanCopyUtils.copyList(result,SysRoleResourceVO.class);
    }

    @PostMapping("/batchInsert")
    public void batchInsert(@RequestBody List<SysRoleResourceVO> sysRoleResourceVOS){
        if(null != sysRoleResourceVOS && !sysRoleResourceVOS.isEmpty()) {
            sysRoleResourceService.batchInsert(BeanCopyUtils.copyList(sysRoleResourceVOS,SysRoleResource.class));
        }

    }

    @PostMapping("/batchDelete")
    public void batchDelete(@RequestBody List<SysRoleResourceVO> sysRoleResourceVOS){
        if(null != sysRoleResourceVOS && !sysRoleResourceVOS.isEmpty()) {
            sysRoleResourceService.batchDelete(BeanCopyUtils.copyList(sysRoleResourceVOS,SysRoleResource.class));
        }

    }

    @GetMapping("/batchEditSysRoleResource")
    boolean batchEditSysRoleResource(String resourceIds,String roleId){
        return sysRoleResourceService.batchEditSysRoleResource(resourceIds,roleId);
    }
}
