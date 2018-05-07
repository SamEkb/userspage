package ru.skilanov.controller.filter;

import ru.skilanov.dao.UserDaoImpl;
import ru.skilanov.database.ConnectionFactory;
import ru.skilanov.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

/**
 * Фильтр аутентификации и авторизации.
 */
public class AuthenticationFilter implements Filter {
    private ConnectionFactory connectionFactory = ConnectionFactory.getInstance();

    /**
     * Инициализация.
     *
     * @param filterConfig FilterConfig
     * @throws ServletException ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * Закрытие.
     */
    @Override
    public void destroy() {

    }

    /**
     * Метод служит для проведения аутентификации и авторизации.
     *
     * @param servletRequest  ServletRequest
     * @param servletResponse ServletResponse
     * @param filterChain     FilterChain
     * @throws IOException      IOException
     * @throws ServletException ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        UserDaoImpl dao = new UserDaoImpl(connectionFactory);
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        int id = dao.findByLogin(login, password);

        User user = dao.findById(id);

        HttpSession session = request.getSession();

        if (nonNull(session) && nonNull(session.getAttribute("user"))) {
            User role = (User) session.getAttribute("user");
            moveToMenu(request, response, role.getRole());
        } else if (dao.userIsExist(login, password)) {
            User.ROLE role = dao.getRoleByLoginPassword(login, password);
            request.getSession().setAttribute("user", user);
            moveToMenu(request, response, role);
        } else {
            moveToMenu(request, response, User.ROLE.UNKNOWN);
        }
    }

    /**
     * Метод служит для перенаправления пользователя в соответствующий раздел после авторизации.
     *
     * @param req  HttpServletRequest
     * @param res  HttpServletResponse
     * @param role User.ROLE
     * @throws ServletException ServletException
     * @throws IOException      IOException
     */
    private void moveToMenu(HttpServletRequest req, HttpServletResponse res, User.ROLE role) throws ServletException, IOException {
        if (role.equals(User.ROLE.ADMIN)) {
            req.getRequestDispatcher("/WEB-INF/view/adminPage.jsp").forward(req, res);
        } else if (role.equals(User.ROLE.USER)) {
            req.getRequestDispatcher("/WEB-INF/view/userPage.jsp").forward(req, res);
        } else {
            req.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(req, res);
        }
    }
}
