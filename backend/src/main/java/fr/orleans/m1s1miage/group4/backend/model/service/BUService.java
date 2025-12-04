package fr.orleans.m1s1miage.group4.backend.model.service;

import fr.orleans.m1s1miage.group4.backend.model.dto.BU.BUCreationDTO;
import fr.orleans.m1s1miage.group4.backend.model.dto.BU.BUDTO;
import fr.orleans.m1s1miage.group4.backend.model.entity.BU;
import fr.orleans.m1s1miage.group4.backend.model.exception.BuDejaExistanteException;
import fr.orleans.m1s1miage.group4.backend.model.exception.BuInconnueException;
import fr.orleans.m1s1miage.group4.backend.model.repository.BURepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BUService {
    private final BURepository repo;

    public BUService(BURepository repo) {
        this.repo = repo;
    }

    /**
     * Récupère toutes les BU en bd sous forme de DTO
     * @return La liste de tous les BUDTO des bu sauvegardées en BD
     */
    public List<BUDTO> findAll() {
        return repo.findAll().stream().map(BUDTO::new).collect(Collectors.toList());
    }

    /**
     * Sauvegarde une BU en bd.
     * @param bu La BU à sauvegarder
     */
    public void save(BU bu) {
        repo.save(bu);
    }

    /**
     * Récupère une BU de la bd selon son id
     * @param id l'id de la BU à récupérer
     * @return l'objet BU demandé
     * @throws BuInconnueException si l'ID est inconnu.
     */
    public BU findById(Long id) throws BuInconnueException {
        return repo.findById(id).orElseThrow(BuInconnueException::new);
    }

    /**
     * Sauvegarde la BU en BD
     * @param buCreationDTO le dto de creation de la BU
     * @return un BUDTO de la BU
     * @throws BuDejaExistanteException si le nom de la nouvelle BU est déjà utilisé
     */
    public BUDTO creerBU(BUCreationDTO buCreationDTO)
            throws  BuDejaExistanteException {
        BU bu = repo.findByNom(buCreationDTO.getNom());
        if (bu != null) {
            throw new BuDejaExistanteException();
        }

        bu = new BU();
        bu.setNom(buCreationDTO.getNom());

        repo.save(bu);

        return new BUDTO(bu);
    }
}
