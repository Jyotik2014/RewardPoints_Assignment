package com.customer.reward;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.customer.reward")
@EnableAutoConfiguration
public class RewardApplication {

	public static void main(String[] args) {
		SpringApplication.run(RewardApplication.class, args);
	}

}
