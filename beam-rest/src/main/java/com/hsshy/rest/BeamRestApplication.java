package com.hsshy.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication(scanBasePackages = {"com.hsshy.rest","com.hsshy.jpa"})
public class BeamRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeamRestApplication.class, args);
    }
}
