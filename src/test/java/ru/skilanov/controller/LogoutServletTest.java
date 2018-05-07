package ru.skilanov.controller;

import org.junit.Test;
import ru.skilanov.controller.LogoutServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * тесты для логаут сервлета.
 */
public class LogoutServletTest {

    /**
     * doGet тест.
     *
     * @throws IOException exception
     */
    @Test
    public void whenDoGetThenLogOut() throws IOException {
        LogoutServlet logoutServlet = new LogoutServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = request.getSession();
        HttpSession httpSession = mock(HttpSession.class);

        when(session).thenReturn(httpSession);

        logoutServlet.doGet(request, response);
    }
}
