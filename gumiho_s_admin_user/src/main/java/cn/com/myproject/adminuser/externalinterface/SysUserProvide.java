package cn.com.myproject.adminuser.externalinterface;


import cn.com.myproject.adminuser.feign.IShopInfoService;
import cn.com.myproject.adminuser.po.SysUser;
import cn.com.myproject.adminuser.po.SysUserShop;
import cn.com.myproject.adminuser.service.ISysUserService;
import cn.com.myproject.adminuser.service.ISysUserShopService;
import cn.com.myproject.adminuser.vo.SysUserVO;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sysUser")
public class SysUserProvide {

    private static final Logger logger = LoggerFactory.getLogger(SysUserProvide.class);

    @Autowired
    private ISysUserService service;
    @Autowired
    private ISysUserShopService sysUserShopService;
    @Autowired
    private IShopInfoService shopInfoService;

    @GetMapping("/getPage")
    public PageInfo<SysUserVO> getPage(int pageNum, int pageSize, String keyword, String departmentId) {
        if (null != keyword) {
            keyword = keyword.replace("%20", "");
        }
        return service.getPage(pageNum, pageSize, keyword, departmentId);
    }

    @GetMapping("/userBlacklist")
    public PageInfo<SysUserVO> userBlacklist(int pageNum, int pageSize, String keyword, String departmentId) {
        if (null != keyword) {
            keyword = keyword.replace("%20", "");
        }
        return service.userBlacklist(pageNum, pageSize, keyword, departmentId);
    }

    @GetMapping("/recycleBinUserList")
    public PageInfo<SysUserVO> recycleBinUserList(int pageNum, int pageSize, String keyword, String departmentId) {
        if (null != keyword) {
            keyword = keyword.replace("%20", "");
        }
        return service.recycleBinUserList(pageNum, pageSize, keyword, departmentId);
    }

    @GetMapping("/getByLoginName")
    public SysUserVO getByLoginName(String loginName) {
        return service.getByLoginName(loginName);
    }

    @GetMapping("/getByPhone")
    public SysUserVO getByPhone(String phone) {
        return service.getByPhone(phone);
    }

    @GetMapping("/getByUserName")
    public SysUserVO getByUserName(String userName) {
        return service.getByUserName(userName);
    }


    @GetMapping("/findByUserId")
    public SysUserVO findById(String userId) {
        SysUser user = service.findByUserId(userId);
        if (null == user) {
            return null;
        }
        SysUserVO sysUserVO = new SysUserVO();
        BeanUtils.copyProperties(user, sysUserVO);
        return sysUserVO;
    }

    @GetMapping("/checkUser")
    public Integer checkUser(String userName) {
        Integer info = service.checkUsers(userName);
        if (null != info && 1 <= info) {
            return 1;
        }
        return 0;
    }

    @PostMapping("/selectUser")
    public SysUserVO selectUser(@RequestBody Integer id) {
        SysUser user = service.selectUsers(id);
        if (null == user) {
            return null;
        }
        SysUserVO sysUserVO = new SysUserVO();
        BeanUtils.copyProperties(user, sysUserVO);
        return sysUserVO;
    }

    @PostMapping("/delUsers")
    public void delUsers(@RequestBody String userId) {
        try {
            service.delusers(userId);
        } catch (Exception e) {
            logger.error("[{}]-[{}]时，发生异常，异常信息为[{}]", "gumiho-s-admin-user", "delUsers", e.getMessage());
        }
    }

    @PostMapping("/updateUser")
    public String updateUsers(@RequestBody SysUserVO sysUserVO) {
        String result = "修改失败！";
        if (null == sysUserVO) {
            return result;
        }
        try {
            SysUser sysUser = new SysUser();
            BeanUtils.copyProperties(sysUserVO, sysUser);
            service.updateUsers(sysUser);
            result = "修改成功!";
        } catch (Exception ex) {
            logger.error("[{}]-[{}]时，发生异常，异常信息为[{}]", "gumiho-s-admin-user", "updateUser", ex.getMessage());
        }
        return result;
    }

    /**
     * 更改主帐号状态,同时修改员工帐号状态。主帐号拉黑，员工帐号放入回收站。
     * updateStatus
     *
     * @return
     */
    @PostMapping("/updateStatus")
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> updateStatus(@RequestParam("userId") String userId, @RequestParam("status") Short status) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (null == userId || status == null) {
            result.put("status", "error");
            result.put("message", "参数为空");
            return result;
        }
        SysUserShop sysUserShop = sysUserShopService.selectByUserId(userId);
        if (null != sysUserShop) {
            List<SysUserShop> sysUserShopList = sysUserShopService.selectByShopId(sysUserShop.getShopId());
            if (null != sysUserShopList && sysUserShopList.size() != 0) {
                String[] staffIds = new String[sysUserShopList.size()];
                int i = 0;
                for (SysUserShop shop : sysUserShopList) {
                    staffIds[i] = shop.getUserId();
                    i++;
                }
                try {
                    service.updateRecycleBinStatus(staffIds, status);
                } catch (Exception e) {
                    logger.error("[{}]-[{}]时，发生异常，异常信息为[{}]", "SysUserProvide-updateStatus", "updateRecycleBinStatus", e.getMessage());
                    result.put("status", "error");
                    result.put("message", "系统异常");
                    return result;
                }
            }
        }


        try {
            SysUser sysUser = new SysUser();
            sysUser.setUserId(userId);
            sysUser.setStatus(status);
            service.updateUsers(sysUser);
            //1、合作终端；2、b2c本地商家；3、b2b本地商家；4、b2b全国商家；5、运营中心；6、总部  7.服务提供商
            switch (sysUserShop.getShopType()) {
                case 1:
                    if (!shopInfoService.o2oStatusUpdate(sysUserShop.getShopId(), Integer.valueOf(status))) {
                        throw new Exception("o2oStatusUpdate方法执行失败-feign");
                    }
                    break;
                case 2:
                    if (!shopInfoService.b2cStatusUpdate(sysUserShop.getShopId(), Integer.valueOf(status))) {
                        throw new Exception("b2cStatusUpdate方法执行失败-feign");
                    }
                    break;
                case 3:
                    if (!shopInfoService.b2bStatusUpdate(sysUserShop.getShopId(), Integer.valueOf(status))) {
                        throw new Exception("b2bStatusUpdate方法执行失败-feign");
                    }
                    break;
                case 4:
                    if (!shopInfoService.b2bNaStatusUpdate(sysUserShop.getShopId(), Integer.valueOf(status))) {
                        throw new Exception("b2bNaStatusUpdate方法执行失败-feign");
                    }
                    break;
                case 5:
                    if (!shopInfoService.serviceStatusUpdate(sysUserShop.getShopId(), Integer.valueOf(status))) {
                        throw new Exception("serviceStatusUpdate方法执行失败-feign");
                    }
                    break;
                case 7:
                    break;
                default:
                    throw new Exception("shopType不匹配,无法更新shop状态");
            }
            result.put("status", "success");
            result.put("message", "修改成功");
            return result;
        } catch (Exception e) {
            logger.error("[{}]-[{}]时，发生异常，异常信息为[{}]", "SysUserProvide-updateStatus", "updateUsers", e.getMessage());
            result.put("status", "error");
            result.put("message", "系统异常,updateUsers");
            return result;
        }
    }


    @PostMapping("/addUser")
    public int addUsers(@RequestBody SysUserVO sysUserVO) {
        int result = 0;
        try {

            if (StringUtils.isNotBlank(sysUserVO.getUserName())) {
                sysUserVO.setVersion(1);
                sysUserVO.setStatus((short) 1);
                sysUserVO.setRecycleBinStatus((short) 1);
                SysUser sysUser = new SysUser();
                BeanUtils.copyProperties(sysUserVO, sysUser);
                service.addUsers(sysUser);
            }
            result = 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("[{}]-[{}]时，发生异常，异常信息为[{}]", "gumiho-s-admin-user", "addUser", ex.getMessage());
            result = 0;
        }
        return result;
    }

    @GetMapping("/getSysUserByLoginName")
    public SysUserVO getSysUserByLoginName(String loginName) {
        SysUser sysUser = service.getSysUserByLoginName(loginName);
        if (null == sysUser) {
            return null;
        }
        SysUserVO sysUserVO = new SysUserVO();
        BeanUtils.copyProperties(sysUser, sysUserVO);
        return sysUserVO;
    }

    @GetMapping("/getSysUserByUserName")
    public SysUserVO getSysUserByUserName(String userName) {
        SysUser sysUser = service.getSysUserByUserName(userName);
        if (null == sysUser) {
            return null;
        }
        SysUserVO sysUserVO = new SysUserVO();
        BeanUtils.copyProperties(sysUser, sysUserVO);
        return sysUserVO;
    }

    @GetMapping("/checkUsersByPhone")
    public Integer checkUsersByPhone(@RequestParam("phone") String phone) {
        if (null == phone) {
            return null;
        }
        return service.checkUsersByPhone(phone);
    }

    @GetMapping("/checkUsersByLoginName")
    public Integer checkUsersByLoginName(@RequestParam("loginName") String loginName) {
        if (null == loginName) {
            return null;
        }
        return service.checkUsersByLoginName(loginName);
    }

    /**
     * 查询商户手机号是否可以注册
     * @param phone
     * @return
     */
    @GetMapping("/checkUsersForMerchant")
    public Integer checkUsersForMerchant(@RequestParam("phone")String phone){
        if(StringUtils.isBlank(phone)){
            return null;
        }
        return service.checkUsersForMerchant(phone);
    }

    /**
     * updateRecycleBinStatus
     *
     * @param userIds
     * @param recycleBinStatus
     */
    @PostMapping("/updateRecycleBinStatus")
    public Map<String, Object> updateRecycleBinStatus(@RequestBody String[] userIds, @RequestParam("recycleBinStatus") Short recycleBinStatus) {
        Map<String, Object> map = new HashMap<>();
        if (null == userIds || null == recycleBinStatus) {
            map.put("status", "error");
            map.put("message", "传参有误");
            return map;
        }
        try {
            service.updateRecycleBinStatus(userIds, recycleBinStatus);
            map.put("status", "success");
            map.put("message", "修改状态成功");
            return map;
        } catch (Exception e) {
            logger.error("[{}]-[{}]时，发生异常，异常信息为[{}]", "SysUserProvide", "updateRecycleBinStatus", e.getMessage());
            map.put("status", "error");
            map.put("message", "更新状态失败,内部服务器错误");
            return map;
        }
    }

    /**
     * 商户登录本地B2B商城,
     *
     * @param phone
     * @param password
     * @return
     */
    @GetMapping("/B2bLogin")
    public Map<String, Object> B2bLogin(@RequestParam("phone") String phone, @RequestParam("password") String password) {
        Map<String, Object> result = new HashMap<>();

       if(StringUtils.isBlank(password)||StringUtils.isBlank(phone)){
           result.put("status", "fail");
           result.put("message", "内部服务错误,传参失败");
           return result;
       }
        SysUserVO sysUserVO = service.getByPhone(phone);
        if (sysUserVO == null) {
            result.put("status", "fail");
            result.put("message", "该用户不存在");
            return result;
        }
        SysUserShop sysUserShop = sysUserShopService.selectByUserId(sysUserVO.getUserId());


        if (null == sysUserShop) {
            result.put("status", "fail");
            result.put("message", "无权限登录[0]");
            return result;
        }
        if (7 == sysUserShop.getShopType()) {
            result.put("status", "fail");
            result.put("message", "无权限登录[7]");
            return result;
        }
        String adcode = shopInfoService.findAdcodeByShopId(sysUserShop.getShopId(),sysUserShop.getShopType());
        BCryptPasswordEncoder encode = new BCryptPasswordEncoder(4);
        if (encode.matches(password,sysUserVO.getPassword())) {
            result.put("status", "success");
            sysUserVO.setPassword("");
            sysUserVO.setAdcode(adcode);
            sysUserVO.setShopId(sysUserShop.getShopId());
            result.put("user", JSON.toJSONString(sysUserVO));
            return result;
        } else {
            result.put("status", "fail");
            result.put("message", "密码错误，请重试");
            return result;
        }
    }
}