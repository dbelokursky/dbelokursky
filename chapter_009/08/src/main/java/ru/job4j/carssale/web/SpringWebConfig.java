package ru.job4j.carssale.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringWebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
        registry.addResourceHandler("/static/img/**")
                .addResourceLocations("/static/img/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/cars").setViewName("carsList");
        registry.addViewController("/carcard").setViewName("carCard");
        registry.addViewController("/carcardowner").setViewName("carCardOwner");
        registry.addViewController("/add").setViewName("carAdd");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/owners").setViewName("ownersList");
    }
}
