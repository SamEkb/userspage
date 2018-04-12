package ru.skilanov.controller;


import ru.skilanov.dao.UserDaoImpl;
import ru.skilanov.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AllUsersServlet extends HttpServlet {
    private static final String LIST_OF_USERS = "/WEB-INF/view//list.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html");
        UserDaoImpl userDao = new UserDaoImpl();
        List<User> users = userDao.getAll();
        req.getServletContext().setAttribute("users", users);
        req.getRequestDispatcher(LIST_OF_USERS).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
