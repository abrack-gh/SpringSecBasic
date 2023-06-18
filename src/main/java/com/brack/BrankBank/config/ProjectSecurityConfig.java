package com.brack.BrankBank.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{

//CUSTOM SECURITY CONFIGURATIONS
        http.authorizeHttpRequests()
                        .requestMatchers("/my-account", "/my-balance", "/my-cards").authenticated()
                        .requestMatchers("/notices", "/contact").permitAll()
                .and().formLogin()
                .and().httpBasic();
        return http.build();



//DENY ALL TEST
//        http.authorizeHttpRequests()
//                .anyRequest().denyAll()
//                .and().formLogin()
//                .and().httpBasic();
//        return http.build();



// PERMIT ALL REQUESTS TEST
//        http.authorizeHttpRequests()
//                .anyRequest().permitAll()
//                .and().formLogin()
//                .and().httpBasic();
//        return http.build();

    }


    //NOT RECOMMENDED FOR PROD
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        //Approach 1

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .authorities("admin")
                .build();

        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user")
                .authorities("user")
                .build();

        return new InMemoryUserDetailsManager(admin, user);

        //Approach 2

//            UserDetails admin = User.withUsername("admin").password("admin").authorities("admin").build();
//
//            UserDetails user = User.withUsername("user").password("user").authorities("user").build();
//
//            return new InMemoryUserDetailsManager(admin, user);
        }

    }

//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }




