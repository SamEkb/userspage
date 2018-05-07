package ru.skilanov.model;

/**
 * Класс модель пользователя.
 */
public class User {
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String UROLE = "role";
    public static final String EMAIL = "email";
    private int id;
    private String name;
    private String login;
    private String password;
    private ROLE role;
    private String email;

    public User() {
    }

    public User(String name, String login, String password, ROLE role, String email) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.role = role;
        this.email = email;
    }

    public User(int id, String name, String login, String password, ROLE role, String email) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.role = role;
        this.email = email;
    }


    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static ROLE getRole(String name) {
        ROLE role = null;
        if ("admin".equalsIgnoreCase(name)) {
            role = ROLE.ADMIN;
        } else if ("user".equalsIgnoreCase(name)) {
            role = ROLE.USER;
        }
        return role;
    }

    public ROLE getRole() {
        return role;
    }

    public void setRole(ROLE role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '}';
    }

    /**
     * ENUM роли пользователя.
     */
    public enum ROLE {
        USER, ADMIN, UNKNOWN
    }
}
