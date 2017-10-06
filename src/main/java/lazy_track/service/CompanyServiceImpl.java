package lazy_track.service;

import lazy_track.dao.CompanyDao;
import lazy_track.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("companyService")
public class CompanyServiceImpl implements CompanyService {

    private CompanyDao companyDao;

    @Autowired
    public void setCompanyDao(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    public List<Company> findAll() {
        return companyDao.findAll();
    }

    public Company findById(long id) {
        return companyDao.findById(id);
    }

    public void save(Company company) {
        companyDao.save(company);
    }

    public void update(Company company) {
        companyDao.update(company);
    }

    public void deleteById(long id) {
        companyDao.deleteById(id);
    }

    public boolean isExist(Company company) {
        return companyDao.findByName(company.getName()) != null;
    }
}
