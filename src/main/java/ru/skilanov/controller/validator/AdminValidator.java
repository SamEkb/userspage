package ru.skilanov.controller.validator;

import javax.servlet.http.HttpServletRequest;

/**
 * Валидация полей ввода.
 */
public class AdminValidator {

    /**
     * Метод валидации полей ввода.
     *
     * @param req HttpServletRequest
     * @return boolean
     */
    public boolean requestValidator(HttpServletRequest req) {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        return name == null || name.length() <= 0 ||
                login == null || login.length() <= 0 ||
                password == null || password.length() <= 0 ||
                email == null || email.length() <= 0;
    }
}
