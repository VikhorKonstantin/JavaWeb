package by.training.paragliding.bean.entity;

import java.io.Serializable;
import java.util.Objects;

public class Application implements Serializable {
    private int sportsmanId;
    private int competitionId;

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


    @Override
    public boolean equals(final Object newO) {
        if (this == newO) return true;
        if (newO == null || getClass() != newO.getClass()) return false;
        Application that = (Application) newO;
        return sportsmanId == that.sportsmanId &&
                competitionId == that.competitionId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sportsmanId, competitionId);
    }

    @Override
    public String toString() {
        return "Application{" +
                "sportsmanId=" + sportsmanId +
                ", competitionId=" + competitionId +
                '}';
    }
}
