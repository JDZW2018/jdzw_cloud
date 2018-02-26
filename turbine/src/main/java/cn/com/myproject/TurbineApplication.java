package cn.com.myproject;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;

@EnableTurbineStream
@EnableAutoConfiguration
public class TurbineApplication {

	public static void main(String[] args) {
		//SpringApplication.run(TurbineApplication.class, args);
		new SpringApplicationBuilder().sources(TurbineApplication.class).run(args);
	}

}
