package nl.novi.gamenight.security;

import nl.novi.gamenight.Repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    public SecurityConfig(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http, PasswordEncoder encoder, UserDetailsService udService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(udService)
                .passwordEncoder(encoder)
                .and()
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsService(this.userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/review/**").hasAuthority("USER")
                .requestMatchers(HttpMethod.POST, "/users/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/auth/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/games/**").hasAuthority("USER")
                .requestMatchers(HttpMethod.PUT, "/games/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/games/**").hasAuthority("ADMIN")

                .requestMatchers("/secret").hasAuthority("ADMIN")
                //.requestMatchers("/**").hasAnyAuthority("USER", "ADMIN")
                .anyRequest().authenticated()
                //.anyRequest().permitAll()
                .and()
                .addFilterBefore(new nl.novi.gamenight.security.JwtRequestFilter(jwtService, userDetailsService()), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }


}
