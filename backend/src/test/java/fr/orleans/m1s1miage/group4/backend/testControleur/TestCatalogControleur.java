//package fr.orleans.m1s1miage.group4.backend.testControleur;
//
//import fr.orleans.m1s1miage.group4.backend.model.dto.livre.LivreDTO;
//import fr.orleans.m1s1miage.group4.backend.model.service.LivreService;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/catalog")
//public class TestCatalogControleur {
//    private final LivreService livreService;
//
//    public void CatalogueController(LivreService livreService) {
//        this.livreService = livreService;
//    }
//    @GetMapping
//    public List<LivreDTO> getCatalogue(@RequestParam(required = false) String titre,
//                                       @RequestParam(required = false) String auteur,
//                                       @RequestParam(required = false) String genre){
//        return livreService.rechercherLivres(titre, auteur, genre);
//    }
//}
