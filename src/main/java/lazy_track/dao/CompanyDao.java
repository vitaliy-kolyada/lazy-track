package lazy_track.dao;

import lazy_track.model.Company;

import java.util.List;

public interface CompanyDao {
    void addCompany(Company company);

    void updateCompany(Company company);

    void removeCompany(int id);

    Company getCompanyById(int id);

    List<Company> listCompanies();
}
