package cn.com.myproject.adminuser.externalinterface;

import cn.com.myproject.adminuser.feign.AllinpayService;
import cn.com.myproject.adminuser.service.ISysRoleDepartmentService;
import cn.com.myproject.adminuser.service.ISysUserService;
import cn.com.myproject.adminuser.service.ISysUserShopService;
import cn.com.myproject.adminuser.service.impl.SysDepartmentService;
import cn.com.myproject.adminuser.util.Validation;
import cn.com.myproject.adminuser.vo.SysUserShopVO;
import cn.com.myproject.adminuser.vo.SysUserVO;
import cn.com.myproject.redis.IRedisService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * @author tianfusheng
 * @date 2018/3/20
 */
@RestController
@RequestMapping("/sysUserShop")
@Api(value = "商户注册查询", tags = "商户注册查询相关接口")
public class SysUserShopProvide {
    private static final Logger LOGGER = LoggerFactory.getLogger(SysUserShopProvide.class);
    private static final String OK ="OK";
    private static final String ALLINPAYUSERCREATE = "AllinpayUserCreate";
    private static final String ALLINPAYUSERID = "allinpayUserId";
    private static final String USERID = "userId";
    private static final String BINDSTATUS = "bindStatus";

    @Autowired
    private ISysUserShopService sysUserShopService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysRoleDepartmentService sysRoleDepartmentService;
    @Autowired
    private SysDepartmentService sysDepartmentService;
    @Autowired
    private AllinpayService allinpayService;
    @Autowired
    private IRedisService redisService;

    @ApiOperation(value = "通过userId查询用户关联信息", produces = "application/json")
    @GetMapping("/selectByUserId")
    public SysUserShopVO selectByUserId(String userId) {
        SysUserShopVO sysUserShopVO = null;
        if (null != userId) {
            SysUserShop sysUserShop = sysUserShopService.selectByUserId(userId);
            sysUserShopVO = new SysUserShopVO();
            BeanUtils.copyProperties(sysUserShop, sysUserShopVO);
        }
        return sysUserShopVO;
    }

    /**
     * 查询商户主体
     *
     * @param shopId
     * @return
     */
    @ApiOperation(value = "通过shopId查询商户主体", produces = "application/json")
    @GetMapping("/selectUserByShopId")
    public SysUserShopVO selectUserByShopId(String shopId) {
        SysUserShopVO sysUserShopVO = new SysUserShopVO();
        if (null != shopId) {
            SysUserShop sysUserShop = sysUserShopService.selectUserByShopId(shopId);
            BeanUtils.copyProperties(sysUserShop, sysUserShopVO);
        }
        return sysUserShopVO;
    }

    @GetMapping("/selectStaffByShopId")
    public PageInfo<SysUserVO> selectStaffByShopId(String shopId) {
        if (null != shopId) {
            PageInfo<SysUserVO> sysUserVOPageInfoList = sysUserService.selectStaffByShopId(shopId);
            return sysUserVOPageInfoList;
        }
        return null;
    }

    /**
     * 逻辑：先注册通联用户，把用户信息放入redis，手机号绑定状态bindStatus为error，
     * 同一个手机号再出发送短信，在redis读取用户通联信息，并判断bindStatus是否为error，
     * 为error进行再出发送短信， 为success返回此手机号已经注册不能再发短信。
     * @param phone
     * @return
     */
    @ApiOperation(value = "发送短信", produces = "application/json")
    @GetMapping("/sendVerificationCode")
    public Map<String, Object> sendVerificationCode(String phone) {
        Map<String, Object> result = new HashMap<String, Object>();
        int i = sysUserService.checkUsersByPhone(phone);
        if (i != 0) {
            result.put("status", "error");
            result.put("message", "手机号已经被占用");
            return result;
        }
        Map<String,String> userIdMap = (Map<String, String>) redisService.getHashValue(ALLINPAYUSERCREATE,phone);
        if(null != userIdMap){
            if("success".equals(userIdMap.get("bindStatus"))){
                result.put("status", "error");
                result.put("message", "该手机号已经被注册和使用,请尝试登录或找回密码！");
                return result;
            }
            String userId = userIdMap.get("userId");
            return sendVerificationCode(phone,userId);
        }
        String userId = UUID.randomUUID().toString().replace("-", "");
        Map<String, Object> createMemberMap = null;
        try {
            createMemberMap = allinpayService.createMember(userId);
        } catch (Exception e) {
            LOGGER.error("创建通联用户发生错误，错误信息为[{}]", e.getMessage());
            result.put("status", "error");
            result.put("message", "短信发送失败，内部服务器错误：无法创建内部账户");
            return result;
        }
        if (null == createMemberMap) {
            result.put("status", "error");
            result.put("message", "短信发送失败，内部服务器错误：无法创建账户");
            LOGGER.error("创建通联用户发生错误，错误信息为feign调用MAP为null");
            return result;
        }
            if (OK.equals(createMemberMap.get("status"))) {
            String allinpayUserId = (String) createMemberMap.get("userId");
            Map<String,String> setUserIdMap = new HashMap<>();
            //绑定手机状态，未绑定状态为error,绑定状态为success
            setUserIdMap.put("bindStatus","error");
            setUserIdMap.put("userId",userId);
            setUserIdMap.put("allinpayUserId",allinpayUserId);
            LOGGER.info("setUserMap:[{}]",setUserIdMap.toString());
            redisService.setHashValue(ALLINPAYUSERCREATE,phone,setUserIdMap);
            return sendVerificationCode(phone,userId);
        }else {
            result.put("status","error");
            result.put("message","短信发送失败，创建通联用户失败，稍后重试");
            LOGGER.error("allinpayService.createMember(uuid);执行失败");
            return  result;
        }
    }

    /**
     * 接受code，通过phone去redis上拿到用户信息，判断bindStatus状态，进行通联手机号绑定。
     * 绑定后把bindStatus改为success。
     * @param phone
     * @param code
     * @return
     */
    @ApiOperation(value = "验证短信", produces = "application/json")
    @GetMapping("/checkCode")
    public Map<String,Object> checkCode(String phone , String code){
        Map<String, Object> result = new HashMap<String, Object>();

        Map<String,String> userIdMap = (Map<String, String>) redisService.getHashValue(ALLINPAYUSERCREATE,phone);
        if (null==userIdMap){
            result.put("status", "error");
            result.put("message","手机尚未验证");
            LOGGER.error("redisService.getHashValue(ALLINPAYUSERCREATE,sysUserVO.getPhone())为null");
            return result;
        }
        String userId = userIdMap.get(USERID);
        Map<String, Object> bindPhoneMap = allinpayService.bindPhone(phone,userId,code);

        if(null == bindPhoneMap){
            result.put("status", "error");
            result.put("message", "内部服务器错误");
            LOGGER.error("allinpayService.bindPhone(sysUserVO.getPhone(),sysUserVO.getUserId(),code)调用错误");
            return result;
        }
        if(!OK.equals(bindPhoneMap.get("status"))){
            result.put("status", "error");
            result.put("message","绑定手机号失败,验证码错误");
            LOGGER.error("bindPhoneMap:[{}]",bindPhoneMap.toString());
            return result;
        }else {
            userIdMap.put(BINDSTATUS,"success");
            redisService.setHashValue(ALLINPAYUSERCREATE,phone,userIdMap);
            result.put("status","success");
            return result;
        }
    }

    /**
     * 通联发送短信共用接口。
     * @param phone
     * @param userId
     * @return
     */
    private Map<String,Object> sendVerificationCode(String phone, String userId){
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> sendCodeMap = allinpayService.sendVerificationCode(phone,userId);
        if(null == sendCodeMap){
            result.put("status","error");
            result.put("message","内部服务器错误");
            LOGGER.error("allinpayService.sendVerificationCode(phone,uuid);执行失败");
            return  result;
        }
        if(OK.equals(sendCodeMap.get("status"))){
            result.put("status","success");
            result.put("bizUserId",userId);
            return  result;
        }else {
            result.put("status","error");
            result.put("message","发送短信失败,稍后重试");
            LOGGER.error("allinpayService.sendVerificationCode(phone,uuid);执行失败");
            return  result;
        }
    }

    /**
     * addShopUser 老接口
     *
     * @param sysUserVO
     * @param shopId
     * @param shopType
     * @return
     */
    @ApiOperation(value = "添加商户主体【老接口】", produces = "application/json")
    @PostMapping("/addShopUser")
    public Map<String, Object> addShopUser(@RequestBody SysUserVO sysUserVO, String shopId, Integer shopType) {

        Map<String, Object> result = new HashMap<String, Object>();
        if (StringUtils.isBlank(shopId) || shopType == null || null == sysUserVO || null==sysUserVO.getDepartmentId()) {
            result.put("status", "error");
            result.put("message", "传参有误");
            return result;
        }
        if( null ==sysDepartmentService.selectByDepartmentId(sysUserVO.getDepartmentId())){
            result.put("status", "error");
            result.put("message", "设置的所属系统不存在！");
            return result;
        }

        int shopUserNumber = sysUserShopService.checkShopUserNumber(shopId);
        if (shopUserNumber != 0 ) {
            result.put("status", "error");
            result.put("message", "Shop主体已经存在或shopId重复！");
            return result;
        }
        if (0 != sysUserService.checkUsersByPhone(sysUserVO.getPhone())) {
            result.put("status", "error");
            result.put("message", "手机号已经被占用");
            return result;
        }
        if (sysUserVO.getLoginName() == null) {
            sysUserVO.setLoginName(shopId);
        }
        if (null != sysUserService.getByLoginName(sysUserVO.getLoginName())) {
            result.put("status", "error");
            result.put("message", "登录名不可重复");
            return result;
        }
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserVO, sysUser);
        sysUser.setCreateTime(System.currentTimeMillis());
        sysUser.setStatus((short) 1);
        sysUser.setVersion(1);

        //sysUser.setRecycleBinStatus((short) 2);//由前端设置状态为2

        SysUserDepartment sysUserDepartment = new SysUserDepartment();
        sysUserDepartment.setDepartmentId(sysUserVO.getDepartmentId());
        sysUserDepartment.setUserId(sysUserVO.getUserId());
        sysUserDepartment.setStatus((short) 1);
        sysUserDepartment.setVersion(1);
        sysUserDepartment.setCreatTime(System.currentTimeMillis());

        SysUserShop sysUserShop = new SysUserShop();
        sysUserShop.setUserId(sysUserVO.getUserId());
        sysUserShop.setShopId(shopId);
        sysUserShop.setShopType(shopType);
        sysUserShop.setCreatTime(System.currentTimeMillis());
        sysUserShop.setStatus((short) 1);
        sysUserShop.setUserType((short)1);
        sysUserShop.setVersion(1);
        List<SysRoleDepartment> sysRoleDepartmentList=null;
        try {
            sysRoleDepartmentList = sysRoleDepartmentService.selectByDepartmentId (sysUserVO.getDepartmentId());
        }catch (Exception e){
            LOGGER.error("sysRoleDepartmentService.selectByDepartmentId(sysUserVO.getDepartmentId())发生发错,错误信息为:[{}]",e.getMessage());
            result.put("status", "error");
            result.put("message", "插入失败,内部服务器错误");
            return result;
        }

        List<SysUserRole> sysUserRoleList = new ArrayList<SysUserRole>();
        SysUserRole sysUserRole = null;
        for(SysRoleDepartment sysRoleDepartment :sysRoleDepartmentList){
            sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(sysRoleDepartment.getRoleId());
            sysUserRole.setUserId(sysUserVO.getUserId());
            sysUserRole.setStatus((short)1);
            sysUserRole.setVersion(1);
            sysUserRole.setCreateTime(System.currentTimeMillis());
            sysUserRoleList.add(sysUserRole);
        }
        try {
            sysUserShopService.addShopUser(sysUser, sysUserShop, sysUserDepartment,sysUserRoleList);
            result.put("status", "success");
            result.put("message", "添加成功");
            return result;
        } catch (BeansException e) {
            LOGGER.error("插入ShopUser商户关联用户失败,addShopUser-SysUserShopProvide-gumiho_s_admin_user" + e.getMessage());
            result.put("status", "error");
            result.put("message", "系统异常");
            return result;
        }
    }


    /**
     * 通过phone获取redis上的用户数据，bindStatus为success，进行创建用户，初始化用户权限和所属子系统。
     *
     * @param sysUserVO
     * @param shopId
     * @param shopType
     * @return
     */
    @ApiOperation(value = "添加商户主体【通联新接口】", produces = "application/json")
    @PostMapping("/addShopUserWithAllinpay")
    public Map<String, Object> addShopUserWithAllinpay(@RequestBody SysUserVO sysUserVO, String shopId, Integer shopType) {

        Map<String, Object> result = new HashMap<String, Object>();
        if (StringUtils.isBlank(shopId) || shopType == null || null == sysUserVO || null == sysUserVO.getDepartmentId()) {
            result.put("status", "error");
            result.put("message", "传参有误");
            return result;
        }
        if (null == sysDepartmentService.selectByDepartmentId(sysUserVO.getDepartmentId())) {
            result.put("status", "error");
            result.put("message", "设置的所属系统不存在！");
            return result;
        }

        int shopUserNumber = sysUserShopService.checkShopUserNumber(shopId);
        if (shopUserNumber != 0) {
            result.put("status", "error");
            result.put("message", "Shop主体已经存在或shopId重复！");
            return result;
        }
        if (0 != sysUserService.checkUsersByPhone(sysUserVO.getPhone())) {
            result.put("status", "error");
            result.put("message", "手机号已经被占用");
            return result;
        }
        if (sysUserVO.getLoginName() == null) {
            sysUserVO.setLoginName(shopId);
        }
        if (null != sysUserService.getByLoginName(sysUserVO.getLoginName())) {
            result.put("status", "error");
            result.put("message", "登录名不可重复");
            return result;
        }

        Map<String,String> userIdMap = (Map<String, String>) redisService.getHashValue(ALLINPAYUSERCREATE,sysUserVO.getPhone());
        if (null==userIdMap){
            result.put("status", "error");
            result.put("message","手机尚未验证");
            LOGGER.error("redisService.getHashValue(ALLINPAYUSERCREATE,sysUserVO.getPhone())为null");
            return result;
        }
        String bindStatus = userIdMap.get(BINDSTATUS);
        if(!"success".equals(bindStatus)){
            result.put("status", "error");
            result.put("message","手机尚未验证绑定");
            LOGGER.error("bindStatus != success");
            return result;
        }
        String userId = userIdMap.get(USERID);
        String allinpayUserId = userIdMap.get(ALLINPAYUSERID);
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserVO, sysUser);
        sysUser.setAllinpayUserId(allinpayUserId);
        sysUser.setUserId(userId);
        sysUser.setCreateTime(System.currentTimeMillis());
        sysUser.setStatus((short) 1);
        sysUser.setVersion(1);

        //sysUser.setRecycleBinStatus((short) 2);//由前端设置状态为2

        SysUserDepartment sysUserDepartment = new SysUserDepartment();
        sysUserDepartment.setDepartmentId(sysUserVO.getDepartmentId());
        sysUserDepartment.setUserId(sysUserVO.getUserId());
        sysUserDepartment.setStatus((short) 1);
        sysUserDepartment.setVersion(1);
        sysUserDepartment.setCreatTime(System.currentTimeMillis());

        SysUserShop sysUserShop = new SysUserShop();
        sysUserShop.setUserId(sysUserVO.getUserId());
        sysUserShop.setShopId(shopId);
        sysUserShop.setShopType(shopType);
        sysUserShop.setCreatTime(System.currentTimeMillis());
        sysUserShop.setStatus((short) 1);
        sysUserShop.setUserType((short) 1);
        sysUserShop.setVersion(1);
        List<SysRoleDepartment> sysRoleDepartmentList = null;
        try {
            sysRoleDepartmentList = sysRoleDepartmentService.selectByDepartmentId(sysUserVO.getDepartmentId());
        } catch (Exception e) {
            LOGGER.error("sysRoleDepartmentService.selectByDepartmentId(sysUserVO.getDepartmentId())发生发错,错误信息为:[{}]", e.getMessage());
            result.put("status", "error");
            result.put("message", "插入失败,内部服务器错误");
            return result;
        }

        List<SysUserRole> sysUserRoleList = new ArrayList<SysUserRole>();
        SysUserRole sysUserRole = null;
        for (SysRoleDepartment sysRoleDepartment : sysRoleDepartmentList) {
            sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(sysRoleDepartment.getRoleId());
            sysUserRole.setUserId(sysUserVO.getUserId());
            sysUserRole.setStatus((short) 1);
            sysUserRole.setVersion(1);
            sysUserRole.setCreateTime(System.currentTimeMillis());
            sysUserRoleList.add(sysUserRole);
        }
        try {
            sysUserShopService.addShopUser(sysUser, sysUserShop, sysUserDepartment, sysUserRoleList);
            result.put("status", "success");
            result.put("message", "添加成功");
            return result;
        } catch (BeansException e) {
            LOGGER.error("插入ShopUser商户关联用户失败,addShopUser-SysUserShopProvide-gumiho_s_admin_user" + e.getMessage());
            result.put("status", "error");
            result.put("message", "系统异常");
            return result;
        }
    }


    /**
     * addShopStaff
     *
     * @param sysUserVO
     * @param shopId
     * @param shopType
     * @return
     */
    @PostMapping("/addShopStaff")
    public Map<String, Object> addShopStaff(@RequestBody SysUserVO sysUserVO, String shopId, Integer shopType) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (StringUtils.isBlank(shopId) || shopType == null || null == sysUserVO || null == sysUserVO.getDepartmentId()) {
            result.put("status", "error");
            result.put("message", "传参有误");
            return result;
        }
        if (null == sysUserShopService.selectUserByShopId(shopId)) {
            result.put("status", "error");
            result.put("message", "商户主体验证有误！");
            return result;
        }
        if (null == sysDepartmentService.selectByDepartmentId(sysUserVO.getDepartmentId())) {
            result.put("status", "error");
            result.put("message", "设置的所属系统不存在！");
            return result;
        }
        int shopUserNumber = sysUserShopService.checkShopUserNumber(shopId);
        if (shopUserNumber >= 5) {
            result.put("status", "error");
            result.put("message", "插入的ShopId关联用户超出限量,默认最多为4个!");
            return result;
        }
        if (0 != sysUserService.checkUsersByPhone(sysUserVO.getPhone())) {
            result.put("status", "error");
            result.put("message", "手机号已经被占用");
            return result;
        }
        if (sysUserVO.getLoginName() == null) {
            sysUserVO.setLoginName(shopId);
        }
        Boolean flag = Validation.isPermitName(sysUserVO.getLoginName());
        if (!flag) {
            result.put("status", "fail");
            result.put("message", "登录名不可含有特殊字符！");
            return result;
        }

        if (null != sysUserService.getByLoginName(sysUserVO.getLoginName())) {
            result.put("status", "error");
            result.put("message", "登录名不可重复");
            return result;
        }
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserVO, sysUser);
        sysUser.setCreateTime(System.currentTimeMillis());
        sysUser.setStatus((short) 1);
        sysUser.setVersion(1);
        sysUser.setRecycleBinStatus((short) 1);

        SysUserDepartment sysUserDepartment = new SysUserDepartment();
        sysUserDepartment.setDepartmentId(sysUserVO.getDepartmentId());
        sysUserDepartment.setUserId(sysUserVO.getUserId());
        sysUserDepartment.setStatus((short) 1);
        sysUserDepartment.setVersion(1);
        sysUserDepartment.setCreatTime(System.currentTimeMillis());

        SysUserShop sysUserShop = new SysUserShop();
        sysUserShop.setUserId(sysUserVO.getUserId());
        sysUserShop.setShopId(shopId);
        sysUserShop.setShopType(shopType);
        sysUserShop.setCreatTime(System.currentTimeMillis());
        sysUserShop.setStatus((short) 1);
        sysUserShop.setUserType((short) 0);
        sysUserShop.setVersion(1);
        try {
            sysUserShopService.addShopStaff(sysUser, sysUserShop, sysUserDepartment);
            result.put("status", "success");
            result.put("message", "添加成功");
            return result;
        } catch (BeansException e) {
            LOGGER.error("[{}]-[{}]时，发生异常，异常信息为[{}]", "gumiho-s-admin-user", "addShopStaff", e.getMessage());
            result.put("status", "error");
            result.put("message", "系统异常");
            return result;
        }
    }

    /**
     * delShopUser
     *
     * @param userIds
     * @param shopId
     * @return
     */
    @PostMapping("/delShopUser")
    public Map<String, Object> delShopUser(@RequestBody String[] userIds, @RequestParam("shopId") String shopId) {

        Map<String, Object> result = new HashMap<String, Object>();

        if (StringUtils.isBlank(shopId) || StringUtils.isBlank(shopId)) {
            result.put("status", "error");
            result.put("message", "传参有误");
            return result;
        }
        try {
            for (String userId : userIds) {
                SysUserShop sysUserShop = sysUserShopService.selectByUserId(userId);
                if (!sysUserShop.getShopId().equals(shopId)) {
                    result.put("status", "error");
                    result.put("message", "传入参数不合法");
                    return result;
                }
            }
        } catch (Exception e) {
            LOGGER.error("[{}]-[{}]时，发生异常，异常信息为[{}]", "gumiho-s-admin-user", "selectByUserId--判断userd和shopId的对应关系", e.getMessage());
            result.put("status", "error");
            result.put("message", "server服务器异常");
            return result;
        }
        try {
            for (int i = 0; i < userIds.length; i++) {
                sysUserShopService.delShopUser(userIds[i]);
            }
            result.put("status", "success");
            result.put("message", "删除成功");
            return result;
        } catch (Exception e) {
            LOGGER.error("[{}]-[{}]时，发生异常，异常信息为[{}]", "SysUserShopProvide", "delShopUser", e.getMessage());
            result.put("status", "error");
            result.put("message", "SysUserService异常");
            return result;
        }
    }
}
