package cn.com.myproject.adminuser.service.fallback;

import cn.com.myproject.adminuser.service.ISysRoleDepartmentService;
import cn.com.myproject.adminuser.vo.SysRoleDepartmentVO;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author tianfusheng
 * @date 2018/4/2
 */
@Component
public class SysRoleDepartmentServiceFallbackFactory implements FallbackFactory<ISysRoleDepartmentService> {
    private static final Logger logger = LoggerFactory.getLogger(SysRoleDepartmentServiceFallbackFactory.class);

    @Override
    public ISysRoleDepartmentService create(Throwable throwable) {
        logger.info(throwable.getMessage());

        return new ISysRoleDepartmentService() {
            @Override
            public void addSysRoleDepartment(SysRoleDepartmentVO sysRoleDepartmentVO) {

            }

            @Override
            public void deleteByRoleId(String roleId) {

            }
        };
    }
}
