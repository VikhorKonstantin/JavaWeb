package by.training.paragliding.bean.entity;

import java.io.Serializable;

public class CompetitionResult implements Serializable {
    private Competition competition;
    private int score;
    private Sportsman sportsman;

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(final Competition newCompetition) {
        competition = newCompetition;
    }

    public int getScore() {
        return score;
    }

    public void setScore(final int newScore) {
        score = newScore;
    }

    public Sportsman getSportsman() {
        return sportsman;
    }

    public void setSportsman(final Sportsman newSportsman) {
        sportsman = newSportsman;
    }
}
