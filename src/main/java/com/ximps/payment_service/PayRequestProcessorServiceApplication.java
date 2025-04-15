package com.ximps.payment_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableJpaAuditing
@EnableFeignClients
@ComponentScan("com.ximps")
public class PayRequestProcessorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayRequestProcessorServiceApplication.class, args);
	}

}
