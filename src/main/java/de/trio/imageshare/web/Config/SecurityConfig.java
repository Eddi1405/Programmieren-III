package de.trio.imageshare.web.Config;

import de.trio.imageshare.web.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Dies ist eine Konfigurationsklasse für die Spring Security.
 * Die Annotation @EnableWebSecurity aktiviert die Web-Sicherheitskonfiguration von Spring Security.
 * <p>
 * Die Methode filterChain() konfiguriert die Sicherheitsfilterkette für die Anwendung.
 * Die Filterkette besteht aus verschiedenen Sicherheitsfiltern, die die Anforderungen des Benutzers validieren.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    UserRepository userRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.csrf().disable();
        http.authorizeHttpRequests().requestMatchers("/indexShow/**").permitAll()
                //.requestMatchers("/dashboard").authenticated()
                .anyRequest().permitAll();
        http.formLogin().loginPage("/login").loginProcessingUrl("/process_login").usernameParameter("name").passwordParameter("password");

        // configure logout
        http.logout().invalidateHttpSession(true).logoutUrl("/logout").logoutSuccessUrl("/login?logout").deleteCookies("JSESSIONID");

        return http.build();

    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}


