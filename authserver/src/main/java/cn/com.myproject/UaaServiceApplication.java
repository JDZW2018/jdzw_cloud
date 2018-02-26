package cn.com.myproject;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.filter.ForwardedHeaderFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableRedisHttpSession
public class UaaServiceApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(UaaServiceApplication.class, args);
	}


//	@Bean
//	FilterRegistrationBean forwardedHeaderFilter() {
//		FilterRegistrationBean filterRegBean = new FilterRegistrationBean();
//		filterRegBean.setFilter(new ForwardedHeaderFilter());
//		filterRegBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
//		return filterRegBean;
//	}


}
