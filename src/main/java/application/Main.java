package application;

import controleur.Controleur;
import javafx.application.Application;
import javafx.stage.Stage;
import vues.FabriqueVues;
import vues.jfx.FabriqueVuesJFX;
import vues.terminal.FabriqueVuesTerminal;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FabriqueVues fabriqueVues = new FabriqueVuesTerminal();
                //new FabriqueVuesJFX(stage);
        Controleur controleur = new Controleur(fabriqueVues);
        controleur.run();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
