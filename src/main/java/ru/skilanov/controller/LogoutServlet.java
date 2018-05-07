package ru.skilanov.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Сервлет выхода пользователя из сессии.
 */
public class LogoutServlet extends HttpServlet {

    /**
     * Главная страница.
     */
    private static final String INDEX_PAGE = "/";
    /**
     * Атрибут пользователь.
     */
    private static final String USER = "user";

    /**
     * Гет метод закрывающий сессию и перенаправляющий на главную страницу.
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @throws IOException IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();

        session.removeAttribute(USER);

        resp.sendRedirect(INDEX_PAGE);
    }
}