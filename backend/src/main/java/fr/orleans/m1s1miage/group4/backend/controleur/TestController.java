package fr.orleans.m1s1miage.group4.backend.controleur;

import fr.orleans.m1s1miage.group4.backend.model.entity.Genre;
import fr.orleans.m1s1miage.group4.backend.model.repository.GenreRepository;
import fr.orleans.m1s1miage.group4.backend.model.service.GenreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TestController {

    private GenreService genreService;

    public TestController(GenreService genreService) {
        this.genreService = genreService;
    }

    @PostMapping("/")
    public ResponseEntity<String> testPost() {
        Genre genre = new Genre();
        int rand = (int) (Math.random() * 1000);

        genre.setName("test"+rand);
        genre.setDescription("description");

        genreService.SaveGenre(genre);

        return ResponseEntity.status(HttpStatus.CREATED).body(genre.getName() + " - "+ genre.getDescription());
    }

    @GetMapping("/")
    public ResponseEntity<List<Genre>> testGet() {
        List<Genre> genres = genreService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(genres);
    }
}
