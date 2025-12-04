package fr.orleans.m1s1miage.group4.backend.configuration;

import fr.orleans.m1s1miage.group4.backend.model.entity.RoleUtilisateur;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    /**
     * Gère l'accessibilité des liens
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        //Swagger
                        .requestMatchers("/swagger-ui/**").permitAll()

                        // connexion et inscription
                        .requestMatchers("/api/auth/**").permitAll() // connexion et inscription

                        // voir les livres
                        .requestMatchers(HttpMethod.POST,"/api/livres").hasRole(RoleUtilisateur.ADMINISTRATEUR.name()) // CreerLivres
                        .requestMatchers(HttpMethod.GET,"/api/livres").permitAll() // getAllLivres
                        .requestMatchers(HttpMethod.GET,"/api/livres/*").permitAll() // getLivreById
                        .requestMatchers(HttpMethod.PATCH,"/api/livres/*").hasRole(RoleUtilisateur.ADMINISTRATEUR.name()) // updateLivre
                        .requestMatchers(HttpMethod.DELETE,"/api/livres/*").hasRole(RoleUtilisateur.ADMINISTRATEUR.name()) // deleteLivre

                        // infos
                        .requestMatchers(HttpMethod.GET, "/api/infos").permitAll() // getInfo
                        .requestMatchers(HttpMethod.POST, "/api/infos").hasRole(RoleUtilisateur.ADMINISTRATEUR.name()) // createInfo
                        .requestMatchers(HttpMethod.PATCH, "/api/infos/*").hasRole(RoleUtilisateur.ADMINISTRATEUR.name()) // updateInfo

                        //TODO - Une fois merge avec les demandes, ajouter leurs routes

                        // voir le catalogue
                        .requestMatchers(HttpMethod.GET,"/api/catalogue/**").permitAll() // recherche et getAll

                        // bu
                        .requestMatchers(HttpMethod.POST,"/api/bu").hasRole(RoleUtilisateur.ADMINISTRATEUR.name())
                        .requestMatchers(HttpMethod.GET,"/api/bu").hasRole(RoleUtilisateur.ADMINISTRATEUR.name())

                        // le reste
                        .anyRequest().permitAll()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * Rend accessible l'authenticationManager pour les autres classes
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Rend accessible le passwordEncoder pour les autres classes
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
