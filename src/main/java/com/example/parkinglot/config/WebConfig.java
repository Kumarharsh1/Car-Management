package com.example.parkinglot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")  // ✅ Only apply to API endpoints
                .allowedOrigins(
                    "https://car-management-eosin-nine.vercel.app", // Your Vercel frontend
                    "http://localhost:3000",                        // Local development
                    "https://car-management-1-46vz.onrender.com"    // Render URL (if needed)
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)  // ✅ Enable credentials if needed
                .maxAge(3600);           // ✅ Cache preflight response for 1 hour
    }
}