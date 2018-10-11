package cn.com.myproject.adminuser.service.fallback;

import cn.com.myproject.adminuser.service.ISysResourceService;
import cn.com.myproject.adminuser.vo.MenuVO;
import cn.com.myproject.adminuser.vo.SysResourceVO;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.List;

/**
 * @author tianfusheng
 * @date 2018/2/26
 */
@Component
public class SysResourceServiceFallbackFactory  implements FallbackFactory<ISysResourceService>{
    private static final Logger logger = LoggerFactory.getLogger(SysUserServiceFallbackFactory.class);
    @Override
    public ISysResourceService create(Throwable cause) {
        logger.info(cause.getMessage());

        return new ISysResourceService() {
            @Override
            public LinkedHashSet<MenuVO> getMenu(String menuId, String userId) {
                return null;
            }

            @Override
            public List<SysResourceVO> getSysResouce(SysResourceVO sysResourceVO) {
                return null;
            }

            @Override
            public List<SysResourceVO> getSysResourceTree(SysResourceVO sysResourceVO, String treeId) {
                return null;
            }

            @Override
            public SysResourceVO getSysResourceByResourceId(String resourceId) {
                return null;
            }

            @Override
            public int addSysResource(SysResourceVO sysResource) {
                return 0;
            }

            @Override
            public int updateSysResource(SysResourceVO sysResource) {
                return 0;
            }

            @Override
            public int deleteSysResource(String resourceId) {
                return 0;
            }

            @Override
            public int checkValue(String value) {
                return 0;
            }
        };
    }
}
