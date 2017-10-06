package lazy_track.dao;

import lazy_track.model.Company;

import java.util.List;

public interface CompanyDao {
    void update(Company company);

    Company findById(long id);

    void save(Company company);

    void deleteById(long id);

    List<Company> findAll();

    Company findByName(String name);
}
