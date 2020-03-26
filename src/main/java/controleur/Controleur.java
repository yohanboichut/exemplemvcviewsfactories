package controleur;

import modele.FacadeAlexKiddBattle;
import modele.FacadeAlexKiddBattleImpl;
import modele.Score;
import modele.exceptions.ChoixIncompletsException;
import modele.exceptions.ChoixInnatenduException;
import modele.exceptions.PartieNonTermineeException;
import vues.*;


public class Controleur {
    private FacadeAlexKiddBattle facadeAlexKiddBattle;
    private Vue vueAccueil;
    private Vue vueResultat;
    private VueCombat vueCombat;

    private String choixAlexKidd;


    public Controleur(FabriqueVues fabriqueVues) {
        facadeAlexKiddBattle = new FacadeAlexKiddBattleImpl();
        vueAccueil = fabriqueVues.creerAccueil();
        vueAccueil.initialiserControleur(this);
        vueResultat = fabriqueVues.creerResultat();
        vueResultat.initialiserControleur(this);
        vueCombat = fabriqueVues.creerCombat();
        vueCombat.initialiserControleur(this);
    }


    public void run() {
        vueAccueil.show();
    }


    public void connexion(String pseudoJoueur) {
        this.facadeAlexKiddBattle.setJoueur(pseudoJoueur);
        this.vueCombat.chargerDonnees();
        this.vueCombat.show();

    }

    public String getVainqueur() throws PartieNonTermineeException {
        return this.facadeAlexKiddBattle.getVainqueur();
    }

    public void goToMenu() {
        this.facadeAlexKiddBattle = new FacadeAlexKiddBattleImpl();
        this.vueAccueil.show();

    }

    public String getNomJoueur() {
        return facadeAlexKiddBattle.getNomJoueur();
    }

    public Score getScore() {
        return this.facadeAlexKiddBattle.getScore();
    }

    public void jouer(String choix) throws ChoixInnatenduException, ChoixIncompletsException {

        this.facadeAlexKiddBattle.setChoixCourantJoueur(choix);
        this.choixAlexKidd = this.facadeAlexKiddBattle.choixAlexKidd();
        this.facadeAlexKiddBattle.miseAJourResultat();
        this.vueCombat.majApresAlexKidd();

        if (this.facadeAlexKiddBattle.partieTerminee()) {
            this.vueResultat.chargerDonnees();
            this.vueResultat.show();
        }
        else {
            this.vueCombat.show();
        }


    }

    public String getChoixAlexKidd() {
        return this.choixAlexKidd;
    }
}
