package cn.com.myproject.etc;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * 启动类
 * @author ly
 */
@SpringCloudApplication
@ComponentScan("cn.com.myproject")
@EnableFeignClients
public class Application {
//    @Autowired
//    private RestTemplateBuilder builder;
//
//    @Bean
//    public RestTemplate restTemplate() {
//
//        CloseableHttpClient httpClient = HttpClientBuilder.create().setMaxConnPerRoute(200).setMaxConnTotal(200).build();
//        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
//
//        return  builder.requestFactory(factory).build();
//    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
