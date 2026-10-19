package com.example.bitxchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling

@SpringBootApplication(scanBasePackages = "com.example.bitxchange")
public class BitXchangeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BitXchangeApplication.class, args);
    }

}
