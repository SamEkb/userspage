package ru.skilanov.controller;

import ru.skilanov.controller.validator.AdminValidator;
import ru.skilanov.dao.UserDaoImpl;
import ru.skilanov.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateUserServlet extends HttpServlet {
    private static final String INDEX = "/WEB-INF/view/create.jsp";

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(INDEX).forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AdminValidator adminValidator = new AdminValidator();
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User.ROLE role = User.getRole(req.getParameter("role"));
        String email = req.getParameter("email");
        UserDaoImpl userDao = new UserDaoImpl();

        User user = new User(name, login, password, role, email);

        if (adminValidator.requestValidator(req)) {
            req.getRequestDispatcher(INDEX).forward(req, resp);
        } else {
            userDao.insert(user);
            resp.sendRedirect(req.getContextPath() + "/list");
        }
    }
//
//    private boolean requestValidator(HttpServletRequest req) {
//        String name = req.getParameter("name");
//        String login = req.getParameter("login");
//        String password = req.getParameter("password");
//        String email = req.getParameter("email");
//        return name != null && name.length() > 0 &&
//                login != null && login.length() > 0 &&
//                password !=null && password.length() > 0 &&
//                email != null && email.length() > 0;
//    }

}
