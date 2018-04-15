package ru.skilanov.dao;

import ru.skilanov.model.User;

import java.util.List;

public interface UserDao {

    List<User> getAll();

    void insert(User user);

    void deleteById(int id);

    void update(User user);

    User findById(int id);

    boolean userIsExist(String login, String password);

    User.ROLE getRoleByLoginPassword(String login, String password);

    int findByLogin(String login, String password);
}
