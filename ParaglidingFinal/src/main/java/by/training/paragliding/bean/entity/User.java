package by.training.paragliding.bean.entity;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    /**
     * User id.
     */
    private int id;
    /**
     * User email.
     */
    private String email;
    /**
     * User password.
     */
    private String password;
    /**
     * User role.
     */
    private Role role;

    private Sportsman sportsman;

    public int getId() {
        return id;
    }

    public void setId(final int newId) {
        id = newId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String newEmail) {
        email = newEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String newPassword) {
        password = newPassword;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(final Role newRole) {
        role = newRole;
    }

    public Sportsman getSportsman() {
        return sportsman;
    }

    public void setSportsman(final Sportsman newSportsman) {
        sportsman = newSportsman;
    }

    @Override
    public boolean equals(final Object newO) {
        if (this == newO) return true;
        if (newO == null || getClass() != newO.getClass()) return false;
        User user = (User) newO;
        return id == user.id &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                role == user.role &&
                Objects.equals(sportsman, user.sportsman);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
