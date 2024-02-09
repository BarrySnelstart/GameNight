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
                .requestMatchers(HttpMethod.GET, "/log/getlog").hasAnyAuthority("USER")


                .requestMatchers(HttpMethod.POST, "/game/create").hasAnyAuthority("USER")
                .requestMatchers(HttpMethod.DELETE, "/game/delete/{id}").hasAnyAuthority("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/game/update/{id}").hasAnyAuthority("ADMIN")
                 .requestMatchers(HttpMethod.GET, "/game/{id}").hasAnyAuthority("USER")
                .requestMatchers(HttpMethod.GET, "/game/games").permitAll()

                .requestMatchers(HttpMethod.POST, "/auth/**").permitAll()



                                /*TODO Set Userrights*/
                .requestMatchers(HttpMethod.POST, "/user/create").anonymous()
                .requestMatchers(HttpMethod.DELETE, "/user/delete/{id}/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/user/update/{id}/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/user/{id}/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/user/users/**").permitAll()


                .requestMatchers(HttpMethod.POST, "/review/create").hasAnyAuthority("USER")
                .requestMatchers(HttpMethod.DELETE, "/review/delete/{id}").hasAnyAuthority("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/review/update/{id}").hasAnyAuthority("ADMIN")
                .requestMatchers(HttpMethod.GET, "/review/{id}").hasAnyAuthority("USER")
                .requestMatchers(HttpMethod.GET, "/review/reviews").permitAll()

                .requestMatchers(HttpMethod.POST, "/expansion/create").hasAnyAuthority("USER")
                .requestMatchers(HttpMethod.DELETE, "/expansion/delete/{id}").hasAnyAuthority("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/expansion/update/{id}").hasAnyAuthority("ADMIN")
                .requestMatchers(HttpMethod.GET, "/expansion/{id}").hasAnyAuthority("USER")
                .requestMatchers(HttpMethod.GET, "/expansion/expansions").permitAll()

                .anyRequest().authenticated()

                .and()
                .addFilterBefore(new nl.novi.gamenight.security.JwtRequestFilter(jwtService, userDetailsService()), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }


}
