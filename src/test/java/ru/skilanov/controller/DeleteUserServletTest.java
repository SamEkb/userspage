package ru.skilanov.controller;

import org.junit.Test;
import ru.skilanov.dao.UserDaoImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Тесты сервлета удаления пользователя.
 */
public class DeleteUserServletTest {

    /**
     * Тест doPost.
     *
     * @throws IOException exception
     */
    @Test
    public void whenDoPostThenDelete() throws IOException {
        UserDaoImpl userDao = mock(UserDaoImpl.class);

        DeleteUserServlet deleteUserServlet = new DeleteUserServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("id")).thenReturn(String.valueOf(1));

        deleteUserServlet.setUserDao(userDao);

        deleteUserServlet.doPost(request, response);
    }
}
