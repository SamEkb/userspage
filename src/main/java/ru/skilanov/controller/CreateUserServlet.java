package ru.skilanov.controller;

import ru.skilanov.controller.validator.AdminValidator;
import ru.skilanov.dao.UserDaoImpl;
import ru.skilanov.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет создания пользователя.
 */
public class CreateUserServlet extends HttpServlet {
    /**
     * Страница jsp создания пользователя.
     */
    private static final String INDEX = "/WEB-INF/view/create.jsp";
    /**
     * Страница всех пользователей.
     */
    private static final String LIST = "/list";
    /**
     * Константа дао.
     */
    private static final String DAO = "userDao";
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
     * Гет метод для получения страницы создания пользователя.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException ServletException
     * @throws IOException      IOException
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(INDEX).forward(req, resp);
    }

    /**
     * Пост метод для создания нового пользователя.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException ServletException
     * @throws IOException      IOException
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AdminValidator adminValidator = new AdminValidator();
        String name = req.getParameter(User.NAME);
        String login = req.getParameter(User.LOGIN);
        String password = req.getParameter(User.PASSWORD);
        User.ROLE role = User.getRole(req.getParameter(User.UROLE));
        String email = req.getParameter(User.EMAIL);

        User user = new User(name, login, password, role, email);

        if (adminValidator.requestValidator(req)) {
            req.getRequestDispatcher(INDEX).forward(req, resp);
        } else {
            userDao.insert(user);
            resp.sendRedirect(req.getContextPath() + LIST);
        }
    }
}
