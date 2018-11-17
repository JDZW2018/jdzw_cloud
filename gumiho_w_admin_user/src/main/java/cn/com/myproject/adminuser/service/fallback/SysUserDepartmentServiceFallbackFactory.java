package cn.com.myproject.adminuser.service.fallback;

import cn.com.myproject.adminuser.service.ISysUserDepartmentService;
import cn.com.myproject.adminuser.vo.SysUserDepartmentVO;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author tianfusheng
 * @date 2018/4/2
 */
@Component
public class SysUserDepartmentServiceFallbackFactory  implements FallbackFactory<ISysUserDepartmentService>{
    private static final Logger logger = LoggerFactory.getLogger(SysUserDepartmentServiceFallbackFactory.class);

    @Override
    public ISysUserDepartmentService create(Throwable throwable) {
        logger.info(throwable.getMessage());

        return new ISysUserDepartmentService() {
            @Override
            public void addSysUserDepartment(SysUserDepartmentVO sysUserDepartmentVO) {

            }

            @Override
            public void deleteByUserId(String userId) {

            }
        };
    }
}
