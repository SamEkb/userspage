package ru.skilanov.controller;

import ru.skilanov.controller.validator.AdminValidator;
import ru.skilanov.dao.UserDaoImpl;
import ru.skilanov.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateUserServlet extends HttpServlet {
    private static final String UPDATE = "/WEB-INF/view/adminUpdate.jsp";
    private UserDaoImpl userDao;

    @Override
    public void init() throws ServletException {
        userDao = (UserDaoImpl) getServletContext().getAttribute("userDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        User user = userDao.findById(id);

        req.setAttribute("user", user);

        req.getRequestDispatcher(UPDATE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        AdminValidator adminValidator = new AdminValidator();
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User.ROLE role = User.getRole(req.getParameter("role"));
        String email = req.getParameter("email");

        if (adminValidator.requestValidator(req)) {
            resp.sendRedirect(req.getContextPath() + "/list");
        } else {
            User user = userDao.findById(id);
            user.setName(name);
            user.setLogin(login);
            user.setPassword(password);
            user.setRole(role);
            user.setEmail(email);

            userDao.update(user);
            resp.sendRedirect(req.getContextPath() + "/list");
        }
    }
}
