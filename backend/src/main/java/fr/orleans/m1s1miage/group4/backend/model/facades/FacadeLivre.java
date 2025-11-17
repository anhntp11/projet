package fr.orleans.m1s1miage.group4.backend.model.facades;

import fr.orleans.m1s1miage.group4.backend.model.entity.Livre;
import fr.orleans.m1s1miage.group4.backend.model.service.LivreService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

public class FacadeLivre {
    private LivreService livreService;

    public FacadeLivre(LivreService livreService) {
        this.livreService = livreService;
    }
    public List<Livre> getAllLivres(){
        return livreService.findAll();
    }
    public Livre getLivre(Long idLivre){
        return livreService.findById(idLivre);
    }
    public Livre createLivre(Livre livre) {
        return livreService.createLivre(livre);
    }

    public Livre updateLivre(Long idLivre, Livre livreModifie) {
        return livreService.updateLivre(idLivre, livreModifie);
    }

    public void deleteLivre(Long idLivre) {
        livreService.deleteLivre(idLivre);
    }
}
