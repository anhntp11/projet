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
    @JoinTable(
            name = "livre",
            joinColumns = @JoinColumn(name = "id_DemandeEmprunt"),
            inverseJoinColumns = @JoinColumn(name = "id-Livre")
    )
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

}
