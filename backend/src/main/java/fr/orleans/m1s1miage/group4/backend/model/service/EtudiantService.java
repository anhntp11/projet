package fr.orleans.m1s1miage.group4.backend.model.service;

import fr.orleans.m1s1miage.group4.backend.model.entity.Etudiant;
import fr.orleans.m1s1miage.group4.backend.model.exception.EtudiantInconnuException;
import fr.orleans.m1s1miage.group4.backend.model.repository.EtudiantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EtudiantService {
    private final EtudiantRepository repo;

    public EtudiantService(EtudiantRepository repo) {
        this.repo = repo;
    }

    /**
     * Compte le nombre d'Etudiant
     * @return le nombre d'Etudiant
     */
    public int getEtudiantCount(){
        return repo.findAll().size();
    }

    /**
     * Permet de sauvegarder l'étudiant dans la BD
     * @param etudiant L'étudiant à sauvegarder
     */
    public void save(Etudiant etudiant) {
        repo.save(etudiant);
    }

    /**
     * Recupere l'étudiant cherché
     * @param etudiantId Id de l'étudiant cherché
     * @return L'étudiant cherché
     * @throws EtudiantInconnuException si l'id ne correspond à aucun étudiant
     */
    public Etudiant getEtudiant(Long etudiantId) throws EtudiantInconnuException {
        Optional<Etudiant> opt = repo.findById(etudiantId);

        if (opt.isPresent()) {
            return opt.get();
        } else {
            throw new EtudiantInconnuException();
        }

    }
}
