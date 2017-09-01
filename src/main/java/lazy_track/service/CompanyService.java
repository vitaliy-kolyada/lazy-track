package lazy_track.service;

import lazy_track.model.Company;

import java.util.List;

public interface CompanyService {
    void add(Company company);

    void update(Company company);

    void remove(int id);

    Company get(int id);

    public List<Company> list();
}
