package ru.job4j.carssale.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringWebConfig implements WebMvcConfigurer {

    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
        registry.addResourceHandler("/static/img/**")
                .addResourceLocations("/static/img/");
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file://" + uploadPath);
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
