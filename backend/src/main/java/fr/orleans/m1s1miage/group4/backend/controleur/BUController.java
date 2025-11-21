package fr.orleans.m1s1miage.group4.backend.controleur;

import fr.orleans.m1s1miage.group4.backend.model.dto.BU.BUCreationDTO;
import fr.orleans.m1s1miage.group4.backend.model.dto.BU.BUDTO;
import fr.orleans.m1s1miage.group4.backend.model.service.BUService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bu")
public class BUController {

    private final BUService buService;

    public BUController(BUService buService) {
        this.buService = buService;
    }


    @PostMapping
    public ResponseEntity<BUDTO> bu(
            @RequestBody BUCreationDTO buCreationDTO
    ) {
        BUDTO buDto = buService.creerBU(buCreationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(buDto);
    }

    @GetMapping
    public ResponseEntity<List<BUDTO>> buList() {
        List<BUDTO> buList = buService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(buList);
    }
}
