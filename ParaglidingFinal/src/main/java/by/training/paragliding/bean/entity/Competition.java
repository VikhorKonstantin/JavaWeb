package by.training.paragliding.bean.entity;

import java.time.LocalDate;
import java.util.Map;

public class Competition {

    public Competition() {
    }

    public Competition(final int newId, final String newName,
                       final LocalDate newDate, final String newDiscipline,
                       final Status newStatus, final String newDescription,
                       final float newParticipationFee) {
        id = newId;
        name = newName;
        date = newDate;
        discipline = newDiscipline;
        status = newStatus;
        description = newDescription;
        participationFee = newParticipationFee;
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
     * Competition discipline.
     */
    private String discipline;

    /**
     * Competition statuses enumeration.
     */
    public enum Status {
        ANNOUNCED,
        REGISTRATION_OPENED,
        REGISTRATION_CLOSED,
        UNDERWAY,
        FINISHED;
    }

    /**
     * Competition organizer.
     */
    private User organizer;

    /**
     * Returns competition id.
     *
     * @return competition id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets competition id into newId.
     *
     * @param newId new id
     */
    public void setId(final int newId) {
        id = newId;
    }

    /**
     * Returns competition date.
     *
     * @return competition date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets competition date into newDate.
     *
     * @param newDate new date
     */
    public void setDate(final LocalDate newDate) {
        date = newDate;
    }

    /**
     * Returns competition name.
     *
     * @return competition name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets competition name into newName.
     *
     * @param newName new name
     */
    public void setName(final String newName) {
        name = newName;
    }
    /**
     * Returns competition status.
     *
     * @return competition status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets competition status into newStatus.
     *
     * @param newStatus new status
     */
    public void setStatus(final Status newStatus) {
        status = newStatus;
    }

    /**
     * Returns competition description.
     *
     * @return competition description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets competition description into newDescription.
     *
     * @param newDescription new description
     */
    public void setDescription(final String newDescription) {
        description = newDescription;
    }

    /**
     * Returns competition participationFee.
     *
     * @return competition participationFee
     */
    public float getParticipationFee() {
        return participationFee;
    }

    /**
     * Sets competition participationFee into newParticipationFee.
     *
     * @param newParticipationFee new participationFee
     */
    public void setParticipationFee(final float newParticipationFee) {
        participationFee = newParticipationFee;
    }

    /**
     * Returns competition discipline.
     *
     * @return competition discipline
     */
    public String getDiscipline() {
        return discipline;
    }

    /**
     * Sets competition discipline into newDiscipline.
     *
     * @param newDiscipline new discipline
     */
    public void setDiscipline(final String newDiscipline) {
        discipline = newDiscipline;
    }

    /**
     * Returns competition results.
     *
     * @return competition results
     */
    public Map<Sportsman, Integer> getResults() {
        return results;
    }

    /**
     * Sets competition results into newResults.
     *
     * @param newResults new results
     */
    public void setResults(final Map<Sportsman, Integer> newResults) {
        results = newResults;
    }
    /**
     * Returns competition organizer.
     *
     * @return competition organizer
     */
    public User getOrganizer() {
        return organizer;
    }
    /**
     * Sets competition organizer into newOrganizer.
     *
     * @param newOrganizer new organizer
     */
    public void setOrganizer(final User newOrganizer) {
        organizer = newOrganizer;
    }
}
