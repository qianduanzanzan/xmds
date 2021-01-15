package com.atxiaoming;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "com.atxiaoming")
@MapperScan("com.atxiaoming.mapper")
@EnableTransactionManagement(proxyTargetClass=true)
public class XmdsApplication {

	public static void main(String[] args) {
		SpringApplication.run(XmdsApplication.class, args);
	}

}
