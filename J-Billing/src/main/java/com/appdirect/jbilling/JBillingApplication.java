package com.appdirect.jbilling;

import java.util.concurrent.Executors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class JBillingApplication {

	public static void main(String[] args) {
		SpringApplication.run(JBillingApplication.class, args);
	}
	
	@Bean("concurrentTaskExecutor")
	public TaskExecutor getConcurrentTaskExecutor(){
		return new ConcurrentTaskExecutor(Executors.newCachedThreadPool());
	}
}
