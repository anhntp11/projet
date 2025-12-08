package fr.orleans.m1s1miage.group4.backend.testService;

import fr.orleans.m1s1miage.group4.backend.model.dto.emprunt.EmpruntDTO;
import fr.orleans.m1s1miage.group4.backend.model.entity.Emprunt;
import fr.orleans.m1s1miage.group4.backend.model.entity.Etudiant;
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
    //EmprunterLivre – Livre không tồn tại → RuntimeException
    @Test
    void testEmprunterLivreIntrouvable()  {
        when(livreRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class,
                () -> empruntService.emprunterLivre(1L, "etu@test.fr"));
        verify(livreRepository).findById(1L);
        verify(empruntRepository, never()).save(any());
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
    //muon thanh cong
    @Test
    void testEmprunterLivreSucces(){
        Livre livre = new Livre();
        livre.setIdLivre(1L);
        when(livreRepository.findById(1L)).thenReturn(Optional.of(livre));

        Etudiant etudiant = new Etudiant();
        etudiant.setNumEtudiant("o225a");
        when(etudiantRepository.findByEmail("etu@test.fr"))
                .thenReturn(Optional.of(etudiant));
        when(empruntRepository.save(any(Emprunt.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        EmpruntDTO empruntDTO = empruntService.emprunterLivre(1L, "etu@test.fr");
        assertNotNull(empruntDTO);
        assertEquals(1L, empruntDTO.getLivreId());
        assertEquals(etudiant.getIdEtudiant(), empruntDTO.getEtudiantId());
        assertNotNull(empruntDTO.getDateEmprunt());

        verify(livreRepository).findById(1L);
        verify(etudiantRepository).findByEmail("etu@test.fr");
        verify(empruntRepository).save(any(Emprunt.class));
    }
}
