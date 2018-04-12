package ru.skilanov.controller;

import ru.skilanov.dao.UserDaoImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        UserDaoImpl userDao = new UserDaoImpl();
        userDao.deleteById(id);
        resp.sendRedirect(req.getContextPath() + "/list");
    }
}
