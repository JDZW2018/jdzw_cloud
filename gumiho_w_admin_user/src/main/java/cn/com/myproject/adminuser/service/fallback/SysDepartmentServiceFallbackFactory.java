package cn.com.myproject.adminuser.service.fallback;

import cn.com.myproject.adminuser.service.ISysDepartmentService;
import cn.com.myproject.adminuser.vo.SysDepartmentVO;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author tianfusheng
 * @date 2018/4/2
 */
@Component
public class SysDepartmentServiceFallbackFactory implements FallbackFactory<ISysDepartmentService> {
    private static final Logger logger = LoggerFactory.getLogger(SysDepartmentServiceFallbackFactory.class);

    @Override
    public ISysDepartmentService create(Throwable throwable) {
        logger.info(throwable.getMessage());

        return new ISysDepartmentService() {
            @Override
            public List<SysDepartmentVO> getAll() {
                return null;
            }

            @Override
            public SysDepartmentVO selectByUserId(String userId) {
                return null;
            }
        };
    }
}
