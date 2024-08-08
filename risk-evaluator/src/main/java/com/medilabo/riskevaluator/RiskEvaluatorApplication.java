package com.medilabo.riskevaluator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RiskEvaluatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(RiskEvaluatorApplication.class, args);
	}

}
