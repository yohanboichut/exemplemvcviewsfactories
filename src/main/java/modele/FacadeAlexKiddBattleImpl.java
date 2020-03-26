package modele;

import modele.exceptions.ChoixIncompletsException;
import modele.exceptions.ChoixInnatenduException;
import modele.exceptions.PartieNonTermineeException;

import java.util.Objects;

public class FacadeAlexKiddBattleImpl implements FacadeAlexKiddBattle {

    private String nomJoueur;

    private int victoireJoueur;
    private int victoireAlexKidd;

    private String choixCourantJoueur;
    private String choixAlexKidd;




    @Override
    public void setChoixCourantJoueur(String choix) throws ChoixInnatenduException {
        if (PIERRE.equals(choix) || FEUILLE.equals(choix) || CISEAUX.equals(choix))
            this.choixCourantJoueur = choix;
        else
            throw new ChoixInnatenduException();
    }


    @Override
    public String choixAlexKidd() {
        int choix= (int)((Math.random())*100);
        if (choix<34) {
            this.choixAlexKidd = PIERRE;
            return PIERRE;
        }

        if (choix >= 34 && choix<67) {
            this.choixAlexKidd = CISEAUX;
            return CISEAUX;
        }
        this.choixAlexKidd = FEUILLE;
        return FEUILLE;
    }

    @Override
    public void miseAJourResultat() throws ChoixIncompletsException {
        if (Objects.isNull(this.choixAlexKidd) || Objects.isNull(this.choixCourantJoueur)) {
            throw new ChoixIncompletsException();
        }
        if (this.choixAlexKidd.equals(PIERRE)) {
            if (this.choixCourantJoueur.equals(CISEAUX)) {
                this.victoireAlexKidd++;
            }
            if (this.choixCourantJoueur.equals(FEUILLE)) {
                this.victoireJoueur++;
            }
        }

        if (this.choixAlexKidd.equals(CISEAUX)) {
            if (this.choixCourantJoueur.equals(PIERRE)) {
                this.victoireJoueur++;
            }
            if (this.choixCourantJoueur.equals(FEUILLE)) {
                this.victoireAlexKidd++;
            }
        }

        if (this.choixAlexKidd.equals(FEUILLE)) {
            if (this.choixCourantJoueur.equals(PIERRE)) {
                this.victoireAlexKidd++;
            }
            if (this.choixCourantJoueur.equals(CISEAUX)) {
                this.victoireJoueur++;
            }
        }
    }


    @Override
    public boolean partieTerminee() {
        return this.victoireJoueur == 2 || this.victoireAlexKidd ==2;
    }

    @Override
    public Score getScore(){
        return new Score(this.victoireAlexKidd, this.victoireJoueur);
    }

    @Override
    public String getVainqueur() throws PartieNonTermineeException {
        if (partieTerminee()) {
            if (this.victoireJoueur>this.victoireAlexKidd) {
                return nomJoueur;
            }
            else {
                return "Alex Kidd";
            }
        }
        else {
            throw new PartieNonTermineeException();
        }

    }

    @Override
    public void setJoueur(String pseudoJoueur) {
        this.nomJoueur = pseudoJoueur;
    }

    @Override
    public String getNomJoueur() {
        return nomJoueur;
    }
}
