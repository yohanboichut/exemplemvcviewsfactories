package modele;

public class Score {

    private int ScoreAlexKidd;
    private int ScoreJoueur;

    public Score(int scoreAlexKidd, int scoreJoueur) {
        ScoreAlexKidd = scoreAlexKidd;
        ScoreJoueur = scoreJoueur;
    }

    public int getScoreAlexKidd() {
        return ScoreAlexKidd;
    }

    public int getScoreJoueur() {
        return ScoreJoueur;
    }
}
