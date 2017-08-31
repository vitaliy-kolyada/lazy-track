package dao;

import model.Company;

import java.util.List;

public interface CompanyDao {
    void add(Company company);

    void update(Company company);

    void remove(int id);

    Company get(int id);

    public List<Company> list();
}
