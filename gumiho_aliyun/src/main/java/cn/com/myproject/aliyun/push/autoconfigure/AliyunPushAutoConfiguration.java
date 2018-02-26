package cn.com.myproject.aliyun.push.autoconfigure;

import cn.com.myproject.aliyun.push.AliyunPushService;
import cn.com.myproject.aliyun.push.prop.AliyunPushProp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AliyunPushProp.class)
@ConditionalOnClass(AliyunPushService.class)
public class AliyunPushAutoConfiguration {

    @Autowired
    private AliyunPushProp aliyunPushProp;

    @Bean
    @ConditionalOnMissingBean(AliyunPushService.class)// 当容器中没有指定Bean的情况下，自动配置PersonService类
    public AliyunPushService aliyunPushService(){
        AliyunPushService aliyunPushService = new AliyunPushService(aliyunPushProp);
        return aliyunPushService;
    }
}
