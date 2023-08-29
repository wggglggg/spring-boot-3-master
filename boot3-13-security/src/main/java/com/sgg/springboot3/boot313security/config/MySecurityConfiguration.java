package com.sgg.springboot3.boot313security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


/**
 * ClassName: MySecurityConfiguration
 * Description:
 * 1、自定义请求授权规则：http.authorizeHttpRequests
 * 2、自定义登录规则：http.formLogin
 * 3、自定义用户信息查询规则：UserDetailsService
 * 4、开启方法级别的精确权限控制：@EnableMethodSecurity + @PreAuthorize("hasAuthority('world_exec')")
 * @Author wggglggg
 * @Create 2023-08-29 9:05
 * @Version 1.0
 */

@EnableMethodSecurity
@Configuration
public class MySecurityConfiguration {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        //请求授权
        http.authorizeHttpRequests(authorizeHttpRequest -> {
            authorizeHttpRequest.requestMatchers("/").permitAll()   //1、首页所有人都允许
                    .anyRequest().authenticated();                          //2、剩下的任意请求都需要 认证（登录）

        });

        //表单登录
        //3、表单登录功能：开启默认表单登录功能；Spring Security提供默认登录页
        http.formLogin(formLoginConfigurer -> {
            formLoginConfigurer.loginPage("/login").permitAll();    //自定义登录页位置，并且所有人都能访问
        });

        http.logout(logout -> logout.permitAll());

        return http.build();
    }

    @Bean
    UserDetailsService userDetailsService(PasswordEncoder passwordEncoder){

        UserDetails suisen = User.withUsername("suisen")
                .password(passwordEncoder.encode("abc123"))
                .roles("admin","accountant")
                .authorities("read", "write")
                .build();

        UserDetails wggglggg = User.withUsername("wggglggg")
                .password(passwordEncoder.encode("abc123"))
                .roles("admin", "it")
                .authorities("read")
                .build();

        UserDetails zhaixuan = User.withUsername("zhaixuan")
                .password(passwordEncoder.encode("abc123"))
                .authorities("read", "exec")    // 只能zhaixuan能访问world页面
                .roles("pr")
                .build();

        //默认内存中保存所有用户信息 InMemoryUserDetailsManager(suisen, wggglggg, zhaixuan);
        return new InMemoryUserDetailsManager(suisen, wggglggg, zhaixuan);
    }

    // UserDetailService需要加密password,接受此方法加密
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
