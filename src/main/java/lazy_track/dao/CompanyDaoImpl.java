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
    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDaoImpl.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Company company) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(company);
        LOGGER.info("Company successfully added. Company details: " + company);
    }

    @Override
    public void update(Company company) {
        Session session = sessionFactory.getCurrentSession();
        session.update(company);
        LOGGER.info("Company successfully updated. Company details: " + company);
    }

    @Override
    public void remove(int id) {
        Session session = sessionFactory.getCurrentSession();
        Company company = (Company) session.load(Company.class, id);
        if (company != null) {
            session.delete(company);
        }
        LOGGER.info("Company successfully removed. Company details: " + company);

    }

    @Override
    public Company get(int id) {
        Session session = sessionFactory.getCurrentSession();
        Company company = (Company) session.load(Company.class, id);
        LOGGER.info("Company successfully loaded. Company details: " + company);
        return company;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Company> list() {
        Session session = sessionFactory.getCurrentSession();
        List<Company> companies = session.createQuery("from lazy_track.model.Company").list();
        for (Company company : companies) {
            LOGGER.info("Company list: " + company);
        }
        return companies;
    }
}
