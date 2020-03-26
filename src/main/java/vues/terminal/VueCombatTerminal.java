package vues.terminal;

import controleur.Controleur;
import modele.FacadeAlexKiddBattle;
import modele.Score;
import modele.exceptions.ChoixIncompletsException;
import modele.exceptions.ChoixInnatenduException;
import vues.Vue;
import vues.VueCombat;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

public class VueCombatTerminal implements VueCombat {
    private Controleur controleur;

    private String pseudo;
    private String choixAlexKidd;
    private String leScore;

    private String choixJoueur;

    private Map<Integer,String> conversion;


    public VueCombatTerminal() {
        conversion = new HashMap<>();
        conversion.put(1, FacadeAlexKiddBattle.PIERRE);
        conversion.put(2, FacadeAlexKiddBattle.CISEAUX);
        conversion.put(3, FacadeAlexKiddBattle.FEUILLE);
    }

    @Override
    public void show() {


        if (!Objects.isNull(choixJoueur) && !Objects.isNull(choixAlexKidd)) {
            System.out.println(pseudo+" : "+choixJoueur + " vs Alex Kidd :"+choixAlexKidd);
        }
        System.out.println("Le score est : "+this.leScore);

        System.out.println("Choisir entre :");
        System.out.println("1 - Pierre");
        System.out.println("2 - Ciseaux");
        System.out.println("3 - Feuille");

        int choix;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Saisir 1, 2 ou 3 :");
            choix = scanner.nextInt();
        } while (choix<1 || choix >3);


        try {
            this.choixJoueur = this.conversion.get(choix);
            this.controleur.jouer(this.choixJoueur);
        } catch (ChoixInnatenduException | ChoixIncompletsException e) {
            this.show();
        }

    }

    @Override
    public void chargerDonnees() {
        this.pseudo = this.controleur.getNomJoueur();
        this.majScore();

    }



    private void majScore(){
        Score score = this.controleur.getScore();
        this.leScore =  pseudo + " "+score.getScoreJoueur() + " / " +score.getScoreAlexKidd() + " Alex Kidd";
    }
    @Override
    public void initialiserControleur(Controleur controleur) {
        this.controleur = controleur;
    }

    @Override
    public void majApresAlexKidd() {
        choixAlexKidd = this.controleur.getChoixAlexKidd();
        this.majScore();

    }
}
