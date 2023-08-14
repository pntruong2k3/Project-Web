package com.project.ecommerc.mart247.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.project.ecommerc.mart247.security.CustomSuccessHandler;
import com.project.ecommerc.mart247.service.serviceImpl.UserDetailsServiceImp;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImp userService;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoder());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/home","/login","/add","/register/*","/css/**","/img/**", "/js/**","/bootstrap.min.css","/save", "/forgotPassword/**", "/calculateTotalPrice", "/calculateTotalProduct","/contact", "/product", "/product/**", "/danh-muc/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login").successHandler(customSuccessHandler())
                .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .and()
            .csrf().disable();
    }
    @Bean
    public CustomSuccessHandler customSuccessHandler() {
    	CustomSuccessHandler customSuccessHandler = new CustomSuccessHandler();
        return customSuccessHandler;
    }
}