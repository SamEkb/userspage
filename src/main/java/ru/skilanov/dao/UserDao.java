package ru.skilanov.dao;

import ru.skilanov.model.User;

import java.util.List;

/**
 * Интерфейс дао содержащий методы для работы с user.
 */
public interface UserDao {

    /**
     * Список всех пользователей.
     *
     * @return List<User>
     */
    List<User> getAll();

    /**
     * Метод создания нового пользователя.
     *
     * @param user User
     */
    void insert(User user);

    /**
     * Метод удаления пользователя по id.
     *
     * @param id int
     */
    void deleteById(int id);

    /**
     * Метод изменения пользователя.
     *
     * @param user User
     */
    void update(User user);

    /**
     * Метод поиска пользователя по id.
     *
     * @param id int
     * @return User
     */
    User findById(int id);

    /**
     * Метод проверки существует ли пользователь.
     *
     * @param login    String
     * @param password String
     * @return boolean
     */
    boolean userIsExist(String login, String password);

    /**
     * Метод получения роли по логину и паролю.
     *
     * @param login    String
     * @param password String
     * @return ROLE
     */
    User.ROLE getRoleByLoginPassword(String login, String password);

    /**
     * Метод поиска по логину.
     *
     * @param login    String
     * @param password String
     * @return int
     */
    int findByLogin(String login, String password);
}
