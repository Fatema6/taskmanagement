package banquemisr.challenge05.taskmanagement.config;

import banquemisr.challenge05.taskmanagement.filter.JwtRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtRequestFilter jwtRequestFilter) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/auth/**").permitAll() // Open endpoints for login/register
                .requestMatchers("/tasks/**").hasAnyRole("USER", "ADMIN") // Role-based access
                .anyRequest().authenticated()
                .and()
                //.sessionManagement().disable() // Stateless session for JWT
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);



        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
