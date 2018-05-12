package ru.job4j.crud;

import org.junit.Test;
import ru.job4j.crud.models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

/**
 * @author Dmitry Belokursky
 * @since 06.05.18.
 */
public class UserLoginTest {

    @Test
    public void checkValidLoginRedirect() throws IOException {
        UserStore userStore = UserStore.INSTANCE;
        UserLogin userLogin = new UserLogin();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        User user = new User("loginTest", "loginTest", "loginTest", "loginTest", "loginTest", "loginTest");
        userStore.addUser(user);
        int id = userStore.isExists(user.getLogin(), user.getPassword()).getId();
        when(request.getParameter("login")).thenReturn(user.getName());
        when(request.getParameter("password")).thenReturn(user.getPassword());
        when(request.getSession()).thenReturn(session);
        when(request.getContextPath()).thenReturn("http://localhost:8080/it");
        userLogin.doPost(request, response);
        verify(response).sendRedirect("http://localhost:8080/it/list");
        userStore.removeUser(id);
    }

    @Test
    public void checkInvalidLoginRedirect() throws IOException {
        UserLogin userLogin = new UserLogin();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getParameter("login")).thenReturn("invalidLogin");
        when(request.getParameter("password")).thenReturn("invalidPassword");
        when(request.getSession()).thenReturn(session);
        when(request.getContextPath()).thenReturn("http://localhost:8080/it");
        userLogin.doPost(request, response);
        verify(response).sendRedirect("http://localhost:8080/it/login");
    }
}