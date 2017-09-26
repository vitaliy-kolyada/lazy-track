package lazy_track.dao;

import lazy_track.model.Company;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompanyDaoImpl implements CompanyDao {
    public static Logger LOGGER = LoggerFactory.getLogger(CompanyDaoImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addCompany(Company company) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(company);
        LOGGER.info("Company added:" + company);
    }

    @Override
    public void updateCompany(Company company) {
        Session session = sessionFactory.getCurrentSession();
        session.update(company);
        LOGGER.info("Company updated:" + company);
    }

    @Override
    public void removeCompany(int id) {
        Session session = sessionFactory.getCurrentSession();
        Company company = (Company) session.load(Company.class, id);

        if (company != null) {
            session.delete(company);
        }
        LOGGER.info("Company deleted: " + company);
    }

    @Override
    public Company getCompanyById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Company company = (Company) session.load(Company.class, id);
        LOGGER.info("Company loaded:" + company);
        return company;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Company> listCompanies() {
        Session session = sessionFactory.getCurrentSession();
        List<Company> companies = session.createQuery("from lazy_track.model.Company").list();
        for (Company c : companies) {
            LOGGER.info("Company list:" + c);
        }
        return companies;
    }
}
