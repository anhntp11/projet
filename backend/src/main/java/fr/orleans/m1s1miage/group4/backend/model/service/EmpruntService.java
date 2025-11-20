package fr.orleans.m1s1miage.group4.backend.model.service;

import fr.orleans.m1s1miage.group4.backend.model.dto.EmpruntDTO;
import fr.orleans.m1s1miage.group4.backend.model.entity.Emprunt;
import fr.orleans.m1s1miage.group4.backend.model.entity.Etudiant;
import fr.orleans.m1s1miage.group4.backend.model.entity.Livre;
import fr.orleans.m1s1miage.group4.backend.model.repository.EmpruntRepository;
import fr.orleans.m1s1miage.group4.backend.model.repository.EtudiantRepository;
import fr.orleans.m1s1miage.group4.backend.model.repository.LivreRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class EmpruntService {

    private final EmpruntRepository empruntRepository;
    private final LivreRepository livreRepository;
    private final EtudiantRepository etudiantRepository;
    // Champs statiques pour la méthode static
    private static EmpruntRepository staticEmpruntRepository;
    private static LivreRepository staticLivreRepository;
    private static EtudiantRepository staticEtudiantRepository;

    public EmpruntService(EmpruntRepository empruntRepository,
                          LivreRepository livreRepository,
                          EtudiantRepository etudiantRepository) {
        this.empruntRepository = empruntRepository;
        this.livreRepository = livreRepository;
        this.etudiantRepository = etudiantRepository;
    }

    @PostConstruct
    public void init() {
        staticEmpruntRepository = empruntRepository;
        staticLivreRepository = livreRepository;
        staticEtudiantRepository = etudiantRepository;
    }

    // Méthode non-static pour sauvegarder un emprunt depuis un service classique
    public void save(Emprunt emprunt) {
        empruntRepository.save(emprunt);
    }
    // Méthode static pour emprunter un livre
    public static EmpruntDTO EmprunterLivre(Long idLivre, String emailEtudiant) {

        Livre livre = staticLivreRepository.findById(idLivre)
                .orElseThrow(() -> new RuntimeException("Livre introuvable"));

        if (livre.getStock() <= 0) {
            throw new RuntimeException("Le livre n'est plus disponible");
        }

        Etudiant etudiant = staticEtudiantRepository.findByEmail(emailEtudiant)
                .orElseThrow(() -> new RuntimeException("Étudiant introuvable"));

        Emprunt emprunt = new Emprunt();
        emprunt.setLivre(livre);
        emprunt.setEtudiant(etudiant);
        emprunt.setDateEmprunt(LocalDateTime.now());

        // Sauvegarde dans la base
        staticEmpruntRepository.save(emprunt);

        // Décrément du stock
        livre.setStock(livre.getStock() - 1);
        staticLivreRepository.save(livre);

        // Construction du DTO
        EmpruntDTO dto = new EmpruntDTO();
        dto.setIdEmprunt(emprunt.getId_Emprunt());
        dto.setLivreId(livre.getIdLivre());
        dto.setEtudiantId(etudiant.getIdEtudiant());
        dto.setDateEmprunt(emprunt.getDateEmprunt());

        return dto;
    }

    // Liste tous les emprunts (non-static)
    public List<Emprunt> findAll() {
        return empruntRepository.findAll();
    }
}