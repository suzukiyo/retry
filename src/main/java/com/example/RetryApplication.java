package com.example;

import com.example.configuration.RetryAdvice;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class RetryApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetryApplication.class, args);
	}

	@Bean(name = "retryAdvice")
	public MethodInterceptor retry() {
		return new RetryAdvice().retry();
	}
}
