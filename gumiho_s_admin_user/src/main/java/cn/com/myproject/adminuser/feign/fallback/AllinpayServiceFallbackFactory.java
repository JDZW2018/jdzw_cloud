package cn.com.myproject.adminuser.feign.fallback;

import cn.com.myproject.adminuser.feign.AllinpayService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author tianfusheng
 * @date 2018.07.17
 */
@Component
public class AllinpayServiceFallbackFactory implements FallbackFactory<AllinpayService> {
    @Override
    public AllinpayService create(Throwable throwable) {
        return new AllinpayService() {
            @Override
            public Map<String, Object> createMember(String bizUserId) {
                return null;
            }

            @Override
            public Map<String, Object> sendVerificationCode(String phone, String bizUserId) {
                return null;
            }

            @Override
            public Map<String, Object> bindPhone(String phone, String bizUserId, String verificationCode) {
                return null;
            }

            @Override
            public Map<String, Object> getMemberInfo(String bizUserId) {
                return null;
            }
        };
    }
}
