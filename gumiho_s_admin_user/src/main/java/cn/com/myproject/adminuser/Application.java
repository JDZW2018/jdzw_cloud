package cn.com.myproject.adminuser;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 *
 * 启动类
 * @author ly
 */
@SpringCloudApplication
@EnableFeignClients
@ComponentScan("cn.com.myproject")
public class Application {

    @Bean
    public ClientHttpRequestFactory myClientHttpRequestFactory() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().setMaxConnPerRoute(200).setMaxConnTotal(200).build();
        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }

    @Bean
    public RestTemplate restTemplate(@Qualifier("myClientHttpRequestFactory") ClientHttpRequestFactory requestFactory) {
        return  new RestTemplate(requestFactory);
    }



    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
