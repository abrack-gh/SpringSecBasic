package com.brack.BrankBank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity(debug = true)
public class SpringSecBasicApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecBasicApplication.class, args);
    }

}
