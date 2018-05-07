package ru.skilanov.controller;

import org.junit.Test;
import ru.skilanov.dao.UserDaoImpl;
import ru.skilanov.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class AllUsersServletTest {

    /**
     * Страница вписка пользователей.
     */
    private static final String LIST_OF_USERS = "/WEB-INF/view//usersList.jsp";

    /**
     * Тест метода doGet.
     *
     * @throws IOException      exception
     * @throws ServletException exception
     */
    @Test
    public void whenDoGetThenReturnListPage() throws IOException, ServletException {
        AllUsersServlet allUsersServlet = new AllUsersServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        ServletContext context = mock(ServletContext.class);
        UserDaoImpl mockDao = mock(UserDaoImpl.class);

        List<User> users = new ArrayList<>();

        when(mockDao.getAll()).thenReturn(users);
        when(request.getServletContext()).thenReturn(context);
        when(request.getRequestDispatcher(LIST_OF_USERS)).thenReturn(dispatcher);

        allUsersServlet.setUserDao(mockDao);

        allUsersServlet.doGet(request, response);

        verify(request, times(1)).getRequestDispatcher(LIST_OF_USERS);
        verify(request, never()).getSession();
        verify(dispatcher).forward(request, response);
    }
}
