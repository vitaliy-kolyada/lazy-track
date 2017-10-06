package lazy_track.dao;

import lazy_track.model.Company;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("companyDao")
@Transactional
public class CompanyDaoImpl extends AbstractDao<Long, Company> implements CompanyDao {
    @Override
    public void update(Company company) {
        getSession().update(company);
    }

    @Override
    public Company findById(long id) {
        return getByKey(id);
    }

    @Override
    public void save(Company company) {
        persist(company);
    }

    @Override
    public void deleteById(long id) {
        delete(findById(id));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Company> findAll() {
        Criteria criteria = createEntityCriteria();
        return (List<Company>) criteria.list();
    }

    @Override
    public Company findByName(String name) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("name", name));
        return (Company) criteria.uniqueResult();
    }
}
