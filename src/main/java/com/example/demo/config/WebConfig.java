package com.example.demo.config;

import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${file.upload-dir:uploads}")
    private String uploadDir;

    // 1. Cấu hình CORS để sửa lỗi "blocked by CORS policy"
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") 
                // Cho phép cả localhost (để test) và domain Vercel của bạn
                .allowedOriginPatterns("https://shoptrangsuc.vercel.app", "http://localhost:*") 
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600); // Cache cấu hình CORS trong 1 giờ để tăng hiệu năng
    }

    // 2. Cấu hình hiển thị ảnh từ thư mục uploads
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Lấy đường dẫn tuyệt đối để tránh lỗi không tìm thấy file khi chạy trên server (Railway)
        String absolutePath = Paths.get(uploadDir).toAbsolutePath().toUri().toString();
        
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(absolutePath);
    }
}