package cn.com.myproject;

import cn.com.myproject.filter.AccessFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableFeignClients
@SpringCloudApplication
@EnableZuulProxy
@EnableRedisHttpSession
@EnableHystrix
public class ZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulApplication.class, args);
	}

	@Bean
	public AccessFilter accessFilter() {
		return new AccessFilter();
	}
}
