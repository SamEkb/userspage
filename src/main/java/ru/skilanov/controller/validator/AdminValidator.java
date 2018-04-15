package ru.skilanov.controller.validator;

import javax.servlet.http.HttpServletRequest;

public class AdminValidator {

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
