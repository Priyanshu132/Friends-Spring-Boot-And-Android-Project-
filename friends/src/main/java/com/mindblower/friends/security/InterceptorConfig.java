package com.mindblower.friends.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@EnableWebMvc
@Component
public class InterceptorConfig extends WebMvcConfigurationSupport{

	

	@Override
	public void addInterceptors(final InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(new Interceptor()).addPathPatterns("/**");
	}
	
	
}
