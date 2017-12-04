package app.controller;

import app.model.Company;

import java.util.ArrayList;

public class CompanyApiController {
    private static ArrayList<Company> companies = new ArrayList<>();

    public boolean create(Company company) {
        for (Company company1 : companies) {
            if (company.getName().equals(company1.getName())) {
                return false;
            }
        }
        companies.add(company);
        return true;
    }
}
