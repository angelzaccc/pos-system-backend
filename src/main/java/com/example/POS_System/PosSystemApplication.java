package com.example.POS_System;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 👈 Add 'scanBasePackages' here to force Spring to find your controller!
@SpringBootApplication(scanBasePackages = {"com.example.POS_System", "com.example.POS_System.controller"})
public class PosSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(PosSystemApplication.class, args);
    }

}