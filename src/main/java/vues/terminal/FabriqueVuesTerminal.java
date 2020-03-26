package vues.terminal;

import vues.FabriqueVues;
import vues.Vue;
import vues.VueCombat;

public class FabriqueVuesTerminal implements FabriqueVues {
    @Override
    public Vue creerAccueil() {
        return new VueAccueilTerminal();
    }

    @Override
    public Vue creerResultat() {
        return new VueResultatTerminal();
    }

    @Override
    public VueCombat creerCombat() {
        return new VueCombatTerminal();
    }
}
