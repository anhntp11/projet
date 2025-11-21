package fr.orleans.m1s1miage.group4.backend.testService;

import fr.orleans.m1s1miage.group4.backend.model.entity.DemandeRetour;
import fr.orleans.m1s1miage.group4.backend.model.entity.StatutEmprunt;
import fr.orleans.m1s1miage.group4.backend.model.repository.DemandeRetourRepository;
import fr.orleans.m1s1miage.group4.backend.model.service.DemandeRetourService;
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
public class TestDemandeRetourService{

    @Mock
    private DemandeRetourRepository demandeRetourRepository;

    @InjectMocks
    private DemandeRetourService demandeRetourService;

    @Test
    void testFindAllDemandesRetour(){
        DemandeRetour demande1 = new DemandeRetour();
        DemandeRetour demande2 = new DemandeRetour();
        when(demandeRetourRepository.findAll()).thenReturn(List.of(demande1, demande2));
        List<DemandeRetour> rs = demandeRetourService.findAll();
        assertEquals(2, rs.size());
        verify(demandeRetourRepository, times(1)).findAll();
    }
    @Test
    void TestSaveDemandeRetour(){
        DemandeRetour demande = new DemandeRetour();
        demandeRetourService.save(demande);
        verify(demandeRetourRepository, times(1)).save(demande);
    }
    @Test
    void TestDemandeRetourSucces() {
        DemandeRetour demande = new DemandeRetour();
        demande.setStatut_Emprunt(StatutEmprunt.RENDU);
        when(demandeRetourRepository.findById(1L)).thenReturn(Optional.of(demande));
        when(demandeRetourRepository.save(demande)).thenReturn(demande);
        DemandeRetour rs = demandeRetourService.validerDemande(1L);
        assertEquals(StatutEmprunt.EMPRUNTER, rs.getStatut_Emprunt());
        verify(demandeRetourRepository).findById(1L);
        verify(demandeRetourRepository).save(demande);
    }
    @Test
    void testDemandeRetourFail() {
        when(demandeRetourRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> demandeRetourService.validerDemande(1L));
        verify(demandeRetourRepository).findById(1L);
        verify(demandeRetourRepository, never()).save(any());
    }
    @Test
    void TestRejectDemandeRetourSucces() {
        DemandeRetour demande = new DemandeRetour();
        demande.setStatut_Emprunt(StatutEmprunt.EMPRUNTER);
        when(demandeRetourRepository.findById(2L)).thenReturn(Optional.of(demande));
        when(demandeRetourRepository.save(demande)).thenReturn(demande);
        DemandeRetour rs = demandeRetourService.rejeterDemande(2L);
        assertEquals(StatutEmprunt.RENDU, rs.getStatut_Emprunt());
        verify(demandeRetourRepository).findById(2L);
        verify(demandeRetourRepository).save(demande);
    }
    @Test
    void testRejectDemandeRetourFail() {
        when(demandeRetourRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> demandeRetourService.rejeterDemande(2L));
        verify(demandeRetourRepository).findById(2L);
        verify(demandeRetourRepository, never()).save(any());
    }
}