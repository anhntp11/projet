package fr.orleans.m1s1miage.group4.backend.testService;

import fr.orleans.m1s1miage.group4.backend.model.dto.livre.LivreCreationDTO;
import fr.orleans.m1s1miage.group4.backend.model.dto.livre.LivreDTO;
import fr.orleans.m1s1miage.group4.backend.model.entity.BU;
import fr.orleans.m1s1miage.group4.backend.model.entity.Genre;
import fr.orleans.m1s1miage.group4.backend.model.entity.Livre;
import fr.orleans.m1s1miage.group4.backend.model.exception.BuInconnueException;
import fr.orleans.m1s1miage.group4.backend.model.exception.GenreInconnuException;
import fr.orleans.m1s1miage.group4.backend.model.exception.LivreInconnuException;
import fr.orleans.m1s1miage.group4.backend.model.repository.LivreRepository;
import fr.orleans.m1s1miage.group4.backend.model.service.BUService;
import fr.orleans.m1s1miage.group4.backend.model.service.GenreService;
import fr.orleans.m1s1miage.group4.backend.model.service.LivreService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)

public class TestLivreService {
    @Mock
    private LivreRepository livreRepository;

    @Mock
    private BUService  buService;

    @Mock
    private GenreService  genreService;

    @InjectMocks
    private LivreService livreService;

    /** test recuperer tous les livres
     * return tous les livres
     */
    @Test
    void findAllLivre(){
            Livre livre1 = new Livre();
            Livre livre2 = new Livre();
        when(livreRepository.findAll())
                .thenReturn(List.of(livre1,livre2));

        List<LivreDTO> rs = livreService.rechercherLivres(null, null,null);
        assertEquals(2, rs.size());
        verify(livreRepository, times(1) ).findAll();
    }
    /**
     * Test recuperer un livre
     */
    @Test
    void testFindLivreById(){
        Livre livre = new Livre();
        livre.setIdLivre(1L);
        livre.setTitre("Livre 1");

        when(livreRepository.findById(1L))
                .thenReturn(Optional.of(livre));

        LivreDTO rs = livreService.getLivreById(1L);
        assertEquals("Livre 1", rs.getTitre());
        verify(livreRepository, times(1)).findById(1L);
    }
    /**
     * live in existant
     * @throws LivreInconnuException
     */
    @Test
    void testFindLivreByIdInExistant(){
        when(livreRepository.findById(50L))
                .thenReturn(Optional.empty());
        assertThrows(LivreInconnuException.class,
                ()-> livreService.getLivreById(50L));
        verify(livreRepository, times(1)).findById(50L);
    }
    /**
     * Test modifier un livre
     */
    @Test
    void TestModifierLivre() throws
            LivreInconnuException,
            BuInconnueException,
            GenreInconnuException {
        Livre existant = new Livre();
        existant.setIdLivre(1L);
        existant.setTitre("Ancien titre");

        when(livreRepository.findById(1L)).thenReturn(Optional.of(existant));

        LivreCreationDTO modifier = new LivreCreationDTO();
        modifier.setTitre("Nouveau titre");
        modifier.setLangue("FR");
        modifier.setAuteur("Auteur Y");
        modifier.setStock(5);
        modifier.setBuIds(List.of(2L));
        modifier.setGenreIds(List.of(20L));

        BU bu = new BU();
        bu.setIdBu(2L);
        Genre genre = new Genre();
        genre.setIdGenre(20L);

        when(buService.findById(2L)).thenReturn(bu);
        when(genreService.findById(20L)).thenReturn(genre);
        when(livreRepository.save(existant)).thenReturn(existant);

        LivreDTO rs = livreService.updateLivre(1L, modifier);
        assertEquals("Nouveau titre", rs.getTitre());
        verify(livreRepository).findById(1L);
        verify(livreRepository).save(existant);
    }
    /**
     * Test modifier  in existant
     * @throws LivreInconnuException
     */
    @Test
    void TestModifierLivreInExistant(){
        LivreCreationDTO modifier = new LivreCreationDTO();
        when(livreRepository.findById(60L))
                .thenReturn(Optional.empty());
        assertThrows(LivreInconnuException.class,
                () -> livreService.updateLivre(60L, modifier));
        verify(livreRepository).findById(60L);
        verify(livreRepository, never()).save(any());
    }
    /**
     * Test supprimer un live
     */
    @Test
    void TestSupprimerLivre() throws LivreInconnuException{
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
    void TestSupprimerLivreInExistant(){
        when(livreRepository.existsById(1L))
                .thenReturn(false);
        assertThrows(LivreInconnuException.class,
                () -> livreService.deleteLivre(1L));
        verify(livreRepository, times(1)).existsById(1L);
        verify(livreRepository, never()).deleteById(anyLong());
    }
    @Test
    void testCreateLivre() throws BuInconnueException, GenreInconnuException {
        LivreCreationDTO dto = new LivreCreationDTO();
        dto.setTitre("Le petit prince");
        dto.setLangue("FR");
        dto.setAuteur("Antoine de Saint Exuperi");
        dto.setStock(3);
        dto.setBuIds(List.of(1L));
        dto.setGenreIds(List.of(10L));

        BU bu = new BU();
        bu.setIdBu(1L);

        Genre genre = new Genre();
        genre.setIdGenre(10L);

        when(buService.findById(1L)).thenReturn(bu);
        when(genreService.findById(10L)).thenReturn(genre);
        when(livreRepository.save(any(Livre.class))).thenAnswer(invocation -> invocation.getArgument(0));

        LivreDTO rs = livreService.createLivre(dto);
        assertEquals("Le petit prince", rs.getTitre());
        assertEquals("Antoine de Saint Exuperi", rs.getAuteur());
        verify(livreRepository).save(any(Livre.class));
    }
    @Test
    void testChercherLivreParTitre(){
        Livre livre1 = new Livre();
        livre1.setTitre("Le petit prince");
        livre1.setIdLivre(1L);

        Livre livre2 = new Livre();
        livre2.setTitre("Le petit enfant");
        livre2.setIdLivre(2L);

        String titreRecherche = "petit";
        when(livreRepository.findByTitreContainingIgnoreCase(titreRecherche))
                .thenReturn(Arrays.asList(livre1, livre2));
        List<LivreDTO> rs = livreService.chercherParTitre(titreRecherche);
        assertEquals(2, rs.size());
        assertEquals("Le petit prince", rs.get(0).getTitre());
        assertEquals("Le petit enfant", rs.get(1).getTitre());
        verify(livreRepository).findByTitreContainingIgnoreCase(titreRecherche);
    }

    @Test
    void testChercherLivreParGenre(){

    }
}
