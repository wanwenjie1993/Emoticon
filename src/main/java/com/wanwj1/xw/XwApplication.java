package com.wanwj1.xw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("com.wanwj1.xw.dao")
//添加servlet组件扫描，使得Spring能够扫描到我们编写的servlet和filter
@ServletComponentScan
public class XwApplication {
    public static void main(String[] args) {
        SpringApplication.run(XwApplication.class,args);
    }
}

