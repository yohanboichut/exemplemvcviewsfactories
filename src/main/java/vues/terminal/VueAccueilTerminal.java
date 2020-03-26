package vues.terminal;

import controleur.Controleur;
import vues.Vue;

import java.util.Scanner;

public class VueAccueilTerminal implements Vue {

    private Controleur controleur;


    @Override
    public void show() {

        System.out.println("####################################################");
        System.out.println("##########  SHIFUMI BATTLE vs Alex Kidd  ###########");
        System.out.println("####################################################");


        Scanner scanner = new Scanner(System.in);
        String pseudo = "";
        do {
            System.out.println("Saisir votre pseudo d'au moins trois caractères :");
            pseudo = scanner.nextLine();
        } while (pseudo.length() <=2);

        this.controleur.connexion(pseudo);

    }

    @Override
    public void chargerDonnees() {

    }

    @Override
    public void initialiserControleur(Controleur controleur) {
        this.controleur = controleur;
    }
}
