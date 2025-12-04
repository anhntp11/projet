package fr.orleans.m1s1miage.group4.backend.configuration;

import fr.orleans.m1s1miage.group4.backend.model.entity.BU;
import fr.orleans.m1s1miage.group4.backend.model.entity.Genre;
import fr.orleans.m1s1miage.group4.backend.model.entity.InfoBU;
import fr.orleans.m1s1miage.group4.backend.model.entity.Livre;
import fr.orleans.m1s1miage.group4.backend.model.repository.BURepository;
import fr.orleans.m1s1miage.group4.backend.model.repository.GenreRepository;
import fr.orleans.m1s1miage.group4.backend.model.repository.InfoBURepository;
import fr.orleans.m1s1miage.group4.backend.model.repository.LivreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Simple startup seeder so fresh databases are populated with demo data.
 * Uses repositories directly so auditing fields (dateCreation/dateMiseAJour)
 * keep working as usual.
 */
@Component
@Profile("!test")
public class DataSeeder implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(DataSeeder.class);

    private final BURepository buRepository;
    private final GenreRepository genreRepository;
    private final InfoBURepository infoBURepository;
    private final LivreRepository livreRepository;

    public DataSeeder(
            BURepository buRepository,
            GenreRepository genreRepository,
            InfoBURepository infoBURepository,
            LivreRepository livreRepository
    ) {
        this.buRepository = buRepository;
        this.genreRepository = genreRepository;
        this.infoBURepository = infoBURepository;
        this.livreRepository = livreRepository;
    }

    @Override
    public void run(String... args) {
        long existing = livreRepository.count();
        if (existing > 0) {
            LOG.info("Skipping data seed, {} livre(s) already present.", existing);
            return;
        }
        LOG.info("Seeding reference data (genres, BU, livres)...");

        // Genres
        Genre romans = genreRepository.save(new Genre("Romans", "Histoires longues et narratives"));
        Genre sciences = genreRepository.save(new Genre("Sciences", "Notions scientifiques vulgarisées"));
        Genre tech = genreRepository.save(new Genre("Technologie", "Informatique et transformation digitale"));

        // BUs + info
        BU centrale = buRepository.save(new BU("BU Centrale"));
        attachInfo(centrale, "BU Centrale", "Ouverte du lundi au vendredi, 9h-18h");

        BU sciencesBU = buRepository.save(new BU("BU Sciences"));
        attachInfo(sciencesBU, "BU Sciences", "Spécialisée en sciences et ingénierie, 8h-20h");

        BU numerique = buRepository.save(new BU("BU Numérique"));
        attachInfo(numerique, "BU Numérique", "Espace moderne axé numérique et innovation");

        // Livres
        Livre archi = new Livre("Architecture distribuée", "FR", "Equipe Pédagogique", 4);
        archi.setGenres(List.of(tech, sciences));
        archi.setBus(List.of(sciencesBU, numerique));
        livreRepository.save(archi);

        Livre roman = new Livre("Les Chroniques d'Orléans", "FR", "A. Martin", 7);
        roman.setGenres(List.of(romans));
        roman.setBus(List.of(centrale));
        livreRepository.save(roman);

        Livre data = new Livre("Data pour les managers", "FR", "C. Leroy", 5);
        data.setGenres(List.of(tech, sciences));
        data.setBus(List.of(centrale, numerique));
        livreRepository.save(data);
        LOG.info("Seed completed: {} livre(s) inserted.", livreRepository.count());
    }

    private void attachInfo(BU bu, String nom, String details) {
        InfoBU infoBU = new InfoBU(details, nom);
        infoBU.setBu(bu);
        infoBURepository.save(infoBU);
        bu.setInfos(infoBU);
        buRepository.save(bu);
    }
}
