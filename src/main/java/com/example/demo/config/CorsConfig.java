package com.example.demo.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();

        // Cho phép gửi cookie / Authorization header (JWT)
        config.setAllowCredentials(true);

        // ✅ ALLOWED ORIGINS (LOCAL + VERCEL)
        config.setAllowedOrigins(Arrays.asList(
                "http://localhost:3000",
                "http://localhost:4200",
                "http://localhost:5173", // Vite local
                "https://shoptrangsuc.vercel.app",
                "https://shoptrangsuc-l2lvyl663-rootmay2022s-projects.vercel.app"
        ));

        // Cho phép tất cả headers
        config.setAllowedHeaders(Arrays.asList("*"));

        // Các method được phép
        config.setAllowedMethods(Arrays.asList(
                "GET", "POST", "PUT", "DELETE", "OPTIONS"
        ));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
