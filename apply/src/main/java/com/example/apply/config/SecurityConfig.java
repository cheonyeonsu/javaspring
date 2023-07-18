package com.example.apply.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration //bean객체 싱글톤으로 공유
@EnableWebSecurity //spring security filterChain 자동 포함. 비밀번호 입력해야 접근가능. 
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		//1. 페이지 접근에 관한 설정
		http.authorizeHttpRequests(authorize -> authorize 
				.requestMatchers("/css/**","/js/**","/img/**","/images/**","/fonts/**").permitAll()
				.requestMatchers("/","/members/**","item/**","").permitAll() 
				.requestMatchers("/favicon.ico/","/error").permitAll() 
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().authenticated()
				)
		//2.로그인에 관련된 설정. 
		.formLogin(formLogin -> formLogin
				.loginPage("/members/login") //로그인 페이지 url 설정
				.defaultSuccessUrl("/") //로그인 성공시 이동할 페이지
				.usernameParameter("userId") //로그인 시 id로 사용할 파마레터 이름
				.failureUrl("/members/login/error") //로그인 실패 시 이동할 url
				)
		
		//3. 로그아웃에 관련된 설정
			.logout(logout -> logout
					.logoutRequestMatcher(new AntPathRequestMatcher("/members/logout")) //로그아웃 시 이동할 Url 설정
					.logoutSuccessUrl("/") //로그아웃 성공하면 메인페이지로 이동하겠음!
					.permitAll()
					)
			
		//4. 인증되지 않은 사용자가 리소스에 접근했을 때 설정
			.exceptionHandling(handling -> handling
					.authenticationEntryPoint(new CustomAuthenticationEntryPoint())
					)
			.rememberMe(Customizer.withDefaults());
		
		return http.build();
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
