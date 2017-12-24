package ru.job4j.tree.additionally;

import java.util.LinkedList;

/**
 * @author Dmitry Belokursky
 * @since 21.11.17.
 */
public class Company implements ICompanyService {

    public final Company parent;

    public final long employeeCount;

    private long cntr = 0;

    Company(Company parent, long employeeCount) {
        this.parent = parent;
        this.employeeCount = employeeCount;
    }

    @Override
    public long getEmployeeCountForCompanyAndChildren(Company company, LinkedList<Company> companies) {
        long tmpCntr = 0;
        Company currentCompany = companies.poll();

        if (currentCompany != null) {
            tmpCntr += currentCompany.employeeCount;
            currentCompany = currentCompany.parent;
            if (currentCompany.equals(company)) {
                cntr += tmpCntr;
            }
        }
        if (companies.size() == 0) {
            cntr += company.employeeCount;
            return cntr;
        }
        getEmployeeCountForCompanyAndChildren(company, companies);
        return cntr;
    }

    @Override
    public Company getTopLevelParent(Company child) {
        if (child.parent == null) {
            return child;
        }
        child = child.parent;
        return getTopLevelParent(child);
    }

    @Override
    public String toString() {
        return "Company{"
                + "parent=" + parent
                + ", employeeCount="
                + employeeCount + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Company company = (Company) o;
        if (employeeCount != company.employeeCount) {
            return false;
        }

        return parent != null ? parent.equals(company.parent) : company.parent == null;
    }

    @Override
    public int hashCode() {
        int result = parent != null ? parent.hashCode() : 0;
        result = 31 * result + (int) (employeeCount ^ (employeeCount >>> 32));
        return result;
    }
}
