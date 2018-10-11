package cn.com.myproject.adminuser.externalinterface;

import cn.com.myproject.adminuser.po.SysDepartment;
import cn.com.myproject.adminuser.service.ISysDepartmentService;
import cn.com.myproject.adminuser.vo.SysDepartmentVO;
import cn.com.myproject.util.bean.BeanCopyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author tianfusheng
 * @date 2018/3/14
 */
@RestController
@RequestMapping("/sysDepartment")
public class SysDepartmentProvide {
    @Autowired
    private ISysDepartmentService sysDepartmentService;

    @GetMapping("/getAll")
    public List<SysDepartmentVO> getAll(){
       List<SysDepartment> list = sysDepartmentService.selectAll();
        return BeanCopyUtils.copyList(list,SysDepartmentVO.class);
    }

    /**
     * 通过userd查找department对象
     * @return
     */
    @GetMapping("/selectByUserId")
    public SysDepartmentVO selectByUserId(@RequestParam("userId") String userId){
        if (StringUtils.isBlank(userId)){
            return  null;
        }
        SysDepartment sysDepartment = sysDepartmentService.selectByUserId(userId);
        SysDepartmentVO sysDepartmentVO = new SysDepartmentVO();
        if (null != sysDepartment) {
            BeanUtils.copyProperties(sysDepartment,sysDepartmentVO);
        }
        return sysDepartmentVO;
    }

}
