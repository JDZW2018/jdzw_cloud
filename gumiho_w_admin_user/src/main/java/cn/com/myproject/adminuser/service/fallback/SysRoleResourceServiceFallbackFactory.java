package cn.com.myproject.adminuser.service.fallback;

import cn.com.myproject.adminuser.service.ISysRoleResourceService;
import cn.com.myproject.adminuser.vo.SysRoleResourceVO;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author tianfusheng
 * @date 2018/2/26
 */
@Component
public class SysRoleResourceServiceFallbackFactory implements FallbackFactory<ISysRoleResourceService> {
    private static final Logger logger = LoggerFactory.getLogger(SysUserServiceFallbackFactory.class);

    @Override
    public ISysRoleResourceService create(Throwable cause) {
        logger.info(cause.getMessage());

        return new ISysRoleResourceService() {
            @Override
            public List<SysRoleResourceVO> getSysRoleResource(SysRoleResourceVO sysRoleResourceVO) {
                return null;
            }

            @Override
            public List<SysRoleResourceVO> getListByResourceIds(String resourceIds) {
                return null;
            }

            @Override
            public void batchInsert(List<SysRoleResourceVO> sysRoleResourceVOS) {

            }

            @Override
            public void batchDelete(List<SysRoleResourceVO> sysRoleResourceVOS) {

            }

            @Override
            public boolean batchEditSysRoleResource(String resourceIds, String roleId) {
                return false;
            }
        };
    }
}
