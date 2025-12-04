package fr.orleans.m1s1miage.group4.backend.model.service.auth;

import fr.orleans.m1s1miage.group4.backend.model.entity.Utilsateur;
import fr.orleans.m1s1miage.group4.backend.model.exception.EtudiantInconnuException;
import fr.orleans.m1s1miage.group4.backend.model.exception.UtilisateurInconnuException;
import fr.orleans.m1s1miage.group4.backend.model.repository.AdministrateurRepository;
import fr.orleans.m1s1miage.group4.backend.model.repository.EtudiantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AdministrateurRepository administrateurRepository;
    private final EtudiantRepository etudiantRepository;

    /**
     * Cette méthode récupère l'utilisateur qui correspond à cet email.
     * Vu qu'on a plusieurs classes utilisateur, on est obligé de les vérifier ici.
     * @param email Le mail de l'user visé
     * @return l'UserDetails utilisé par Spring Security pour l'authentification
     * @throws UtilisateurInconnuException si l'email n'appartient à aucun compte
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UtilisateurInconnuException {

        // recup l'Utilsateur en cherchant dans ses deux classes filles
        Utilsateur user = administrateurRepository.findByEmail(email)
                .map(a -> (Utilsateur) a)
                .or(() -> etudiantRepository.findByEmail(email).map(e -> (Utilsateur) e))
                .orElseThrow(EtudiantInconnuException::new);

        return User
                .withUsername(user.getEmail())
                .password(user.getMotDePasse())
                .roles(user.getRole().name())
                .build();
    }
}

