package cn.com.myproject.adminuser.controller;

import cn.com.myproject.adminuser.vo.SysResourceVO;
import cn.com.myproject.adminuser.vo.SysRoleResourceVO;
import cn.com.myproject.service.ISysResourceService;
import com.github.pagehelper.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资源管理
 */
@Controller
@RequestMapping("/resource")
public class SysResourceController {

    @Autowired
    private ISysResourceService sysResourceService;

    @RequestMapping({"/index","/"})
    public String index(){
        return "sys/resource";
    }

    @RequestMapping("/get")
    @ResponseBody
    public Map getMenus(String menuId, HttpServletRequest request, HttpServletResponse response) {
        return new HashMap();
    }

    @ResponseBody
    @RequestMapping(value="/getResourceJson")
    public String getResourceJson(){
        StringBuffer data  = new StringBuffer();
        data.append("[");
        List<SysResourceVO> resourceList  = sysResourceService.getSysResourceTree(new SysResourceVO(),"0");
        List<SysRoleResourceVO> sysRoleResourceList = null;
        int i=0;
        for(SysResourceVO sysResource:resourceList){
            if(i!=0){
                data.append(",");
            }
            data.append(this.menutoJson(sysResource,sysRoleResourceList));
            i++;
        }
        data.append("]");
        return data.toString();
    }

    @ResponseBody
    @RequestMapping(value="/getMenuJson")
    public String getMenuJson(){
        StringBuffer data  = new StringBuffer();
        data.append("[{\"id\":\"0\",\"text\":\"顶级菜单\",\"children\":[");
        List<SysResourceVO> resourceList  = sysResourceService.getSysResourceTree(new SysResourceVO(),"0");
        List<SysRoleResourceVO> sysRoleResourceList = null;
        int i=0;
        for(SysResourceVO sysResource:resourceList){
            if(i!=0){
                data.append(",");
            }
            data.append(this.menutoJson(sysResource,sysRoleResourceList));
            i++;
        }
        data.append("]}]");
        return data.toString();
    }

    @ResponseBody
    @RequestMapping(value="/getResource")
    public Map<String,Object> getResource(String resourceId){
        Map<String,Object> result = new HashMap<>();
        if(StringUtil.isNotEmpty(resourceId)){
            SysResourceVO sysResource = sysResourceService.getSysResourceByResourceId(resourceId);
            if(null != sysResource){
                result.put("status","success");
                result.put("resource",sysResource);
            }else{
                result.put("status","fail");
                result.put("message","未获取到相关数据");
            }
        }else{
            result.put("status","fail");
            result.put("message","参数为空");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value="/operSysResource")
    public Map<String,Object> operSysResource(SysResourceVO sysResource){
        Map<String,Object> result = new HashMap<>();
        int i= 0;
        String oper = "";
        if(StringUtils.isNotBlank(sysResource.getResourceId())){
           i = sysResourceService.updateSysResource(sysResource);
           oper = "更新";
        }else{
           sysResource.setMenu((short)1);
            sysResource.setCreateTime(System.currentTimeMillis());
           i = sysResourceService.addSysResource(sysResource);
           oper = "添加";
        }
        if(i>0){
            result.put("status","success");
            result.put("message",oper + "操作成功");
        }else{
            result.put("status","success");
            result.put("message",oper + "操作失败");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value="/deleteSysResource")
    public Map<String,Object> deleteSysResource(String resourceId){
        Map<String,Object> result = new HashMap<>();
        SysResourceVO sysResource = new SysResourceVO();
        sysResource.setParentId(resourceId);
        List<SysResourceVO> sysResourceList = sysResourceService.getSysResouce(sysResource);
        if(null != sysResourceList && sysResourceList.size()>0){
            result.put("status","fail");
            result.put("message","请先移除子类");
        }else{
            int i = sysResourceService.deleteSysResource(resourceId);
            if(i>0){
                result.put("status","success");
                result.put("message","操作成功");
            }else{
                result.put("status","fail");
                result.put("message","操作失败");
            }
        }
        return result;
    }

    private String menutoJson(SysResourceVO sysResource,List<SysRoleResourceVO> sysRoleResourceList){
        StringBuffer data  = new StringBuffer();
        data.append("{\"id\":\""+sysResource.getResourceId()+"\", \"text\":\""+sysResource.getResourceName()+"\"");
        if(null != sysRoleResourceList && sysRoleResourceList.size()>0){
            for (SysRoleResourceVO sysRoleResource : sysRoleResourceList) {
                if((sysRoleResource.getResourceId()).equals(sysResource.getResourceId()) && !sysResource.getHasChildren()){
                    data.append(",\"checked\":true");
                }
            }
        }
        if(sysResource.getHasChildren()){ //如果有子，继续
            data.append(",\"children\":[");
            int i=0;
            List<SysResourceVO> menuList= sysResource.getChildren();
            for(SysResourceVO child:menuList){
                if(i!=0){
                    data.append(",");
                }
                if(sysRoleResourceList!=null){
                    data.append(this.menutoJson(child,sysRoleResourceList));
                }else{
                    data.append(this.menutoJson(child,null));
                }
                i++;
            }
            data.append("]");
        }
        data.append("} ");
        return data.toString();
    }
}
