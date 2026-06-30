package com.info.spring.dar.springboot_aplicacion.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web MVC configuration class.
 *
 * Tells Spring where to look for static files that are stored
 * outside the default 'static' folder, such as user-uploaded
 * profile pictures.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Registers a resource handler that maps the /uploads/** URL pattern
     * to the local 'uploads/' directory on the file system.
     *
     * This allows profile pictures to be accessed via:
     * /uploads/filename.jpg
     *
     * @param registry the resource handler registry provided by Spring MVC
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // Map /uploads/** to the local uploads/ folder outside the classpath
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");
    }
}