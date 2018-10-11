package cn.com.myproject.adminuser.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author tianfusheng
 * @date 2018.07.17
 */
@FeignClient(value = "gumiho-s-allinpay", url = "${gumiho-s-allinpay.url}")
public interface AllinpayService {
    /**
     * 创建会员接口
     * @param bizUserId
     * @return
     */
    @GetMapping("/member/createMember")
    Map<String, Object> createMember(@RequestParam("bizUserId") String bizUserId);


    /**
     * 发送短信验证码
     * @param phone
     * @param bizUserId
     * @return
     */
    @GetMapping("/member/sendVerificationCode")
    Map<String, Object> sendVerificationCode(@RequestParam("phone") String phone, @RequestParam("bizUserId") String bizUserId);

    /**
     * 绑定手机号
     * @param phone
     * @param bizUserId
     * @param verificationCode
     * @return
     */
    @GetMapping("/member/bindPhone")
    Map<String, Object> bindPhone(@RequestParam("phone") String phone, @RequestParam("bizUserId") String bizUserId, @RequestParam("verificationCode") String verificationCode);

    @GetMapping("/member/")
    Map<String, Object> getMemberInfo(@RequestParam("bizUserId") String bizUserId);

    }
