package com.carParts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CarPartsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarPartsApplication.class, args);
    }

}
