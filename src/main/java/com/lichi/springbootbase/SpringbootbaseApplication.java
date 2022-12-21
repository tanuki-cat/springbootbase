package com.lichi.springbootbase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

/**
 * @SpringBootApplication 来标注一个主程序类，说明这是一个Spring Boot应用
 * @author lich
 */
@SpringBootApplication
@EnableCaching
//@ComponentScan("com.lichi")
public class SpringbootbaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootbaseApplication.class, args);
    }

}
