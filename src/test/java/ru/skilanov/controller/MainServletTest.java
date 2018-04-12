package ru.skilanov.controller;

import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

/**
 * Тысты сервлета главной страницы.
 */
public class MainServletTest {
    /**
     * Главная страница.
     */
    private static final String INDEX = "/WEB-INF/view/index.jsp";

    /**
     * Тест doGet.
     * @throws ServletException exception
     * @throws IOException exception
     */
    @Test
    public void whenDoGetIndexThenGetRightResult() throws ServletException, IOException {
        MainServlet mainServlet = new MainServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher(INDEX)).thenReturn(requestDispatcher);

        mainServlet.doGet(request, response);

        verify(request, times(1)).getRequestDispatcher(INDEX);
        verify(request, never()).getSession();
        verify(requestDispatcher).forward(request, response);
    }
}
