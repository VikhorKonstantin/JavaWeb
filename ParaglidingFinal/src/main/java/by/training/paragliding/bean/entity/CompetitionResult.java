package by.training.paragliding.bean.entity;

import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(final Object newO) {
        if (this == newO) {
            return true;
        }
        if (newO == null || getClass() != newO.getClass()) {
            return false;
        }
        CompetitionResult that = (CompetitionResult) newO;
        return score == that.score
                && Objects.equals(competition, that.competition)
                && Objects.equals(sportsman, that.sportsman);
    }

    @Override
    public int hashCode() {
        return Objects.hash(competition, score, sportsman);
    }

    @Override
    public String toString() {
        return "CompetitionResult{"
                + "competition=" + competition
                + ", score=" + score
                + ", sportsman=" + sportsman
                + '}';
    }
}
