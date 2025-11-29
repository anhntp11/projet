package fr.orleans.m1s1miage.group4.backend.model.service;

import fr.orleans.m1s1miage.group4.backend.model.dto.infoBU.InfoBuDTO;
import fr.orleans.m1s1miage.group4.backend.model.dto.infoBU.InfoBUCreationDTO;
import fr.orleans.m1s1miage.group4.backend.model.entity.BU;
import fr.orleans.m1s1miage.group4.backend.model.entity.InfoBU;
import fr.orleans.m1s1miage.group4.backend.model.exception.BuInconnueException;
import fr.orleans.m1s1miage.group4.backend.model.exception.InformationBUInconnueException;
import fr.orleans.m1s1miage.group4.backend.model.repository.InfoBURepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoBUService {
    private final InfoBURepository infoBURepository;
    private final BUService buService;

    public InfoBUService(
            InfoBURepository genreRepository,
            BUService buService
            ) {
        this.infoBURepository = genreRepository;
        this.buService = buService;
    }

    /**
     * Méthode créer pour tester le bon fonctionnement de JPA et pour servir d'exemple.
     * @return La list de tous les genres sauvegardés en BD
     */
    public List<InfoBU> findAll() {
        return infoBURepository.findAll();
    }


    /**
     * Recupere les info d'une BU grace a son id
     * @param idBu l'id de la BU pour laquelle on veut les infos
     * @return le DTO de l'info cherchée
     * @throws BuInconnueException si aucune BU n'a été trouvé avec l'idBU donné
     */
    public InfoBuDTO getInfoBUDtoByBUId(Long idBu) throws BuInconnueException {
        InfoBU info = infoBURepository.findByBuIdBU(idBu);
        if (info == null) {
            throw new BuInconnueException();
        }
        return new InfoBuDTO(
        );
    }

    /**
     * Sauvegarde en BD les info d'une BU
     * @param creationDTO le DTO de creation de l'information
     * @return un DTO de l'info créé
     * @throws BuInconnueException si l'id de la BU fournie dans le DTO de creation n'appartient a aucune BU
     */
    public InfoBuDTO createInfoBU(InfoBUCreationDTO creationDTO) throws BuInconnueException {
        InfoBU infoBU = new InfoBU(creationDTO.getInformations(), creationDTO.getNom());
        BU bu = buService.findById(creationDTO.getIdBu());
        infoBU.setBu(bu);
        bu.setInfos(infoBU);
        infoBURepository.save(infoBU);

        return new InfoBuDTO(infoBU);
    }


    public InfoBuDTO editInfo(Long idInfo, InfoBUCreationDTO editDTO) throws InformationBUInconnueException, BuInconnueException{
        InfoBU infoBU = infoBURepository.findById(idInfo).orElseThrow(InformationBUInconnueException::new);
        infoBU.setInformations(editDTO.getInformations());
        infoBU.setNom(editDTO.getNom());
        BU bu = buService.findById(editDTO.getIdBu());
        infoBU.setBu(bu);
        infoBURepository.save(infoBU);

        return new InfoBuDTO(infoBU);
    }
}
