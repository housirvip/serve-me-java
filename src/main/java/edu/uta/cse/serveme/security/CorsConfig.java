package edu.uta.cse.serveme.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author housirvip
 */
@Slf4j
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Value("${cors.path}")
    private String path;

    @Value("${cors.origins}")
    private String[] origins;

    @Value("${cors.methods}")
    private String[] methods;

    @Value("${cors.headers}")
    private String[] headers;

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {

        corsRegistry.addMapping(path)
                .allowedOrigins(origins)
                .allowCredentials(true)
                .allowedMethods(methods)
                .allowedHeaders(headers);
    }
}
