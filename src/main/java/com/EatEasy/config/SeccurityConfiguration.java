package com.EatEasy.config;


import com.EatEasy.Repository.UserDetailsRepository;
import com.EatEasy.Services.UserDetailServiceImpl;
import com.EatEasy.auth.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SeccurityConfiguration {

    private final UserDetailsRepository userDetailsRepository;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
        RequestMatcher h2ConsoleMatcher = new AntPathRequestMatcher("/h2-console/**");
        http
                .csrf(csrf -> csrf.disable())
                .cors()
                .and()
                .headers().frameOptions().disable() // Deshabilitar frameOptions para permitir la consola H2
                .and()
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(mvc.pattern("/api/restaurants")).permitAll()             //Aqui son los enlaces a los cuales les permitimos ver los jugadores y equipos
                        .requestMatchers(mvc.pattern("/api/restaurants/search")).permitAll()
                        .requestMatchers(mvc.pattern("/api/restaurants/{id}")).permitAll()
                        .requestMatchers(mvc.pattern("/api/reviews")).permitAll()
                        .requestMatchers(mvc.pattern("/api/reviews/restaurant/{restaurantId}")).permitAll()
                        .requestMatchers(mvc.pattern("/api/reviews/{id}/user")).permitAll()
                        .requestMatchers(mvc.pattern("/api/reviews/{id}/userProfilePicture")).permitAll()
                        .requestMatchers(mvc.pattern("/api/reviews/*/userProfilePicture")).permitAll()
                        .requestMatchers(mvc.pattern("/api/auth/users")).permitAll()
                        .requestMatchers(mvc.pattern("/api/auth/login")).permitAll()
                        .requestMatchers(mvc.pattern("/api/auth/signup")).permitAll()
                        .requestMatchers(h2ConsoleMatcher).permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(customizer -> customizer
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return http.build();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    public UserDetailsService userDetailsService() {
        return new UserDetailServiceImpl(userDetailsRepository, passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    }
}
