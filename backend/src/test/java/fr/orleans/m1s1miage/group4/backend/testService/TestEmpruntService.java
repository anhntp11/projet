package fr.orleans.m1s1miage.group4.backend.testService;

import fr.orleans.m1s1miage.group4.backend.model.entity.Emprunt;
import fr.orleans.m1s1miage.group4.backend.model.entity.Livre;
import fr.orleans.m1s1miage.group4.backend.model.repository.EmpruntRepository;
import fr.orleans.m1s1miage.group4.backend.model.repository.EtudiantRepository;
import fr.orleans.m1s1miage.group4.backend.model.repository.LivreRepository;
import fr.orleans.m1s1miage.group4.backend.model.service.EmpruntService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestEmpruntService {

    @Mock
    private EmpruntRepository empruntRepository;

    @Mock
    private LivreRepository livreRepository;

    @Mock
    private EtudiantRepository etudiantRepository;

    @InjectMocks
    private EmpruntService empruntService;

    @BeforeEach
    void setUp() {
        // Gán các mock vào staticRepository trong EmpruntService
        empruntService.init();
    }

    //save(emprunt)
    @Test
    void TestSaveEmprunt() {
        Emprunt emprunt = new Emprunt();
        empruntService.save(emprunt);
        verify(empruntRepository, times(1)).save(emprunt);
    }
    //findAll()
    @Test
    void TestFindAllEmprunts() {
        Emprunt emprunt1 = new Emprunt();
        Emprunt emprunt2 = new Emprunt();
        when(empruntRepository.findAll()).thenReturn(List.of(emprunt1, emprunt2));
        List<Emprunt> result = empruntService.findAll();
        assertEquals(2, result.size());
        verify(empruntRepository, times(1)).findAll();
    }
    //EmprunterLivre succes
//    @Test
//    void TestEmprunterLivreSucces() {
//        Livre livre = new Livre();
//        livre.setIdLivre(1L);
//        livre.setStock(2);
//        when(livreRepository.findById(1L)).thenReturn(Optional.of(livre));
//        //id etudiant exist
//        Etudiant etudiant = new Etudiant();
//        etudiant.setIdEtudiant(10L);
//        when(etudiantRepository.findByEmail("etu@test.fr"))
//                .thenReturn(Optional.of(etudiant));
//        //save Emprunt -> id
//        when(empruntRepository.save(any(Emprunt.class))).thenAnswer(invocation -> {
//            Emprunt emprunt = invocation.getArgument(0);
//            emprunt.setId_Emprunt(100L);
//            return emprunt;
//        });
//        EmpruntDTO EmDTO = EmpruntService.EmprunterLivre(1L, "etu@test.fr");
//        assertNotNull(EmDTO);
//        assertEquals(100L, EmDTO.getIdEmprunt());
//        assertEquals(1L, EmDTO.getLivreId());
//        assertEquals(10L, EmDTO.getEtudiantId());
//        assertNotNull(EmDTO.getDateEmprunt());
//
//        assertEquals(1, livre.getStock());
//
//        verify(livreRepository).findById(1L);
//        verify(etudiantRepository).findByEmail("etu@test.fr");
//        verify(empruntRepository).save(any(Emprunt.class));
//        verify(livreRepository).save(livre);
//    }
    //EmprunterLivre – Livre không tồn tại → RuntimeException
    @Test
    void testEmprunterLivreLivreIntrouvable() {
        when(livreRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class,
                () -> empruntService.emprunterLivre(1L, "etu@test.fr"));
        verify(livreRepository).findById(1L);
        verifyNoMoreInteractions(empruntRepository, etudiantRepository);
    }
    //EmprunterLivre – hết stock
    @Test
    void testEmprunterLivrePlusDeStock() {
        Livre livre = new Livre();
        livre.setIdLivre(1L);
        livre.setStock(0);
        when(livreRepository.findById(1L)).thenReturn(Optional.of(livre));

        assertThrows(RuntimeException.class,
                () -> empruntService.emprunterLivre(1L, "etu@test.fr"));

        verify(livreRepository).findById(1L);
        verifyNoMoreInteractions(empruntRepository, etudiantRepository);
    }
    //EmprunterLivre – Étudiant không tồn tại → RuntimeException
    @Test
    void testEmprunterLivreEtudiantIntrouvable() {
        Livre livre = new Livre();
        livre.setIdLivre(1L);
        livre.setStock(2);
        when(livreRepository.findById(1L)).thenReturn(Optional.of(livre));
        when(etudiantRepository.findByEmail("etu@test.fr"))
                .thenReturn(Optional.empty());
        assertThrows(RuntimeException.class,
                () -> empruntService.emprunterLivre(1L, "etu@test.fr"));
        verify(livreRepository).findById(1L);
        verify(etudiantRepository).findByEmail("etu@test.fr");
        verify(empruntRepository, never()).save(any());
        // stock không bị thay đổi
        assertEquals(2, livre.getStock());
    }
}
