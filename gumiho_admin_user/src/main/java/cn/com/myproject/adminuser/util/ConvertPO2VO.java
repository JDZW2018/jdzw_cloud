package cn.com.myproject.adminuser.util;

import cn.com.myproject.adminuser.po.SysResource;
import cn.com.myproject.adminuser.vo.MenuVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConvertPO2VO {

    private static final Logger logger = LoggerFactory.getLogger(ConvertPO2VO.class);

    public static void sysResourceToMenuVO(SysResource sysResource, MenuVO vo) {
        if(sysResource == null) {
            logger.warn("SysResource为空");
            return;
        }
        if(vo == null) {
            logger.warn("MenuVO为空");
            vo = new MenuVO();
        }
        vo.setId(sysResource.getResourceId());
        vo.setName(sysResource.getResourceName());
        vo.setUrl(sysResource.getValue());
        vo.setIcon(sysResource.getIcon());
    }
}
