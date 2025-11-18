package fr.orleans.m1s1miage.group4.backend.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Notification")
public class Notification extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNotification;

    @Column(nullable = false)
    private String message;

    @ManyToOne
    @JoinColumn(name = "etudiant_id")
    private Etudiant etudiant;

    //constructeurs
    public Notification() {}
    public Notification(String message, Etudiant etudiant) {
        this.message = message;
        this.etudiant = etudiant;
    }

    //getters / setters

    public Long getId() {
        return idNotification;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
