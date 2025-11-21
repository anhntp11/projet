package fr.orleans.m1s1miage.group4.backend.testService;

import fr.orleans.m1s1miage.group4.backend.model.entity.DemandeEmprunt;
import fr.orleans.m1s1miage.group4.backend.model.entity.StatutEmprunt;
import fr.orleans.m1s1miage.group4.backend.model.repository.DemandeEmpruntRepository;
import fr.orleans.m1s1miage.group4.backend.model.service.DemandeEmpruntService;
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
public class TestDemandeEmpruntService {

    @Mock
    private DemandeEmpruntRepository demandeEmpruntRepository;

    @InjectMocks
    private DemandeEmpruntService demandeEmpruntService;

    @Test
    void testFindAllDemandesEmprunt() {
        DemandeEmprunt demande1 = new DemandeEmprunt();
        DemandeEmprunt demande2 = new DemandeEmprunt();
        when(demandeEmpruntRepository.findAll()).thenReturn(List.of(demande1, demande2));
        List<DemandeEmprunt> result = demandeEmpruntService.findAll();
        assertEquals(2, result.size());
        verify(demandeEmpruntRepository, times(1)).findAll();
    }

    @Test
    void TestSaveDemandeEmprunt() {
        DemandeEmprunt demande = new DemandeEmprunt();
        demandeEmpruntService.save(demande);
        verify(demandeEmpruntRepository, times(1)).save(demande);
    }

    //validerDemande => set statut => save
    @Test
    void TestValiderDemandeSucces() {
        DemandeEmprunt demande = new DemandeEmprunt();
        demande.setStatut_Emprunt(StatutEmprunt.RENDU);
        when(demandeEmpruntRepository.findById(1L)).thenReturn(Optional.of(demande));
        when(demandeEmpruntRepository.save(demande)).thenReturn(demande);
        DemandeEmprunt rs = demandeEmpruntService.validerDemande(1L);
        assertEquals(StatutEmprunt.EMPRUNTER, rs.getStatut_Emprunt());
        verify(demandeEmpruntRepository).findById(1L);
        verify(demandeEmpruntRepository).save(demande);
    }

    //validerDemande id n'existe pas
    @Test
    void testValiderDemandeIdExistePas() {
        when(demandeEmpruntRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> demandeEmpruntService.validerDemande(1L));
        verify(demandeEmpruntRepository).findById(1L);
        verify(demandeEmpruntRepository, never()).save(any());
    }
}