package ru.job4j.crud;

import org.junit.Test;
import ru.job4j.crud.models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author Dmitry Belokursky
 * @since 06.05.18.
 */
public class UserListTest {

    private static final UserStore USER_STORE = UserStore.INSTANCE;

    @Test
    public void checkUserListViewRedirect() throws IOException {
        UserList ul = new UserList();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        when(session.getAttribute("role")).thenReturn("USER");
        ul.doGet(request, response);
        verify(request).getRequestDispatcher("/WEB-INF/views/user/ListUserView.jsp");
    }

    @Test
    public void checkAdminListViewRedirect() throws IOException {
        UserList ul = new UserList();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        when(session.getAttribute("role")).thenReturn("ADMIN");
        ul.doGet(request, response);
        verify(request).getRequestDispatcher("/WEB-INF/views/admin/ListAdminView.jsp");
    }

    @Test
    public void removeUserTest() {
        User user = new User("test", "test", "test@test.com", "test", "test", "test");
        USER_STORE.addUser(user);
        user = USER_STORE.isExists(user.getLogin(), user.getPassword());
        USER_STORE.removeUser(user.getId());
        User result = USER_STORE.isExists(user.getLogin(), user.getPassword());
        User expected = null;
        assertThat(result, is(expected));
    }
}