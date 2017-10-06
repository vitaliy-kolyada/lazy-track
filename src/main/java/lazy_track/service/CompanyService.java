package lazy_track.service;

import lazy_track.model.Company;

import java.util.List;

public interface CompanyService {
    Company findById(long id);

    void save(Company company);

    void update(Company company);

    void deleteById(long id);

    List<Company> findAll();

    boolean isExist(Company company);
}
