package cn.com.myproject.adminuser.service.fallback;

import cn.com.myproject.adminuser.service.ISysUserRoleService;
import cn.com.myproject.adminuser.vo.SysUserRoleVO;
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
public class SysUserRoleServiceFallbackFactory implements FallbackFactory<ISysUserRoleService> {
    private static final Logger logger = LoggerFactory.getLogger(SysUserServiceFallbackFactory.class);
    @Override
    public ISysUserRoleService create(Throwable cause) {
        logger.info(cause.getMessage());
        return new ISysUserRoleService() {
            @Override
            public void addSysUserRole(SysUserRoleVO sysUserRole) {

            }

            @Override
            public void updateSysUserRole(SysUserRoleVO sysUserRole) {

            }

            @Override
            public List<SysUserRoleVO> findSysUserRoleByUserId(String userId) {
                return null;
            }

            @Override
            public void deleteSysUserRole(String userId) {

            }

            @Override
            public void batchAddSysUserRole(List<SysUserRoleVO> sysUserRoleList) {

            }
        };
    }
}
