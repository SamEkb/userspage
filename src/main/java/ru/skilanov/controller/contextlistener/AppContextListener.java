package ru.skilanov.controller.contextlistener;

import ru.skilanov.dao.UserDaoImpl;
import ru.skilanov.database.ConnectionFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppContextListener implements ServletContextListener {
    private ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
    private UserDaoImpl userDao;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        userDao = new UserDaoImpl(connectionFactory);
        ServletContext context = sce.getServletContext();
        context.setAttribute("userDao", userDao);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
