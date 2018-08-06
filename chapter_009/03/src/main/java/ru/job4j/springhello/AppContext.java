package ru.job4j.springhello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {

    @Bean
    public Message message() {
        Message message = new Message();
        message.setMessage("Hello from AppContext");
        return message;
    }
}
