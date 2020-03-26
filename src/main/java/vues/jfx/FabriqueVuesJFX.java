package vues.jfx;

import javafx.stage.Stage;
import vues.FabriqueVues;
import vues.Vue;
import vues.VueCombat;

public class FabriqueVuesJFX implements FabriqueVues {
    private Stage stage;

    public FabriqueVuesJFX(Stage stage) {
        this.stage = stage;
    }

    @Override
    public Vue creerAccueil() {
        return VueAccueilJFX.creer(stage);
    }

    @Override
    public Vue creerResultat() {
        return VueResultatJFX.creer(stage);
    }

    @Override
    public VueCombat creerCombat() {
        return VueCombatJFX.creer(stage);
    }
}
