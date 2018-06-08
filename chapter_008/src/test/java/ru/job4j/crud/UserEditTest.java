package ru.job4j.crud;

/**
 * @author Dmitry Belokursky
 * @since 06.05.18.
 */
public class UserEditTest {

//    @Test
//    public void checkEditUserViewRedirect() throws IOException {
//        UserStore store = UserStore.INSTANCE;
//        UserEdit userEdit = new UserEdit();
//        User user = new User();
//        user.setName("editTestAdmin");
//        user.setLogin("editTestAdmin");
//        user.setPassword("editTestAdmin");
//        user.setEmail("editTestAdmin@");
//        user.setRole(new Role("USER"));
//        user.setCountry("editTestAdmin");
//        user.setCity("editTestAdmin");
//        store.addUser(user);
//        int id = store.isExists(user.getLogin(), user.getPassword()).getId();
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        HttpServletResponse response = mock(HttpServletResponse.class);
//        HttpSession session = mock(HttpSession.class);
//        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
//        when(session.getAttribute("role")).thenReturn("USER");
//        when(session.getAttribute("id")).thenReturn(id);
//        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
//        when(request.getSession()).thenReturn(session);
//        userEdit.doPost(request, response);
//        verify(request).getRequestDispatcher("/WEB-INF/views/user/EditUserView.jsp");
//        store.removeUser(id);
//    }
//
//    @Test
//    public void checkEditAdminViewRedirect() throws IOException {
//        UserStore userStore = UserStore.INSTANCE;
//        UserEdit userEdit = new UserEdit();
//        User user = new User();
//        user.setName("editTestAdmin");
//        user.setLogin("editTestAdmin");
//        user.setPassword("editTestAdmin");
//        user.setEmail("editTestAdmin@");
//        user.setRole(new Role("ADMIN"));
//        user.setCountry("editTestAdmin");
//        user.setCity("editTestAdmin");
//        userStore.addUser(user);
//        int id = userStore.isExists(user.getLogin(), user.getPassword()).getId();
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        HttpServletResponse response = mock(HttpServletResponse.class);
//        HttpSession session = mock(HttpSession.class);
//        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
//        when(session.getAttribute("role")).thenReturn("ADMIN");
//        when(request.getParameter("userId")).thenReturn(Integer.toString(id));
//        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
//        when(request.getSession()).thenReturn(session);
//        userEdit.doPost(request, response);
//        verify(request).getRequestDispatcher("/WEB-INF/views/admin/Edit.jsp");
//        userStore.removeUser(id);
//    }
}