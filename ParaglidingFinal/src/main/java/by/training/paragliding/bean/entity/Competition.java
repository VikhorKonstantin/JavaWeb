package by.training.paragliding.bean.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Competition implements Serializable {

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

    @Override
    public boolean equals(final Object newO) {
        if (this == newO) {
            return true;
        }
        if (newO == null || getClass() != newO.getClass()) {
            return false;
        }
        Competition that = (Competition) newO;
        return id == that.id
                && Float.compare(that.participationFee, participationFee) == 0
                && Objects.equals(name, that.name)
                && Objects.equals(date, that.date)
                && status == that.status
                && Objects.equals(description, that.description)
                && Objects.equals(discipline, that.discipline)
                && Objects.equals(organizer, that.organizer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, date, status,
                description, participationFee, discipline, organizer);
    }

    @Override
    public String toString() {
        return "Competition{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", date=" + date
                + ", status=" + status
                + ", description='" + description + '\''
                + ", participationFee=" + participationFee
                + ", discipline='" + discipline + '\''
                + ", organizer=" + organizer
                + '}';
    }
}
