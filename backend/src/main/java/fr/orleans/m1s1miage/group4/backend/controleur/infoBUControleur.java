package fr.orleans.m1s1miage.group4.backend.controleur;

import fr.orleans.m1s1miage.group4.backend.model.dto.InfoBuDTO;

import fr.orleans.m1s1miage.group4.backend.model.dto.infoBU.InfoBUCreationDTO;
import fr.orleans.m1s1miage.group4.backend.model.service.InfoBUService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/info")
public class infoBUControleur {

    InfoBUService infoBUService;

    public infoBUControleur(
            InfoBUService infoBUService
    ) {
        this.infoBUService = infoBUService;
    }

    @GetMapping
    public ResponseEntity<InfoBuDTO> getBuDTO(
            @RequestParam Long idBU
    ) {
        InfoBuDTO infoBuDTO = infoBUService.getInfoBUDtoByBUId(idBU);

        return ResponseEntity.status(HttpStatus.OK).body(infoBuDTO);
    }

    @PostMapping
    public ResponseEntity<InfoBuDTO> createInfo(
            @RequestBody InfoBUCreationDTO creationDTO
    ) {
        InfoBuDTO infoBuDTO = infoBUService.createInfoBU(creationDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(infoBuDTO);
    }
}
