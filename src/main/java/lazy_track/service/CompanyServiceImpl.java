package lazy_track.service;

import lazy_track.dao.CompanyDao;
import lazy_track.model.Company;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    private CompanyDao companyDao;

    public void setCompanyDao(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    @Override
    @Transactional
    public void add(Company company) {
        companyDao.add(company);
    }

    @Override
    @Transactional
    public void update(Company company) {
        companyDao.update(company);
    }

    @Override
    @Transactional
    public void remove(int id) {

    }

    @Override
    @Transactional
    public Company get(int id) {
        return companyDao.get(id);
    }

    @Override
    @Transactional
    public List<Company> list() {
        return companyDao.list();
    }
}
