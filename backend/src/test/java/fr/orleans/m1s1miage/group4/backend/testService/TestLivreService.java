package fr.orleans.m1s1miage.group4.backend.testService;

import fr.orleans.m1s1miage.group4.backend.model.entity.Livre;
import fr.orleans.m1s1miage.group4.backend.model.exception.LivreInconnuException;
import fr.orleans.m1s1miage.group4.backend.model.repository.LivreRepository;
import fr.orleans.m1s1miage.group4.backend.model.service.LivreService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)

public class TestLivreService {
    @Mock
    private LivreRepository livreRepository;

    @InjectMocks
    private LivreService livreService;

    /** test recuperer tous les livres
     * return tous les livres
     */
    @Test
    void findAllLivre(){
        Livre livre1 = new Livre(1L, "Livre 1", "Anglais", "10-12-2005",
                "Meow", LocalDateTime.now(), LocalDateTime.now(), 10);

        Livre livre2 = new Livre(2L, "Livre 2", "Francais", "05-10-2007",
                "Meow", LocalDateTime.now(), LocalDateTime.now(), 7);
        when(livreRepository.findAll())
                .thenReturn(Arrays.asList(livre1,livre2));

        List<Livre> result = livreService.findAll();
        assertEquals(2, result.size());
        verify(livreRepository, times(1) ).findAll();
    }

    /**
     * Test recuperer un livre
     */
    @Test
    void findLivreById(){
        Livre livre = new Livre();
        livre.setIdLivre(1L);
        livre.setTitre("Livre 1");

        when(livreRepository.findById(livre.getIdLivre()))
                .thenReturn(Optional.of(livre));

        Livre result = livreService.getLivreById(1L);
        assertEquals("Livre 1", result.getTitre());
        verify(livreRepository, times(1)).findById(1L);
    }

    /**
     * live non existant
     * @throws LivreInconnuException
     */
    @Test
    void findLivreByIdNonExistant(){
        when(livreRepository.findById(50L))
                .thenReturn(Optional.empty());
        assertThrows(LivreInconnuException.class,
                ()-> livreService.getLivreById(50L));
        verify(livreRepository, times(1)).findById(50L);
    }

    /**
     * Test ajouter un livre
     */
    @Test
    void TestAjouterLivre(){
        Livre entree = new Livre();
        entree.setTitre("Livre 1");
        entree.setLangue("Anglais");
        entree.setEditon("10-06-2019");
        entree.setNomAuteur("Meow");
        entree.setStock(5);

        Livre sortir = new Livre();
        sortir.setIdLivre(20L);
        sortir.setTitre("Livre 1");
        sortir.setLangue("Anglais");
        sortir.setEditon("10-06-2019");
        sortir.setNomAuteur("Meow");
        sortir.setStock(5);

        when(livreRepository.save(any(Livre.class))).thenReturn(sortir);
        Livre result = livreService.createLivre(entree);
        assertNotNull(result.getIdLivre());
        assertEquals(20L, result.getIdLivre());
        verify(livreRepository, times(1)).save(any(Livre.class));
    }

    /**
     * Test modifier un livre
     */
    @Test
    void TestModifierLivre(){
        Livre existant = new Livre();
        existant.setIdLivre(1L);
        existant.setTitre("Ancien Livre");
        existant.setLangue("Francais");
        existant.setEditon("10-06-2019");
        existant.setNomAuteur("Ancien Auteur");
        existant.setStock(10);

        when(livreRepository.findById(existant.getIdLivre()))
                .thenReturn(Optional.of(existant));

        Livre modifer = new Livre();
        modifer.setTitre("Nouveau titre");
        modifer.setLangue("Anglais");
        modifer.setEditon("10-06-2024");
        modifer.setNomAuteur("Nouveau Auteur");
        modifer.setStock(5);

        when(livreRepository.save(any(Livre.class)))
                .thenAnswer(i -> i.getArgument(0));
        Livre result = livreService.updateLivre(1L, modifer);
        assertEquals("Nouveau titre", result.getTitre());
        assertEquals("Anglais", result.getLangue());
        assertEquals("10-06-2024", result.getEditon());
        assertEquals("Nouveau Auteur", result.getNomAuteur());
        assertEquals(5, result.getStock());

        verify(livreRepository, times(1)).findById(1L);
        verify(livreRepository, times(1)).save(existant);
    }
    /**
     * Test modifier non existant
     * @throws LivreInconnuException
     */
    @Test
    void TestModifierLivreNonExistant(){
        Livre modifier = new Livre();
        when(livreRepository.findById(60L))
                .thenReturn(Optional.empty());
        assertThrows(LivreInconnuException.class,
                () -> livreService.updateLivre(60L, modifier));
        verify(livreRepository, times(1)).findById(60L);
        verify(livreRepository, never()).save(any());
    }
    /**
     * Test supprimer un live
     */
    @Test
    void TestSupprimerLivre(){
        when(livreRepository.existsById(1L))
                .thenReturn(true);
        livreService.deleteLivre(1L);
        verify(livreRepository, times(1)).existsById(1L);
        verify(livreRepository, times(1)).deleteById(1L);
    }
    /**
     * Test supprimer un livre non existant
     */
    @Test
    void TestSupprimerLivreNonExistant(){
        when(livreRepository.existsById(1L))
                .thenReturn(false);
        assertThrows(LivreInconnuException.class,
                () -> livreService.deleteLivre(1L));
        verify(livreRepository, times(1)).existsById(1L);
        verify(livreRepository, never()).deleteById(anyLong());
    }

}
