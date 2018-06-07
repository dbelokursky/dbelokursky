package ru.job4j.musicvenue.models;

/**
 * @author Dmitry Belokursky
 * @since 13.05.18.
 */
public class Address extends BaseEntity {

    private String country;

    private String city;

    private String street;

    private String unit;

    private int zip;

    public Address() {
    }

    public Address(String country, String city, String street, String unit, int zip) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.unit = unit;
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Address address = (Address) o;

        if (getZip() != address.getZip()) {
            return false;
        }
        if (!getCountry().equals(address.getCountry())) {
            return false;
        }
        if (!getCity().equals(address.getCity())) {
            return false;
        }
        if (!getStreet().equals(address.getStreet())) {
            return false;
        }
        return getUnit().equals(address.getUnit());
    }

    @Override
    public int hashCode() {
        int result = getCountry().hashCode();
        result = 31 * result + getCity().hashCode();
        result = 31 * result + getStreet().hashCode();
        result = 31 * result + getUnit().hashCode();
        result = 31 * result + getZip();
        return result;
    }
}
