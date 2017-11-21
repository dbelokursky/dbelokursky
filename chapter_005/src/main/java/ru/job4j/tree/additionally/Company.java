package ru.job4j.tree.additionally;

import java.util.LinkedList;

/**
 * @author Dmitry Belokursky
 * @since 21.11.17.
 */
public class Company implements ICompanyService {

    public final Company parent;

    public final long employeeCount;

    Company(Company parent, long employeeCount) {
        this.parent = parent;
        this.employeeCount = employeeCount;
    }

    @Override
    public long getEmployeeCountForCompanyAndChildren(Company company, LinkedList<Company> companies) {
        long cntr = 0;
        for (Company com : companies) {
            long tmpCntr = 0;
            while (com != null) {
                tmpCntr += com.employeeCount;
                if (com.equals(company)) {
                    cntr += tmpCntr - company.employeeCount;
                    break;
                }
                com = com.parent;
            }
        }
        return cntr += company.employeeCount;
    }

    @Override
    public Company getTopLevelParent(Company child) {
        while (child.parent != null) {
            child = child.parent;
        }
        return child;
    }

    @Override
    public String toString() {
        return "Company{" +
                "parent=" + parent +
                ", employeeCount=" + employeeCount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        if (employeeCount != company.employeeCount) return false;
        return parent != null ? parent.equals(company.parent) : company.parent == null;
    }

    @Override
    public int hashCode() {
        int result = parent != null ? parent.hashCode() : 0;
        result = 31 * result + (int) (employeeCount ^ (employeeCount >>> 32));
        return result;
    }
}
