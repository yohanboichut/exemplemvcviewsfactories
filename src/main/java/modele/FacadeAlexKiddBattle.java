package modele;

import modele.exceptions.ChoixIncompletsException;
import modele.exceptions.ChoixInnatenduException;
import modele.exceptions.PartieNonTermineeException;

public interface FacadeAlexKiddBattle {
    /**
     * Permet d'initialiser le choix du joueur
     * @param choix
     * @throws ChoixInnatenduException : le choix passé ne correspond pas à un choix attendu
     */
    void setChoixCourantJoueur(String choix) throws ChoixInnatenduException;


    /**
     * Permet d'initialiser le choix d'Alex Kidd
     * @return
     */

    String choixAlexKidd();

    /**
     * Permet de mettre à jour les scores en fonction des choix
     * @throws ChoixIncompletsException : il manque au moins un choix des deux joueurs
     */

    void miseAJourResultat() throws ChoixIncompletsException;

    /**
     * Retourne vrai si la partie est terminée i.e. il y a deux points d'écarts entre les deux joueurs
     * @return
     */
    boolean partieTerminee();

    /**
     * Permet de récupérer le score en cours
     * @return
     */
    Score getScore();

    /**
     * Permet de récupérer le nom du vainqueur
     * @return
     * @throws PartieNonTermineeException : La partie n'est pas terminée et il n'y a pas encore de vainqueur
     */
    String getVainqueur() throws PartieNonTermineeException;

    void setJoueur(String pseudoJoueur);

    String getNomJoueur();

    String PIERRE="Pierre";
    String FEUILLE="Feuille";
    String CISEAUX = "Ciseaux";


}
