package ru.job4j.parser;

import java.util.Objects;

/**
 * @author Dmitry Belokursky
 * @since 11.03.18.
 */
public class JobOffer {

    private String url;

    private String name;

    public JobOffer(String name, String url) {
        this.name = name;
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        JobOffer jobOffer = (JobOffer) o;
        return Objects.equals(name, jobOffer.name) && Objects.equals(url, jobOffer.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, url);
    }
}
