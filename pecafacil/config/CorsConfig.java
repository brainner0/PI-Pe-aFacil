package com.example.pecafacil.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        
        // 1. Permite o Angular (Frontend)
        config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        
        // 2. Permite TODOS os métodos (inclusive o PATCH que estava falhando)
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        
        // 3. Permite Cabeçalhos (Token, JSON, etc)
        config.setAllowedHeaders(Arrays.asList("*"));
        
        // 4. Permite credenciais (cookies/auth headers)
        config.setAllowCredentials(true);

        source.registerCorsConfiguration("/**", config);
        
        // Retorna o filtro que roda ANTES da segurança bloquear
        return new CorsFilter(source);
    }
}