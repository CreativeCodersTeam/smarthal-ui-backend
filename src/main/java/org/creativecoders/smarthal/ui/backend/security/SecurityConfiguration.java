package org.creativecoders.smarthal.ui.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            //.csrf().disable()
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/api/**").permitAll()
            );
//            .authorizeHttpRequests(authorize -> authorize
//                .anyRequest().authenticated()
//            )
//            .formLogin(withDefaults())
//            .httpBasic(withDefaults());
        return http.build();
    }
}
