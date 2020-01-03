package com.scu.fagaiju;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.scu.fagaiju.mapper"})
public class FagaijuApplication {

    public static void main(String[] args) {
        SpringApplication.run(FagaijuApplication.class, args);
    }

}
