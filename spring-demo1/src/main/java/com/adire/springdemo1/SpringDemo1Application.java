package com.adire.springdemo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.adire.springdemo1.*"})
public class SpringDemo1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringDemo1Application.class, args);
    }

}
