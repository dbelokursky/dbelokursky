package ru.job4j.optimization;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitry Belokursky
 * @since 07.03.18.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Entries implements Serializable {

    @XmlElement(name = "entry")
    private List<Entry> entries = new ArrayList<>();

    public Entries() {
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }
}
