package by.training.paragliding.bean.entity;

import java.io.Serializable;
import java.util.Objects;

public class Result implements Serializable {
    private int sportsmanId;
    private int competitionId;
    private int score;

    public Result() {
    }

    public Result(final int newSportsmanId,
                  final int newCompetitionId, final int newScore) {
        sportsmanId = newSportsmanId;
        competitionId = newCompetitionId;
        score = newScore;
    }

    public int getSportsmanId() {
        return sportsmanId;
    }

    public void setSportsmanId(final int newSportsmanId) {
        sportsmanId = newSportsmanId;
    }

    public int getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(final int newCompetitionId) {
        competitionId = newCompetitionId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(final int newScore) {
        score = newScore;
    }

    @Override
    public boolean equals(final Object newO) {
        if (this == newO) {
            return true;
        }
        if (newO == null || getClass() != newO.getClass()) {
            return false;
        }
        Result result = (Result) newO;
        return sportsmanId == result.sportsmanId
                && competitionId == result.competitionId
                && score == result.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sportsmanId, competitionId, score);
    }

    @Override
    public String toString() {
        return "Result{"
                + "sportsmanId=" + sportsmanId
                + ", competitionId=" + competitionId
                + ", score=" + score
                + '}';
    }
}
