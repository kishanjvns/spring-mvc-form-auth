package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends AbstractSecurityWebApplicationInitializer {
    private Logger log = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    public AuthenticationProvider casenetAuthProvider(UserDetailsService userDetailsService) {
        return new CasenetAuthProvider(userDetailsService, true); // Adjust the boolean flag as required
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(request -> request
                        .requestMatchers("/security/public").permitAll()
                        .requestMatchers("/security/admin").hasRole("ROLE_ADMIN")
                        .requestMatchers("/security/user").hasRole("ROLE_USER")
                        .requestMatchers("/security/greet").hasAnyRole("ROLE_USER","ROLE_ADMIN")
                        .anyRequest()
                        .authenticated()
                )
                .sessionManagement(
                        session -> session
                                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .formLogin(Customizer.withDefaults());

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http, AuthenticationProvider casenetAuthProvider) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class).authenticationProvider(casenetAuthProvider).build();
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        UserDetails user1 = User.builder().username("Jim@test.com").password("password") // {noop} is for plain text passwords
                .roles("USER").build();

        UserDetails user2 = User.builder().username("Kim@test.com").password("password").roles("ADMIN").build();

        return new InMemoryUserDetailsManager(user1, user2);
    }


}
