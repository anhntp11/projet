package fr.orleans.m1s1miage.group4.backend.model.facades;

import fr.orleans.m1s1miage.group4.backend.model.entity.*;
import fr.orleans.m1s1miage.group4.backend.model.exception.*;

import java.util.List;

/**
 * Facade où sont regroupées les méthodes en lien avec l'Administrateur uniquement.
 */
public interface FacadeAdministrateur {

    /**
     * Récupère une liste de tous les emprunts
     * @param statutEmprunt type à récupérer (Tous, EnCours, Rendus, etc...)
     * @return la liste des Emprunts demandés
     */
    public List<Emprunt> getTousLesEmprunts(StatutEmprunt statutEmprunt);

    /**
     * Récupère la liste des demandes d'emprunt
     * @return la liste des demandes
     */
    public List<DemandeEmprunt> getDemandeEmprunts();

    /**
     * Récupère la liste des demandes de retour
     * @return la liste des demandes
     */
    public List<DemandeRetour> getDemandeRetours();

    /**
     * Récupère un objet demande d'emprunt demandé
     * @param idDemande l'id de l'objet souhaité
     * @return l'objet demande
     * @throws DemandeInconnueException quand la demande n'est pas trouvée
     */
    public DemandeEmprunt getDemandeEmprunt(int idDemande)
            throws DemandeInconnueException;

    /**
     * Récupère un objet demande de retour demandé
     * @param idDemande l'id de l'objet souhaité
     * @return l'objet demande
     * @throws DemandeInconnueException quand la demande n'est pas trouvée
     */
    public DemandeEmprunt getDemandeRetour(int idDemande)
            throws DemandeInconnueException;

    /**
     * Valide la demande
     * @param demande demande à valider
     * @throws DemandeDejaTraiteeException quand la demande a déjà reçu une réponse
     */
    public void validerDemande(DemandeAbstract demande)
            throws DemandeDejaTraiteeException;

    /**
     * Rejette la demande
     * @param demande demande à rejeter
     * @throws DemandeDejaTraiteeException quand la demande a déjà reçu une réponse
     */
    public void rejeterDemande(DemandeAbstract demande)
            throws DemandeDejaTraiteeException;

    /**
     * Supprime un livre
     * @param idLivre livre a supprimer
     * @throws LivreInconnuException quand le livre n'est pas trouvé.
     */
    public void supprimerLivre(int idLivre)
            throws LivreInconnuException;

    /**
     * Modifie les informations de la BU
     * @param idBu id de la BU visée
     * @param infoBu les nouvelles infos
     * @throws BuInconnueException quand la BU n'est pas trouvée.
     */
    public void modifierInfoBU(int idBu, String infoBu)
            throws BuInconnueException;

    /**
     * Supprime le compte d'un utilisateur.
     * @param idUtilisateur id de l'utilisateur à supprimer
     * @throws UtilsateurInconnuException quand l'utilisateur n'est pas reconnu.
     * @throws AdminSuppressionException quand l'utilisateur ciblé est administrateur.
     */
    public void supprimerUtilisateur(int idUtilisateur)
            throws UtilsateurInconnuException, AdminSuppressionException;

    /**
     * Envoie une notification à l'utilisateur propriétaire d'un emprunt par rapport à l'échéance
     * @param idEmprunt id de l'emprunt sur lequel efféctué la notification.
     * @throws EmpruntInconnuException quand l'emprunt n'est pas trouvé.
     */
    public void envoyerRappel(int idEmprunt)
            throws EmpruntInconnuException;

    /**
     * Récupère le profil d'un compte
     * @param idUtilisateur id de l'utilisateur à récupérer
     * @return l'utilsateur en question
     * @throws UtilsateurInconnuException s'il n'est pas reconnu
     */
    public Utilsateur getUtilisateur(int idUtilisateur)
            throws UtilsateurInconnuException;

    /**
     * Récupère le profil d'un compte
     * @param idEtudiant id de l'etudiant à récupérer
     * @return l'utilsateur en question
     * @throws UtilsateurInconnuException s'il n'est pas reconnu
     */
    public Etudiant getEtudiant(int idEtudiant)
            throws UtilsateurInconnuException;


}
