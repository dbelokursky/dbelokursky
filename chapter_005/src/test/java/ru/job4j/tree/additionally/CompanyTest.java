package ru.job4j.tree.additionally;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author Dmitry Belokursky
 * @since 21.11.17.
 */
public class CompanyTest {


    @Test
    public void getTopLevelParentTest() throws Exception {
        Company company = new Company(null, 100);
        Company company1 = new Company(company, 200);
        Company company2 = new Company(company1, 300);
        System.out.println(company2.getTopLevelParent(company2));
    }

    @Test
    public void getEmployeeCountForCompanyAndChildren() throws Exception {
        Company parent = new Company(null, 100);
        Company company1 = new Company(parent, 200);
        Company company2 = new Company(parent, 300);
        Company company3 = new Company(parent, 300);
        LinkedList<Company> companies = new LinkedList<>(Arrays.asList(company1, company2, company3));
        System.out.println(parent.getEmployeeCountForCompanyAndChildren(parent, companies));
    }

}