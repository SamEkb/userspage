package ru.skilanov.controller;

import ru.skilanov.dao.UserDaoImpl;
import ru.skilanov.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {
    private static final String INDEX = "/WEB-INF/view/index.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(INDEX).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDaoImpl dao = new UserDaoImpl();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User.ROLE role = dao.getRoleByLoginPassword(login, password);
        int id = dao.findByLogin(login);

        User user = dao.findById(id);

        if (dao.userIsExist(login, password)) {

            req.getSession().setAttribute("user", user);
            moveToMenu(req, resp, role);
        } else {
            moveToMenu(req, resp, User.ROLE.UNKNOWN);
        }
    }

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
