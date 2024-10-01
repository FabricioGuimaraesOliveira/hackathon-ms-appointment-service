//package com.fiap.hackathon.appointmentservice.infrastructure.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class CorsConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")  // Permite CORS para todas as rotas
//                .allowedOrigins("*") // Permite todas as origens (modifique conforme necessário)
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Permite todos os métodos HTTP
//                .allowedHeaders("*")  // Permite todos os cabeçalhos
//                .allowCredentials(true);
//    }
//}
