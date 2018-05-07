package ru.skilanov.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Главный сервлет.
 */
public class MainServlet extends HttpServlet {
    /**
     * Страница индекс jsp.
     */
    private static final String INDEX = "/WEB-INF/view/index.jsp";

    /**
     * Метод гет открывающий главную страницу.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException ServletException
     * @throws IOException      IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(INDEX).forward(req, resp);
    }
}

