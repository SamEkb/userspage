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
 * Метод изменения пользователя.
 */
public class UpdateUserServlet extends HttpServlet {
    /**
     * Константа дао.
     */
    private static final String DAO = "userDao";
    /**
     * Атрибут.
     */
    private static final String USER = "user";
    /**
     * Страница jsp изменения пользователя.
     */
    private static final String UPDATE = "/WEB-INF/view/adminUpdate.jsp";
    /**
     * Страница списка всех пользователей.
     */
    private static final String LIST = "/list";
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
     * Гет метод открывающий страницу изменения пользователя.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException ServletException
     * @throws IOException      IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter(User.ID));

        User user = userDao.findById(id);

        req.setAttribute(USER, user);

        req.getRequestDispatcher(UPDATE).forward(req, resp);
    }

    /**
     * Пост метод изменяющий пользователя.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws IOException IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        AdminValidator adminValidator = new AdminValidator();
        int id = Integer.parseInt(req.getParameter(User.ID));
        String name = req.getParameter(User.NAME);
        String login = req.getParameter(User.LOGIN);
        String password = req.getParameter(User.PASSWORD);
        User.ROLE role = User.getRole(req.getParameter(User.UROLE));
        String email = req.getParameter(User.EMAIL);

        if (adminValidator.requestValidator(req)) {
            resp.sendRedirect(req.getContextPath() + LIST);
        } else {
            User user = userDao.findById(id);
            user.setName(name);
            user.setLogin(login);
            user.setPassword(password);
            user.setRole(role);
            user.setEmail(email);

            userDao.update(user);
            resp.sendRedirect(req.getContextPath() + LIST);
        }
    }
}
