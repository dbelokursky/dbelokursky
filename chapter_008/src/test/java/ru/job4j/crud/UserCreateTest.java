package ru.job4j.crud;

import org.junit.Test;
import ru.job4j.crud.models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * @author Dmitry Belokursky
 * @since 06.05.18.
 */
public class UserCreateTest {

    @Test
    public void checkAdminCreateViewRedirect() throws IOException {
        UserCreate userCreate = new UserCreate();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        userCreate.doGet(request, response);
        verify(request).getRequestDispatcher("/WEB-INF/views/admin/CreateAdminView.jsp");
    }

    @Test
    public void createUserTest() {
        UserStore userStore = UserStore.INSTANCE;
        User user = new User("createTest", "createTest", "createTest@mail.com", "createTest", "createTest", "createTest");
        userStore.addUser(user);
        User result = userStore.isExists(user.getLogin(), user.getPassword());
        assertTrue(result != null);
        userStore.removeUser(result.getId());
    }
}