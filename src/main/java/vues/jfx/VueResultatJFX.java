package vues.jfx;

import controleur.Controleur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import modele.exceptions.PartieNonTermineeException;
import vues.Vue;

import java.io.IOException;
import java.net.URL;

public class VueResultatJFX implements Vue {



    private Stage stage;
    private Controleur controleur;


    @FXML
    Label vainqueur;

    private void setScene(Scene scene) {
        this.scene = scene;
    }

    private Scene scene;

    private void setStage(Stage stage) {
        this.stage = stage;
    }

    public static VueResultatJFX creer(Stage stage) {
        URL location = VueResultatJFX.class.getResource("resultat.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        VueResultatJFX vue = fxmlLoader.getController();
        vue.setStage(stage);
        Scene scene = new Scene(root);
        vue.setScene(scene);
        return vue;
    }

    @Override
    public void show() {
        this.stage.setScene(this.scene);
        this.stage.show();
    }


    @Override
    public void chargerDonnees()  {
        try {
            String winner  = this.controleur.getVainqueur();
            this.vainqueur.setText(winner);
        } catch (PartieNonTermineeException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR,"La partie n'est pas terminée !!", ButtonType.OK);
        }

    }

    @Override
    public void initialiserControleur(Controleur controleur) {
        this.controleur = controleur;
    }

    public void menu(ActionEvent actionEvent) {
        this.controleur.goToMenu();

    }
}
