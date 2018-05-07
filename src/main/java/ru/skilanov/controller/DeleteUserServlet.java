package ru.skilanov.controller;

import ru.skilanov.dao.UserDaoImpl;
import ru.skilanov.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет удаления пользователя.
 */
public class DeleteUserServlet extends HttpServlet {
    /**
     * Константа дао.
     */
    private static final String DAO = "userDao";
    /**
     * Страница jsp списка всех пользователй.
     */
    private static final String LIST = "/list";
    /**
     * дао.
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
     * Метод пост для удаления пользователя.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws IOException IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter(User.ID));
        userDao.deleteById(id);
        resp.sendRedirect(req.getContextPath() + LIST);
    }
}
