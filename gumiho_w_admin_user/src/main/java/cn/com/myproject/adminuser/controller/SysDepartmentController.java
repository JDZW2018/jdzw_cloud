package cn.com.myproject.adminuser.controller;

import cn.com.myproject.adminuser.service.ISysDepartmentService;
import cn.com.myproject.adminuser.vo.SysDepartmentVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tianfusheng
 * @date 2018/3/14
 */
@Controller
@RequestMapping("/sysDepartment")
public class SysDepartmentController {
    @Autowired
    private ISysDepartmentService sysDepartmentService;
    private static final Logger logger = LoggerFactory.getLogger(SysDepartmentController.class);

    @ResponseBody
    @RequestMapping("/getAllForSysUser")
    public Map<String, Object> getAllForSysUser() {
        Map<String, Object> result = new HashMap<String, Object>();
        List<SysDepartmentVO> sysDepartmentVOList = sysDepartmentService.getAll();
        result.put("status", "success");
        result.put("sysDepartmentVOList", sysDepartmentVOList);
        return result;
    }


    @ResponseBody
    @RequestMapping("/getAllForSysRole")
    public Map<String, Object> getAllForSysRole() {
        Map<String, Object> result = new HashMap<String, Object>();
        List<SysDepartmentVO> sysDepartmentVOList = sysDepartmentService.getAll();
        result.put("status", "success");
        result.put("sysDepartmentVOList", sysDepartmentVOList);
        return result;
    }

    /**
     * 通过userid查询department对象
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("/selectByUserId")
    public Map<String, Object> selectByUserId(@RequestParam("userId") String userId) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (StringUtils.isBlank(userId)){
            result.put("status", "error");
            result.put("message","后台传参失败！");
            logger.error("message:后台传参失败");
            return result;
        }
        try {
            SysDepartmentVO sysDepartmentVO = sysDepartmentService.selectByUserId(userId);
            result.put("status", "success");
            result.put("sysDepartmentVO",sysDepartmentVO);
            return result;
        } catch (Exception e) {
            logger.error("[{}]-[{}]时，发生异常，异常信息为[{}]","gumiho-w-admin-user","department/selectByUserId内部错误",e.getMessage());
            result.put("status", "success");
            result.put("message","内部错误,稍后重试！");
            return result;
        }
    }
}
