package ru.job4j.storage;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.job4j.storage.dao.UserDao;
import ru.job4j.storage.models.User;

import java.util.Scanner;

public class ImportUser {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name: ");
        String name = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-conf.xml");
        UserDao userDao = context.getBean("userDAO", UserDao.class);
        userDao.add(user);
    }
}
