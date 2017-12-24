package ru.job4j.bank;

/**
 * @author Dmitry Belokursky
 * @since 12.09.17.
 */
public class Account {

    private double value;

    private String requisites;

    public Account() {

    }

    public Account(double value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getRequisites() {
        return requisites;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        if (Double.compare(account.getValue(), getValue()) != 0) {
            return false;
        }
        return getRequisites().equals(account.getRequisites());
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(getValue());
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + getRequisites().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Account{"
                + "value=" + value
                + ", requisites='" + requisites + '\'' + '}';
    }
}
