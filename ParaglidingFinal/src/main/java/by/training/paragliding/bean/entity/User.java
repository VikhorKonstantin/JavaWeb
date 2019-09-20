package by.training.paragliding.bean.entity;

public class User {
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
}
