package lazy_track.service;

import lazy_track.model.Company;

import java.util.List;

public interface CompanyService {
    void addCompany(Company company);

    void updateCompany(Company company);

    void removeCompany(int id);

    Company getCompanyById(int id);

    List<Company> listCompanies();

}
