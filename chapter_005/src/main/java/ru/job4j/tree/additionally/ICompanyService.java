package ru.job4j.tree.additionally;

import java.util.LinkedList;

/**
 * @author Dmitry Belokursky
 * @since 21.11.17.
 */
public interface ICompanyService {

    /**
     * @param child - company for whom we are searching the top level parent (parent of parent of...)
     * @return top level parent
     */
    Company getTopLevelParent(Company child);

    /**
     * @param company   - company for whom we are searching count of employees (count of this company employees +
     *                  count of employees for all children companies and their children, etc.)
     * @param companies - all available companies
     * @return count of employees
     */
    long getEmployeeCountForCompanyAndChildren(Company company, LinkedList<Company> companies);
}
