package com.meritamerica.assignment7;

import java.util.Arrays;

import javax.servlet.Filter;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.meritamerica.assignment7.filters.JwtRequestFilter;
import com.meritamerica.assignment7.services.MeritBankServiceImpl;
import com.meritamerica.assignment7.services.MeritUserDetailsService;


@EnableWebSecurity
@EnableGlobalMethodSecurity( 
		  securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	MeritUserDetailsService meritBankSvc;
	
	@Autowired
	JwtRequestFilter jwtRequestFilter;
	
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(meritBankSvc);
	}
	
	@Bean
	public PasswordEncoder encoder() {
	    return NoOpPasswordEncoder.getInstance();
	}
	

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.cors().and().csrf().disable();
		 httpSecurity.authorizeRequests()
         .antMatchers("/authenticate").permitAll()
         .antMatchers("/h2-console/**").permitAll()
         .antMatchers("/v3/api-docs/**").permitAll()
		 .antMatchers("/swagger-ui/**").permitAll()
		 .antMatchers("/swagger-ui.html").permitAll()
         .anyRequest().authenticated().and()
			.exceptionHandling().and().sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		//httpSecurity.headers().frameOptions().disable();
		//httpSecurity.authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll();

		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	
}
