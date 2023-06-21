package com.ds.project01.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.ds.project01.service.PT_ApiService;

@EnableWebSecurity
@Configuration
public class SecurityConfig{
	
	@Autowired
	PT_ApiService service;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable()
                .authorizeRequests(request -> request
                        .antMatchers("/admin/sd").hasRole("ADMIN") // /admin/** 경로에 대한 인증 필요
                        .anyRequest().permitAll() // 그 외의 요청은 모두 허용
                )
                .formLogin(login -> login
                        .loginPage("/admin/login")	// [A] 커스텀 로그인 페이지 지정
                        .loginProcessingUrl("/admin/login-proc")	// [B] submit 받을 url
                        .usernameParameter("userId")	// [C] submit할 아이디
                        .passwordParameter("userPw")	// [D] submit할 비밀번호
                        .defaultSuccessUrl("/admin", true)
                        .failureUrl("/admin/login-error")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/admin/logout") // 로그아웃 URL 설정
                        .logoutSuccessUrl("/") // 로그아웃 후 리디렉션될 URL
                );

        return http.build();
    }

    @Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
    
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    
}