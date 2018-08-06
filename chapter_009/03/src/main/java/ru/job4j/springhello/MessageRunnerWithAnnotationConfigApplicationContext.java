package ru.job4j.springhello;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MessageRunnerWithAnnotationConfigApplicationContext {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppContext.class);
        Message message = context.getBean(Message.class);
        System.out.println(message.getMessage());
    }
}
