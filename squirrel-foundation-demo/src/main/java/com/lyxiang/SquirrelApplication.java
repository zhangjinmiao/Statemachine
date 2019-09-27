package com.lyxiang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.lyxiang"})
@MapperScan(basePackages = "com.lyxiang.dao")
public class SquirrelApplication {

    public static void main(String[] args) {
        SpringApplication.run(SquirrelApplication.class, args);
    }

}
