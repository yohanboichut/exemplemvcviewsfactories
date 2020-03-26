package vues.terminal;

import controleur.Controleur;
import modele.exceptions.PartieNonTermineeException;
import vues.Vue;

import java.util.Scanner;

public class VueResultatTerminal implements Vue {

    private Controleur controleur;
    private String vainqueur;

    @Override
    public void show() {

        System.out.println("####################################################");
        System.out.println("##########        Fin de la Battle       ###########");
        System.out.println("####################################################");
        System.out.println("Le vainqueur est "+ this.vainqueur);
        System.out.println("Voulez vous refaire une partie (o/n) ?");
        Scanner scanner = new Scanner(System.in);

        String choix = scanner.nextLine();
        if ("o".equals(choix)) {
            this.controleur.goToMenu();
        }
        else {
            System.exit(0);
        }

    }


    @Override
    public void chargerDonnees() {
        try {
            this.vainqueur = this.controleur.getVainqueur();
        } catch (PartieNonTermineeException e) {
            this.controleur.goToMenu();
        }
    }

    @Override
    public void initialiserControleur(Controleur controleur) {
        this.controleur = controleur;
    }
}
