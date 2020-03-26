package vues.jfx;

import controleur.Controleur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.FacadeAlexKiddBattle;
import modele.Score;
import modele.exceptions.ChoixIncompletsException;
import modele.exceptions.ChoixInnatenduException;
import vues.VueCombat;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class VueCombatJFX implements VueCombat {
    private Stage stage;
    private Scene scene;
    private Controleur controleur;


    @FXML
    Label pseudo;

    @FXML
    Label nomJoueur;


    @FXML
    RadioButton radioPierre;


    @FXML
    RadioButton radioCiseaux;

    @FXML
    RadioButton radioFeuille;


    @FXML
    Label scorePseudo;

    @FXML
    Label scoreAlexKidd;



    @FXML
    ImageView rien;

    @FXML
    ImageView pierre;

    @FXML
    ImageView ciseaux;

    @FXML
    ImageView feuille;

    @FXML
    VBox vBoxImages;



    private ImageView imageCourante;
    private ToggleGroup toggleGroup;

    private void setStage(Stage stage) {
        this.stage = stage;
    }

    private void setScene(Scene scene) {
        this.scene = scene;
    }

    public static VueCombat creer(Stage stage) {
        URL location = VueCombatJFX.class.getResource("combat.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        VueCombatJFX vue = fxmlLoader.getController();
        vue.setStage(stage);
        Scene scene = new Scene(root);
        vue.setScene(scene);
        vue.initialiserRadioBoutons();
        vue.initialiserImages();
        return vue;
    }

    private void initialiserRadioBoutons() {
        this.toggleGroup = new ToggleGroup();
        this.radioCiseaux.setToggleGroup(toggleGroup);
        this.radioFeuille.setToggleGroup(toggleGroup);
        this.radioPierre.setToggleGroup(toggleGroup);
    }



    private void initialiserImages() {
        this.imageCourante = rien;
        this.vBoxImages.getChildren().removeAll(pierre,ciseaux,feuille);
    }


    @Override
    public void show() {
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    @Override
    public void chargerDonnees() {
        String nom = controleur.getNomJoueur();
        this.nomJoueur.setText(nom);
        this.pseudo.setText(nom);
        this.initialiserScore();
    }




    private void initialiserScore() {
        Score score = this.controleur.getScore();
        this.scoreAlexKidd.setText(Integer.toString(score.getScoreAlexKidd()));
        this.scorePseudo.setText(Integer.toString(score.getScoreJoueur()));

    }






    @Override
    public void initialiserControleur(Controleur controleur) {
        this.controleur = controleur;
    }

    public void jouer(ActionEvent actionEvent) {

        RadioButton radioButton = (RadioButton) toggleGroup.getSelectedToggle();
        if (Objects.isNull(radioButton)) {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Vous devez choisir entre Pierre/Ciseaux/Feuille !!!", ButtonType.OK);
            alert.showAndWait();
        }
        else {
            String choix = radioButton.getText();
            try {
                this.controleur.jouer(choix);
            } catch (ChoixInnatenduException | ChoixIncompletsException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Vous devez choisir entre Pierre/Ciseaux/Feuille !!!", ButtonType.OK);
                alert.showAndWait();
            }
        }
    }


    @Override
    public void majApresAlexKidd() {
        String choixAlexKidd = this.controleur.getChoixAlexKidd();
        this.initialiserScore();

        if (FacadeAlexKiddBattle.PIERRE.equals(choixAlexKidd)) {
            this.vBoxImages.getChildren().remove(imageCourante);
            this.imageCourante = pierre;
            this.vBoxImages.getChildren().add(pierre);
        }
        if (FacadeAlexKiddBattle.CISEAUX.equals(choixAlexKidd)) {
            this.vBoxImages.getChildren().remove(imageCourante);
            this.imageCourante = ciseaux;
            this.vBoxImages.getChildren().add(ciseaux);
        }

        if (FacadeAlexKiddBattle.FEUILLE.equals(choixAlexKidd)) {
            this.vBoxImages.getChildren().remove(imageCourante);
            this.imageCourante = feuille;
            this.vBoxImages.getChildren().add(feuille);
        }

        this.show();


    }
}
