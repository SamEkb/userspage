package ru.skilanov.controller;

import ru.skilanov.dao.UserDaoImpl;
import ru.skilanov.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        UserDaoImpl dao = new UserDaoImpl();
//        String login = req.getParameter("login");
//        String password = req.getParameter("password");
//
//        User user = new User(login, password);
//
//        if (dao.userIsExist(login, password)) {
//            User.ROLE role = dao.getRoleByLoginPassword(login, password);
//            req.getSession().setAttribute("user", user);
//            moveToMenu(req, resp, role);
//        }
    }

//    private void moveToMenu(HttpServletRequest req, HttpServletResponse res, User.ROLE role) throws ServletException, IOException {
//        if (role.equals(User.ROLE.ADMIN)) {
//            req.getRequestDispatcher("/WEB-INF/view/adminPage.jsp").forward(req, res);
//        } else if (role.equals(User.ROLE.USER)) {
//            req.getRequestDispatcher("/WEB-INF/view/userPage.jsp").forward(req, res);
//        } else {
//            req.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(req, res);
//        }
//    }
}
