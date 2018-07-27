package com.johnny.atcrowdfunding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author johnny
 * @create 2018-07-18 上午9:48
 **/
@SpringBootApplication
@MapperScan(value = "com.johnny.atcrowdfunding.dao")
public class SpringBootWebApplication {
    public static void main(String[] args)
    {
        SpringApplication.run(SpringBootWebApplication.class, args);
    }
}