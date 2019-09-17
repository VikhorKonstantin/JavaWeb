package by.training.sqltask.bean.entity;

import java.time.LocalDate;
import java.util.Enumeration;
import java.util.Map;

public class Competition {
    /**
     * Competition id.
     */
    private int id;
    /**
     * Competition date.
     */
    private LocalDate date;
    /**
     * Competition name.
     */
    private String name;
    /**
     * Competition discipline.
     */
    private Discipline discipline;
    /**
     * Competition status.
     */
    private Status status;
    /**
     * Description of competition.
     */
    private String description;
    /**
     * Participation fee(in USA Dollars).
     */
    private float participationFee;
    /**
     * Results of competition.
     */
    private Map<Sportsman, Integer> results;

    public enum Discipline {
        PG_AC("Paragliding Accuracy"),
        PG("Paragliding"),
        PG_AER_SOLO("Paragliding Aerobatic Solo"),
        PG_AER_SYNCRO("Paragliding Aerobatic Syncro"),
        HG_C1("Hang Gliding Class 1"),
        HG_C2("Hang Gliding Class 2");

        private String name;

        Discipline(final String newName) {
            name = newName;
        }

        public String getName() {
            return name;
        }
    }

    public enum Status {
        ANNOUNCED,
        REGISTRATION_OPENED,
        REGISTRATION_CLOSED,
        UNDERWAY,
        FINISHED;
    }


    public Competition(final int newId, final LocalDate newDate, final String newName,
                       final Discipline newDiscipline, final Status newStatus, final String newDescription,
                       final float newParticipationFee, final Map<Sportsman, Integer> newResults) {
        id = newId;
        date = newDate;
        name = newName;
        discipline = newDiscipline;
        status = newStatus;
        description = newDescription;
        participationFee = newParticipationFee;
        results = newResults;
    }


    public int getId() {
        return id;
    }

    public void setId(final int newId) {
        id = newId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(final LocalDate newDate) {
        date = newDate;
    }

    public String getName() {
        return name;
    }

    public void setName(final String newName) {
        name = newName;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(final Discipline newDiscipline) {
        discipline = newDiscipline;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(final Status newStatus) {
        status = newStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String newDescription) {
        description = newDescription;
    }

    public float getParticipationFee() {
        return participationFee;
    }

    public void setParticipationFee(final float newParticipationFee) {
        participationFee = newParticipationFee;
    }

    public Map<Sportsman, Integer> getResults() {
        return results;
    }

    public void setResults(final Map<Sportsman, Integer> newResults) {
        results = newResults;
    }
}
