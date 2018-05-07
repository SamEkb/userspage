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
 * Сервлет регистрации нового пользователя.
 */
public class RegisterServlet extends HttpServlet {
    /**
     * Константа дао.
     */
    private static final String DAO = "userDao";
    /**
     * Страница jsp регистрации.
     */
    private static final String REGISTER_PAGE = "/WEB-INF/view/register.jsp";
    /**
     * Страница jsp регистрации.
     */
    private static final String REGISTER = "/register";
    /**
     * Главная страница.
     */
    private static final String MAIN = "/";
    /**
     * дао.
     */
    private UserDaoImpl userDao;

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
     * Гет метод открывающий страницу регистрации нового пользователя.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException ServletException
     * @throws IOException      IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(REGISTER_PAGE).forward(req, resp);
    }

    /**
     * Метод пост регистрирующий новго пользователя.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException ServletException
     * @throws IOException      IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AdminValidator adminValidator = new AdminValidator();
        String name = req.getParameter(User.NAME);
        String login = req.getParameter(User.LOGIN);
        String password = req.getParameter(User.PASSWORD);
        String email = req.getParameter(User.EMAIL);

        if (adminValidator.requestValidator(req)) {
            resp.sendRedirect(req.getContextPath() + REGISTER);
        } else {
            User user = new User(name, login, password, User.ROLE.USER, email);
            userDao.insert(user);
            resp.sendRedirect(MAIN);
        }
    }
}
