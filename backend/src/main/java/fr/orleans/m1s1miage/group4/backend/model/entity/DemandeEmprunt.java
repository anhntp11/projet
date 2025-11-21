package fr.orleans.m1s1miage.group4.backend.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="DemandeEmprunt")
public class DemandeEmprunt extends DemandeAbstract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_DemandeEmprunt;



    @ManyToOne
    @JoinColumn(name = "id_livre", nullable = false)
    private Livre livre;

    // constructeurs
    public DemandeEmprunt() {
    }

    public DemandeEmprunt(Etudiant etudiant, Livre livre, LocalDateTime dateDemande,
                          StatutEmprunt statut_Emprunt, Administrateur admin,
                          LocalDateTime dateApprobationEmprunt) {
        super(etudiant,dateDemande,statut_Emprunt,admin,dateApprobationEmprunt);
        this.livre = livre;
    }

    public Long getId_DemandeEmprunt() {
        return id_DemandeEmprunt;
    }


    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }
    public void setStatut_Emprunt() {

        super.setStatutEmprunt(StatutEmprunt.EMPRUNTER);
    }
}
