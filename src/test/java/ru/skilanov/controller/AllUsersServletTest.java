package ru.skilanov.controller;

import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

/**
 * Тесты сервлета вписка всех пользователей.
 */
public class AllUsersServletTest {

    /**
     * Страница вписка пользователей.
     */
    private static final String PAGE = "/WEB-INF/view//list.jsp";

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

        when(request.getServletContext()).thenReturn(context);
        when(request.getRequestDispatcher(PAGE)).thenReturn(dispatcher);

        allUsersServlet.doGet(request, response);

        verify(request, times(1)).getRequestDispatcher(PAGE);
        verify(request, never()).getSession();
        verify(dispatcher).forward(request, response);
    }
}
