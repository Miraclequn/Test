package com.yf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@EnableScheduling   //定时
@EnableTransactionManagement //事务
@ComponentScan   //就是根据定义的扫描路径,把符合扫描规则的类装配到spring容器中
@EnableAutoConfiguration   //
@SpringBootApplication
public class Template {

	public static void main(String[] args) {
		SpringApplication.run(Template.class, args);
	}

}
