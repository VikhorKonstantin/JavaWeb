package by.training.paragliding.bean.entity;

import java.time.LocalDate;
import java.util.Map;

public class Competition {

    public Competition() {
    }

    public Competition(final int newId, final String newName,
                       final LocalDate newDate, final Discipline newDiscipline,
                       final Status newStatus, final String newDescription,
                       final float newParticipationFee,
                       final Map<Sportsman, Integer> newResults) {
        id = newId;
        name = newName;
        date = newDate;
        discipline = newDiscipline;
        status = newStatus;
        description = newDescription;
        participationFee = newParticipationFee;
        results = newResults;
    }

    /**
     * Competition id.
     */
    private int id;
    /**
     * Competition name.
     */
    private String name;
    /**
     * Competition date.
     */
    private LocalDate date;
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

    /**
     * Competition disciplines enumiration.
     */
    public enum Discipline {
        /**
         * Define "Paragliding Accuracy" discipline.
         */
        PG_AC("Paragliding Accuracy"),
        /**
         * Define "Paragliding" discipline.
         */
        PG("Paragliding"),
        /**
         * Define "Paragliding Aerobatic Solo" discipline.
         */
        PG_AER_SOLO("Paragliding Aerobatic Solo"),
        /**
         * Define "Paragliding Aerobatic Syncro" discipline.
         */
        PG_AER_SYNCRO("Paragliding Aerobatic Syncro"),
        /**
         * Define "Hang Gliding Class 1" discipline.
         */
        HG_C1("Hang Gliding Class 1"),
        /**
         * Define "Hang Gliding Class 2" discipline.
         */
        HG_C2("Hang Gliding Class 2");
        /**
         * Name of Dicipline.
         */
        private String name;

        /**
         * Creates discipline by newName.
         * @param newName name of discipline
         */
        Discipline(final String newName) {
            name = newName;
        }

        /**
         * Returns name of discipline.
         * @return name of discipline
         */
        public String getName() {
            return name;
        }
    }

    /**
     * Competition statuses enumiration.
     */
    public enum Status {
        ANNOUNCED,
        REGISTRATION_OPENED,
        REGISTRATION_CLOSED,
        UNDERWAY,
        FINISHED;
    }

    /**
     * Returns competition id.
     * @return competition id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets competition id into newId.
     * @param newId new id
     */
    public void setId(final int newId) {
        id = newId;
    }
    /**
     * Returns competition date.
     * @return competition date
     */
    public LocalDate getDate() {
        return date;
    }
    /**
     * Sets competition date into newDate.
     * @param newDate new date
     */
    public void setDate(final LocalDate newDate) {
        date = newDate;
    }
    /**
     * Returns competition name.
     * @return competition name
     */
    public String getName() {
        return name;
    }
    /**
     * Sets competition name into newName.
     * @param newName new name
     */
    public void setName(final String newName) {
        name = newName;
    }
    /**
     * Returns competition discipline.
     * @return competition discipline
     */
    public Discipline getDiscipline() {
        return discipline;
    }
    /**
     * Sets competition discipline into newDiscipline.
     * @param newDiscipline new discipline
     */
    public void setDiscipline(final Discipline newDiscipline) {
        discipline = newDiscipline;
    }
    /**
     * Returns competition status.
     * @return competition status
     */
    public Status getStatus() {
        return status;
    }
    /**
     * Sets competition status into newStatus.
     * @param newStatus new status
     */
    public void setStatus(final Status newStatus) {
        status = newStatus;
    }
    /**
     * Returns competition description.
     * @return competition description
     */
    public String getDescription() {
        return description;
    }
    /**
     * Sets competition description into newDescription.
     * @param newDescription new description
     */
    public void setDescription(final String newDescription) {
        description = newDescription;
    }
    /**
     * Returns competition participationFee.
     * @return competition participationFee
     */
    public float getParticipationFee() {
        return participationFee;
    }
    /**
     * Sets competition participationFee into newParticipationFee.
     * @param newParticipationFee new participationFee
     */
    public void setParticipationFee(final float newParticipationFee) {
        participationFee = newParticipationFee;
    }

    /**
     * Returns competition results.
     * @return competition results
     */
    public Map<Sportsman, Integer> getResults() {
        return results;
    }
    /**
     * Sets competition results into newResults.
     * @param newResults new results
     */
    public void setResults(final Map<Sportsman, Integer> newResults) {
        results = newResults;
    }
}
