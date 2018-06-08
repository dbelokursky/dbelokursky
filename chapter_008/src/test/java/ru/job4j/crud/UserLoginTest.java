package ru.job4j.crud;

/**
 * @author Dmitry Belokursky
 * @since 06.05.18.
 */
public class UserLoginTest {

//    @Test
//    public void checkValidLoginRedirect() throws IOException {
//        UserStore userStore = UserStore.INSTANCE;
//        UserLogin userLogin = new UserLogin();
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        HttpServletResponse response = mock(HttpServletResponse.class);
//        HttpSession session = mock(HttpSession.class);
//        User user = new User();
//        user.setName("login_redirect");
//        user.setLogin("1");
//        user.setPassword("1");
//        user.setEmail("login@");
//        user.setRole(new Role("USER"));
//        user.setCountry("login");
//        user.setCity("login");
//        userStore.addUser(user);
//        int id = userStore.isExists(user.getLogin(), user.getPassword()).getId();
//        when(request.getParameter("login")).thenReturn(user.getName());
//        when(request.getParameter("password")).thenReturn(user.getPassword());
//        when(request.getSession()).thenReturn(session);
//        when(request.getContextPath()).thenReturn("http://localhost:8080/it");
//        userLogin.doPost(request, response);
//        verify(response).sendRedirect("http://localhost:8080/it/login");
//        userStore.removeUser(id);
//    }
//
//    @Test
//    public void checkInvalidLoginRedirect() throws IOException {
//        UserLogin userLogin = new UserLogin();
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        HttpServletResponse response = mock(HttpServletResponse.class);
//        HttpSession session = mock(HttpSession.class);
//        when(request.getParameter("login")).thenReturn("invalidLogin");
//        when(request.getParameter("password")).thenReturn("invalidPassword");
//        when(request.getSession()).thenReturn(session);
//        when(request.getContextPath()).thenReturn("http://localhost:8080/it");
//        userLogin.doPost(request, response);
//        verify(response).sendRedirect("http://localhost:8080/it/login");
//    }
}