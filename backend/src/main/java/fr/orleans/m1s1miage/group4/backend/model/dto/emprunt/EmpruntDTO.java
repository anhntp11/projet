package fr.orleans.m1s1miage.group4.backend.model.dto.emprunt;

import java.time.LocalDateTime;

public class EmpruntDTO {
    private Long idEmprunt;
   private Long etudiantId;
   private Long livreId;
   private Long buId;
   private LocalDateTime dateEmprunt;
   private LocalDateTime dateRetour;
   private LocalDateTime dateEcheance;
   private String statutEmprunt; // ou enum StatutEmprunt si tu veux exposer l'enum
   private boolean estRenouvelle;private Long adminId;


   public EmpruntDTO() {}

   public EmpruntDTO(Long idEmprunt, Long etudiantId, Long livreId, Long buId,
                  LocalDateTime dateEmprunt, LocalDateTime dateRetour, LocalDateTime dateEcheance,
                  String statutEmprunt, boolean estRenouvelle, Long adminId) {
    this.idEmprunt = idEmprunt;
    this.etudiantId = etudiantId;
    this.livreId = livreId;
    this.buId = buId;
    this.dateEmprunt = dateEmprunt;
    this.dateRetour = dateRetour;
    this.dateEcheance = dateEcheance;
    this.statutEmprunt = statutEmprunt;
    this.estRenouvelle = estRenouvelle;
    this.adminId = adminId;
   }

// Getters et setters
   public Long getIdEmprunt() {
      return idEmprunt;
}

  public void setIdEmprunt(Long idEmprunt) {
      this.idEmprunt = idEmprunt;
}

public Long getEtudiantId() {
    return etudiantId;
}

public void setEtudiantId(Long etudiantId) {
    this.etudiantId = etudiantId;
}

public Long getLivreId() {
    return livreId;
}

public void setLivreId(Long livreId) {
    this.livreId = livreId;
}

public Long getBuId() {
    return buId;
}

public void setBuId(Long buId) {
    this.buId = buId;
}

public LocalDateTime getDateEmprunt() {
    return dateEmprunt;
}

public void setDateEmprunt(LocalDateTime dateEmprunt) {
    this.dateEmprunt = dateEmprunt;
}

public LocalDateTime getDateRetour() {
    return dateRetour;
}

public void setDateRetour(LocalDateTime dateRetour) {
    this.dateRetour = dateRetour;
}

public LocalDateTime getDateEcheance() {
    return dateEcheance;
}

public void setDateEcheance(LocalDateTime dateEcheance) {
    this.dateEcheance = dateEcheance;
}

public String getStatutEmprunt() {
    return statutEmprunt;
}

public void setStatutEmprunt(String statutEmprunt) {
    this.statutEmprunt = statutEmprunt;
}

public boolean isEstRenouvelle() {
    return estRenouvelle;
}

public void setEstRenouvelle(boolean estRenouvelle) {
    this.estRenouvelle = estRenouvelle;
}

public Long getAdminId() {
    return adminId;
}

public void setAdminId(Long adminId) {
    this.adminId = adminId;
}
}