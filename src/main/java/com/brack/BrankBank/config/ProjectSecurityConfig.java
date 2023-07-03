package com.brack.BrankBank.config;

import com.brack.BrankBank.filter.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");

//CUSTOM SECURITY CONFIGURATIONS
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .cors().configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration configuration = new CorsConfiguration();
                        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:4200")); //Revoke CORS from backend application from front end
                        configuration.setAllowedMethods(Collections.singletonList("*"));
                        configuration.setAllowCredentials(true);
                        configuration.setAllowedHeaders(Collections.singletonList("*"));
                        configuration.setExposedHeaders(Arrays.asList("Authorization"));
                        configuration.setMaxAge(3600L);

                        return configuration;
                    }

                    ;
                }).and().csrf().disable()
                .addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests()
                .requestMatchers(toH2Console()).permitAll()
                .requestMatchers("/my-account").hasAnyRole("USER", "ADMIN") //Any AUTHORITY
                .requestMatchers("/my-balance").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/my-loans").authenticated()
//                .requestMatchers("/my-loans").hasAnyRole("USER", "ADMIN") // ONLY THOSE WITH VIEW LOANS AUTH
                .requestMatchers("/my-cards").hasAnyRole("USER", "ADMIN") // ONY
                .requestMatchers("/user").authenticated()
                .requestMatchers("/notices", "/register","/h2-console").permitAll()

                .and().formLogin()
                .and().httpBasic();

        http
                .headers().frameOptions().disable();
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


    //NOT FOR PROD
//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager() {
//
//        //Approach 1
//
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("admin")
//                .authorities("admin")
//                .build();
//
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("user")
//                .authorities("user")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin, user);
//
//        //Approach 2
//
////            UserDetails admin = User.withUsername("admin").password("admin").authorities("admin").build();
////
////            UserDetails user = User.withUsername("user").password("user").authorities("user").build();
////
////            return new InMemoryUserDetailsManager(admin, user);
//        }
//
//    }

//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource){
//        return new JdbcUserDetailsManager(dataSource);
//    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}




