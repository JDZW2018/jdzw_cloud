package cn.com.myproject.aliyun.oss.autoconfigure;


import cn.com.myproject.aliyun.oss.AliyunOssService;
import cn.com.myproject.aliyun.oss.prop.AliyunOssProp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AliyunOssProp.class)
@ConditionalOnClass(AliyunOssService.class)
public class AliyunOssAutoConfiguration {

    @Autowired
    private AliyunOssProp aliyunOssProp;

    @Bean
    @ConditionalOnMissingBean(AliyunOssService.class)// 当容器中没有指定Bean的情况下，自动配置PersonService类
    public AliyunOssService aliyunOssService(){
        AliyunOssService aliyunOssService = new AliyunOssService(aliyunOssProp);
        return aliyunOssService;
    }
}
