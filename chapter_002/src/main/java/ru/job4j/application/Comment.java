package ru.job4j.application;

import java.util.Date;

/**
 * Created by db on 01.07.17.
 */
public class Comment {

    private String name;

    private String content;

    private Date date;

    public Comment(String name, String content) {
        this.name = name;
        this.content = content;
        this.date = new Date();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }
}
