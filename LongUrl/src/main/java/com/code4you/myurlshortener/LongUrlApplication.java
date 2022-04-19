package com.code4you.myurlshortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class LongUrlApplication {

	public static void main(String[] args) {
		SpringApplication.run(LongUrlApplication.class, args);
	}

}
