package ru.job4j.crud;

/**
 * @author Dmitry Belokursky
 * @since 06.05.18.
 */
public class UserCreateTest {

//    @Test
//    public void checkAdminCreateViewRedirect() throws IOException {
//        UserCreate userCreate = new UserCreate();
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        HttpServletResponse response = mock(HttpServletResponse.class);
//        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
//        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
//        userCreate.doGet(request, response);
//        verify(request).getRequestDispatcher("/WEB-INF/views/admin/CreateAdminView.jsp");
//    }
//
//    @Test
//    public void createUserTest() {
//        UserStore userStore = UserStore.INSTANCE;
//        User user = new User();
//        user.setName("createTest");
//        user.setLogin("createTest");
//        user.setPassword("createTest");
//        user.setEmail("createTest@");
//        user.setRole(new Role("ADMIN"));
//        user.setCountry("createTest");
//        user.setCity("createTest");
//        userStore.addUser(user);
//        User result = userStore.isExists(user.getLogin(), user.getPassword());
//        assertTrue(result != null);
//        userStore.removeUser(result.getId());
//    }
}