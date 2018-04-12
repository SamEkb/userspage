package ru.skilanov.controller;

import org.junit.Before;
import org.junit.Test;
import ru.skilanov.dao.UserDaoImpl;
import ru.skilanov.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * Тест сервлета создания пользователей.
 */
public class CreateUserServletTest {
    /**
     * Страница создания пользователей.
     */
    private static final String CREATE = "/WEB-INF/view/create.jsp";

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
    private CreateUserServlet createUserServlet;

    /**
     * Инициализация перед тестом.
     *
     * @throws Exception Exception
     */
    @Before
    public void setUp() throws Exception {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        createUserServlet = new CreateUserServlet();
    }

    /**
     * Тест метода doPost.
     *
     * @throws ServletException exception
     * @throws IOException      exception
     */
    @Test
    public void whenDoPostThenGetAddedUser() throws ServletException, IOException {
        UserDaoImpl userDao = mock(UserDaoImpl.class);

        when(request.getParameter("name")).thenReturn("Sam");
        when(request.getParameter("login")).thenReturn("User");
        when(request.getParameter("password")).thenReturn("root");
        when(request.getParameter("role")).thenReturn(String.valueOf(User.ROLE.ADMIN));
        when(request.getParameter("email")).thenReturn("email");

        createUserServlet.doPost(request, response);

        List<User> list = userDao.getAll();
        User user = new User("Sam", "User", "root", User.ROLE.USER, "email");
        list.add(user);

        assertThat(list.get(0).getName(), is("Sam"));
        assertThat(list.get(0).getLogin(), is("User"));
        assertThat(list.get(0).getPassword(), is("root"));
        assertThat(list.get(0).getRole(), is(User.ROLE.USER));
        assertThat(list.get(0).getEmail(), is("email"));
    }

    /**
     * Тест метода doGet.
     *
     * @throws ServletException exception
     * @throws IOException      exception
     */
    @Test
    public void whenDoGetThenReturnCreatePage() throws ServletException, IOException {
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher(CREATE)).thenReturn(requestDispatcher);

        createUserServlet.doGet(request, response);

        verify(request, times(1)).getRequestDispatcher(CREATE);
        verify(request, never()).getSession();
        verify(requestDispatcher).forward(request, response);
    }
}
