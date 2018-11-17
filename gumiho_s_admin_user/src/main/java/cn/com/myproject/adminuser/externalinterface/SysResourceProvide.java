package cn.com.myproject.adminuser.externalinterface;


import cn.com.myproject.adminuser.po.SysResource;
import cn.com.myproject.adminuser.service.ISysResourceService;
import cn.com.myproject.adminuser.vo.MenuVO;
import cn.com.myproject.adminuser.vo.SysResourceVO;
import cn.com.myproject.util.bean.BeanCopyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.UUID;

/**
 *
 */
@RestController
@RequestMapping("/sysResource")
public class SysResourceProvide {

    @Autowired
    private ISysResourceService sysResourceService;

    @GetMapping("/getMenu")
    public LinkedHashSet<MenuVO> getMenu(String menuId, String loginName,String str) {
        return sysResourceService.getMenu(menuId,loginName,str);
    }

    @PostMapping("/getSysResource")
    public List<SysResourceVO> getSysResouce(@RequestBody SysResourceVO sysResourceVO) {
        if(null == sysResourceVO) {
            sysResourceVO = new SysResourceVO();
        }
        SysResource sysResource = new SysResource();
        BeanUtils.copyProperties(sysResourceVO,sysResource);
        List<SysResource> result = sysResourceService.getSysResource(sysResource);
        return BeanCopyUtils.copyList(result,SysResourceVO.class);
    }

    @PostMapping("/getSysResourceTree")
    public List<SysResourceVO> getSysResourceTree(@RequestBody SysResourceVO sysResourceVO, String treeId){
        SysResource sysResource = new SysResource();
        if(null != sysResourceVO) {
            BeanUtils.copyProperties(sysResourceVO,sysResource);
            List<SysResource> result = sysResourceService.getSysResourceTree(sysResource,treeId);
            List<SysResourceVO> resultvo = BeanCopyUtils.copyList(result,SysResourceVO.class);
            return resultvo;
        }else{
            return null;
        }
    }

    @PostMapping("/getSysResourceByResourceId")
    public SysResourceVO getSysResourceByResourceId(@RequestBody String resourceId){
        SysResource sysResource =  sysResourceService.getSysResourceByResourceId(resourceId);
        if(null == sysResource) {
            return null;
        }
        SysResourceVO vo = new SysResourceVO();
        BeanUtils.copyProperties(sysResource,vo);
        return vo;
    }

    @PostMapping("/addSysResource")
    public int addSysResource(@RequestBody SysResourceVO sysResourceVO){
        if(sysResourceVO == null) {
            return 0;
        }
        sysResourceVO.setResourceId(UUID.randomUUID().toString().replace("-", ""));
        sysResourceVO.setVersion(1);
        sysResourceVO.setStatus((short) 1);
        SysResource sysResource = new SysResource();
        BeanUtils.copyProperties(sysResourceVO,sysResource);
        sysResource.setType((short)1);
        return sysResourceService.addSysResource(sysResource);
    }

    @PostMapping("/updateSysResource")
    public int updateSysResource(@RequestBody SysResourceVO sysResourceVO){
        int result = 0;
        if(StringUtils.isNotBlank(sysResourceVO.getResourceId())){
            SysResource sysResource = new SysResource();
            BeanUtils.copyProperties(sysResourceVO,sysResource);
            result =sysResourceService.updateSysResource(sysResource);
        }
        return result;
    }

    @PostMapping("/deleteSysResource")
    public int deleteSysResource(@RequestBody String resourceId){
        int result = 0;
        if(StringUtils.isNotBlank(resourceId)){
            result =sysResourceService.deleteSysResource(resourceId);
        }
        return result;
    }
    @GetMapping("/checkValue")
    public Integer checkValue(@RequestParam("value") String value){
        Integer result = null;
        if (StringUtils.isNotBlank(value)){
            result = sysResourceService.checkValue(value);
        }
        return  result;
    }
}
