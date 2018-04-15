package ru.skilanov.controller;

import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class UpdateUserServletTest {

    /**
     * Страница изменения.
     */
    private static final String UPDATE = "/WEB-INF/view/adminUpdate.jsp";

    /**
     * HttpServletRequest.
     */
    private HttpServletRequest request;

    /**
     * HttpServletRequest.
     */
    private HttpServletResponse response;

    /**
     * Объект сервлета.
     */
    private UpdateUserServlet updateUserServlet;

    /**
     * Инициализация перед тестом.
     */
    @Before
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        updateUserServlet = new UpdateUserServlet();
    }

    /**
     * Тест doGet.
     *
     * @throws ServletException exception
     * @throws IOException      exception
     */
    @Test
    public void whenDoGetThenReturnRightPage() throws ServletException, IOException {
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

        when(request.getParameter("id")).thenReturn("55");
        when(request.getRequestDispatcher(UPDATE)).thenReturn(requestDispatcher);

        updateUserServlet.doGet(request, response);

        verify(request, times(1)).getRequestDispatcher(UPDATE);
        verify(request, never()).getSession();
        verify(requestDispatcher).forward(request, response);
    }
}
