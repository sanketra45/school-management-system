package com.eazybytes.eazyschool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http
                // ✅ CSRF using lambda (no deprecation)
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/saveMsg", "/public/**")
                )

                // ✅ Authorization using lambda
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/dashboard", "/displayProfile", "/updateProfile").authenticated()
                        .requestMatchers("/displayMessages/**", "/closeMsg/**", "/admin/**").hasRole("ADMIN")
                        .requestMatchers(
                                "/", "/home",
                                "/holidays/**",
                                "/contact",
                                "/saveMsg",
                                "/courses",
                                "/about",
                                "/login",
                                "/logout",
                                "/admin/messages",
                                "/public/**",
                                "/assets/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )

                // ✅ Form login (already correct)
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/dashboard", true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )

                // ✅ Logout (already correct)
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true)
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
