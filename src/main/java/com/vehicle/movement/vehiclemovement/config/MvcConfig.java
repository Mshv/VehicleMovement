package com.vehicle.movement.vehiclemovement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry
        .addResourceHandler("/resources/**")
        .addResourceLocations("/resources/");

    registry.addResourceHandler("/webjars/**",
                                             "/js/**",
                                              "/css/**").
        addResourceLocations("classpath:/META-INF/resources/webjars/",
                             "classpath:/static/js/",
                             "classpath:/static/css/");


  }
}

