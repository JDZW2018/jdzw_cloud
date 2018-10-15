package cn.com.myproject.adminuser;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 *
 * 启动类
 * @author tianfusheng
 */
@SpringCloudApplication
@ComponentScan("cn.com.myproject")
public class Application {
    @Autowired
    private RestTemplateBuilder builder;

    @Bean
    public RestTemplate restTemplate() {

        CloseableHttpClient httpClient = HttpClientBuilder.create().setMaxConnPerRoute(200).setMaxConnTotal(200).build();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);

        return  builder.requestFactory(factory).build();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
