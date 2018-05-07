package ru.skilanov.controller;


import ru.skilanov.dao.UserDaoImpl;
import ru.skilanov.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Сервлет списка всех пользователей.
 */
public class AllUsersServlet extends HttpServlet {
    /**
     * Ссылка на jsp страницу всех пользователей.
     */
    private static final String LIST_OF_USERS = "/WEB-INF/view//usersList.jsp";
    /**
     * Константа дао.
     */
    private static final String DAO = "userDao";
    /**
     * Атрибут.
     */
    private static final String USERS = "users";
    /**
     * Дао.
     */
    private UserDaoImpl userDao;

    /**
     * Сеттер.
     *
     * @param userDao UserDaoImpl
     */
    public void setUserDao(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    /**
     * Инициализация. Получаем дао из контекста.
     *
     * @throws ServletException ServletException
     */
    @Override
    public void init() throws ServletException {
        userDao = (UserDaoImpl) getServletContext().getAttribute(DAO);
    }

    /**
     * Гет метод отвечающий на запрос страницы всех пользователей.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws IOException      IOException
     * @throws ServletException ServletException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        List<User> users = userDao.getAll();
        req.getServletContext().setAttribute(USERS, users);
        req.getRequestDispatcher(LIST_OF_USERS).forward(req, resp);
    }

    /**
     * Пост метод.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException ServletException
     * @throws IOException      IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
