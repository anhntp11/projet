package fr.orleans.m1s1miage.group4.backend.model.facades;


import fr.orleans.m1s1miage.group4.backend.model.entity.Emprunt;
import fr.orleans.m1s1miage.group4.backend.model.entity.Notification;
import fr.orleans.m1s1miage.group4.backend.model.entity.StatutEmprunt;
import fr.orleans.m1s1miage.group4.backend.model.exception.*;

import java.util.List;

/**
 * Facade où sont regroupées les méthodes en lien avec l'Étudiant uniquement.
 */
public interface FacadeEtudiant {

    /**
     * Gère la création d'une demande d'emprunt
     * @param idLivre l'id du livre à emprunter
     * @param idBu l'id de la BU dans lequel l'emprunt sera fait
     * @throws LivreInconnuException quand le livre n'est pas trouvé
     * @throws BuInconnueException quand la BU n'est pas trouvé
     */
    public void demandeEmprunt(int idLivre, int idBu)
            throws LivreInconnuException, BuInconnueException;

    /**
     * Créer une demande de retour d'un emprunt
     * @param idEmprunt id de l'Emprunt concerné par le retour
     * @throws EmpruntInconnuException quand l'emprunt n'est pas trouvé
     * @throws RetourImpossibleException quand le retour est impossible (Deja rendue/n'appartient pas à l'étudiant le rendant)
     */
    public void retournerLivre(int idEmprunt)
            throws EmpruntInconnuException, RetourImpossibleException;

    /**
     * Récupère une liste d'emprunt de l'utilsateur
     * @param statutEmprunt type à récupérer (Tous, EnCours, Rendus, etc...)
     * @return la liste des Emprunts demandés
     */
    public List<Emprunt> getListeEmprunt(StatutEmprunt statutEmprunt);
    /**
     * Etend la durée d'un emprunt
     * @param idEmprunt emprunt à étendre
     * @throws EmpruntInconnuException quand l'emprunt n'est pas reconnu.
     * @throws EmpruntDejaEtenduException quand l'emprunt a déjà été étendu au maximum (une fois).
     * @throws DateEcheanceEmpruntDepasseeException quand la date d'échéance de l'emprunt a été dépassé, rendant impossible l'emprunt.
     */
    public void etendreEmprunt(int idEmprunt)
            throws EmpruntInconnuException, EmpruntDejaEtenduException, DateEcheanceEmpruntDepasseeException;

    /**
     * Récupère la liste des notifications de l'utilisateur
     * @return la liste des notification
     */
    public List<Notification> getListeNotification();
}
