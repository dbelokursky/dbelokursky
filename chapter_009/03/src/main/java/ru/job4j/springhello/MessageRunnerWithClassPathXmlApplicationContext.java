package ru.job4j.springhello;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MessageRunnerWithClassPathXmlApplicationContext {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("message-bean.xml");
        Message message = (Message) context.getBean("message");
        System.out.println(message.getMessage());
    }
}
