package fr.orleans.m1s1miage.group4.backend.controleur;

import fr.orleans.m1s1miage.group4.backend.model.entity.Genre;
import fr.orleans.m1s1miage.group4.backend.model.entity.Livre;
import fr.orleans.m1s1miage.group4.backend.model.service.GenreService;
import fr.orleans.m1s1miage.group4.backend.model.service.LivreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/api/test")
public class TestController {

    private GenreService genreService;
    private LivreService livreService;

    public TestController(GenreService genreService, LivreService livreService) {
        this.genreService = genreService;
        this.livreService = livreService;
    }

    @PostMapping("/genre")
    public ResponseEntity<String> testGenrePost() {
        Genre genre = new Genre();
        int rand = (int) (Math.random() * 1000);

        genre.setName("test"+rand);
        genre.setDescription("description");

        genreService.save(genre);

        return ResponseEntity.status(HttpStatus.CREATED).body(genre.getName() + " - "+ genre.getDescription());
    }

    @GetMapping("/genre")
    public ResponseEntity<List<Genre>> testGenreGet() {
        List<Genre> genres = genreService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(genres);
    }

    @PostMapping("/livre")
    public ResponseEntity<String> testLivrePost() {
        Livre livre = new Livre();
        int rand = (int) (Math.random() * 1000);

        livre.setTitre("test"+rand);
        livre.setAuteur("auteur");
        livre.setLangue("langue");
        livre.setStock(1);
        HashSet<Genre> genres = new HashSet<>();
        genres.add(genreService.findFirst());
        livre.setGenres(genres);

        livreService.save(livre);

        return ResponseEntity.status(HttpStatus.CREATED).body(livre.getTitre() + " - "+ livre.getAuteur() + " - "+ livre.getStock());
    }

    @GetMapping("/livre")
    public ResponseEntity<List<Livre>> testLivreGet() {
        List<Livre> livres = livreService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(livres);
    }
}
