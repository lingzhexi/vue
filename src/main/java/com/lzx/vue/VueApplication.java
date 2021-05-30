package com.lzx.vue;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lzx.vue.dao")
public class VueApplication {

    public static void main(String[] args) {
        SpringApplication.run(VueApplication.class, args);
    }

}
