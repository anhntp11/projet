package fr.orleans.m1s1miage.group4.backend.model.facades;


import fr.orleans.m1s1miage.group4.backend.model.entites.Livre;
import fr.orleans.m1s1miage.group4.backend.model.exception.*;

import java.util.List;

/**
 * Facade où sont regroupées les méthodes en lien avec tous les Utilisateurs.
 */
public interface FacadeGenerale {

    /**
     * Methode permettant de s'inscrire (créer un nouvel utilisateur/étudiant)
     * @param email email de l'étudiant
     * @param motDePasse mot de passe de l'étudiant
     * @param numEtudiant numéro de l'étudiant
     * @param nom nom de l'étudiant
     * @param prenom prénom de l'étudiant
     * @param departement département d'étude de l'étudiant
     * @return l'id de l'utilisateur nouvellement créé
     * @throws InformationManquanteException quand une information n'a pas été donnée
     * @throws InformationIncorrecteException quand une information a un format erroné
     */
    public int inscription(String email,
                              String motDePasse,
                              String numEtudiant,
                              String nom,
                              String prenom,
                              String departement)
            throws InformationManquanteException, InformationIncorrecteException;


    /**
     * Méthode permettant de se connecter
     * @param email email de l'utilisateur
     * @param motDePasse mot de passe de l'utilisateur
     * @return l'id de l'utilisateur
     * @throws InformationManquanteException quand une information n'a pas été donnée
     * @throws InformationIncorrecteException quand une information est manquante
     * @throws UtilsateurInconnuException quand aucun utilisateur ne correspond aux informations donnée
     */
    public int connexion(String email, String motDePasse)
            throws InformationManquanteException, InformationIncorrecteException, UtilsateurInconnuException;

    /**
     * Rècupère l'objet d'un livre
     * @param idLivre l'id du livre à récupérer
     * @return l'objet Livre
     * @throws LivreInconnuException quand le livre n'est pas trouvé.
     */
    public Livre getLivre(int idLivre)
            throws LivreInconnuException;

    /**
     * Pour récupérer les livres present dans le catalogue (tous les livres)
     * TO DO potentiel : Paramètre pour la recherche à ajouter
     * @return la liste des livres dans le catalogue (à éditer pour la pagination)
     */
    public List<Livre> getCatalogue();

    /**
     * Recupere les info de la BU
     * @param idBu l'id de la BU visée par la recherche
     * @return le texte d'information de la BU
     * @throws BuInconnueException quand la BU n'est pas trouvée
     */
    public String infoBU(int idBu)
            throws BuInconnueException;
}
