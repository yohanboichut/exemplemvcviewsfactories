package vues.jfx;

import controleur.Controleur;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import vues.Vue;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class VueAccueilJFX implements Vue {


    @FXML
    TextField pseudo;

    @FXML
    Button go;

    private Stage stage;
    private Controleur controleur;


    private void setScene(Scene scene) {
        this.scene = scene;
    }

    private Scene scene;

    private void setStage(Stage stage) {
        this.stage = stage;
    }

    public static VueAccueilJFX creer(Stage stage) {
        URL location = VueAccueilJFX.class.getResource("accueil.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        VueAccueilJFX vue = fxmlLoader.getController();
        vue.setStage(stage);
        Scene scene = new Scene(root);
        vue.setScene(scene);
        vue.initialiserBouton();
        return vue;
    }

    @Override
    public void show() {
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    private void initialiserBouton() {
        this.go.setOnAction(e -> connexion());
    }

    private void connexion() {
        String pseudoJoueur = this.pseudo.getText();
        if (Objects.isNull(pseudoJoueur) || pseudoJoueur.length()<2) {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Vous devez renseigner un pseudo d'au moins 2 caractères !!", ButtonType.OK);
            alert.showAndWait();
        }
        else {
            controleur.connexion(pseudoJoueur);
        }
    }

    @Override
    public void chargerDonnees() {

    }

    @Override
    public void initialiserControleur(Controleur controleur) {
        this.controleur = controleur;
    }
}
