//package com.fiap.hackathon.appointmentservice.infrastructure.configuration;
//
//import com.fiap.hackathon.appointmentservice.infrastructure.authentication.JwtAuthenticationFilter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//
//import java.util.Arrays;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    private final JwtAuthenticationFilter jwtAuthenticationFilter;
//
//    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
//        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())  // Desativa o CSRF explicitamente
//                .cors(cors -> cors.configurationSource(corsConfigurationSource()))  // Ativa o CORS com configuração customizada
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/swagger-resources/**", "/swagger-resources", "/v3/api-docs/**", "/proxy/**").permitAll()  // Permite acesso ao Swagger
//                        .requestMatchers("/public/**").permitAll()  // Permite acesso a rotas públicas
//                        .requestMatchers("/agendamentos/consultar").permitAll()  // Permite acesso à rota de consulta de agendamentos
//                        .anyRequest().authenticated()  // Qualquer outra rota exige autenticação
//                )
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);  // Adiciona o filtro JWT antes da autenticação padrão
//
//        return http.build();
//    }
//
//    // Configuração CORS
//    @Bean
//    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowedOrigins(Arrays.asList("http://localhost:8080"));  // Defina as origens permitidas (adicione seus domínios)
//        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
//        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
//        config.setAllowCredentials(true);  // Permite credenciais (cookies, headers de autenticação)
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", config);  // Aplica a configuração de CORS para todos os endpoints
//        return source;
//    }
//}
