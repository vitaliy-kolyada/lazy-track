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
    public void addCompany(Company company) {
        companyDao.addCompany(company);
    }

    @Override
    @Transactional
    public void updateCompany(Company company) {
        companyDao.updateCompany(company);
    }

    @Override
    @Transactional
    public void removeCompany(int id) {
        companyDao.removeCompany(id);
    }

    @Override
    @Transactional
    public Company getCompanyById(int id) {
        return companyDao.getCompanyById(id);
    }

    @Override
    @Transactional
    public List<Company> listCompanies() {
        return companyDao.listCompanies();
    }
}
