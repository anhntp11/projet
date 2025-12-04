package fr.orleans.m1s1miage.group4.backend.configuration;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

/**
 * Charge le contenu du .env. Contient les paths des private/public keys, légèrement overkill.
 */
@Component
public class EnvLoader {
    // TODO : pensez a envoyer le .env sur le discord

    public static Dotenv dotenv;

    @PostConstruct
    public void init() {
        dotenv = Dotenv.configure()
                .directory("./")
                .ignoreIfMissing()
                .load();
    }
}
