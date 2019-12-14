package com.mengo.android.backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.mengo.android.backend.mapper")
public class AndroidBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(AndroidBackendApplication.class, args);
    }

}
