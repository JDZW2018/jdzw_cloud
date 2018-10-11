package cn.com.myproject.adminuser.service.fallback;

import cn.com.myproject.adminuser.service.ISysRoleService;
import cn.com.myproject.adminuser.vo.SysRoleVO;
import com.github.pagehelper.PageInfo;
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
public class SysRoleServiceFallbackFactory implements FallbackFactory<ISysRoleService>{
    private static final Logger logger = LoggerFactory.getLogger(SysUserServiceFallbackFactory.class);
    @Override
    public ISysRoleService create(Throwable cause) {
        logger.info(cause.getMessage());

        return new ISysRoleService() {

            @Override
            public PageInfo<SysRoleVO> getPage(int pageNum, int pageSize, String keyword, String departmentId) {
                return null;
            }

            @Override
            public void addRoles(SysRoleVO sysRoleVO) {

            }

            @Override
            public void updateRoles(SysRoleVO sysRoleVO) {

            }

            @Override
            public Integer checkRoles(String roleName) {
                return null;
            }

            @Override
            public void delRoles(Integer id) {

            }

            @Override
            public SysRoleVO selectRoles(Integer id) {
                return null;
            }

            @Override
            public List<SysRoleVO> getRole() {
                return null;
            }

            @Override
            public List<SysRoleVO> selectRolesByDepartmentId(String departmentId) {
                return null;
            }
        };
    }
}
