package ru.job4j.springhello;

public class Message {

    private String message;

    public String getMessage() {
        return String.format("Message: %s", message);
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
