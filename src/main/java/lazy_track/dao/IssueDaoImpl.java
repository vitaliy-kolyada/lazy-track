package lazy_track.dao;

import lazy_track.model.Issue;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("issueDao")
@Transactional

public class IssueDaoImpl extends AbstractDao<Long, Issue> implements IssueDao {
    @Override
    public void update(Issue issue) {
        getSession().update(issue);
    }

    @Override
    public Issue findById(long id) {
        return getByKey(id);
    }

    @Override
    public void save(Issue issue) {
        persist(issue);
    }

    @Override
    public void deleteById(long id) {
        delete(findById(id));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Issue> findAll() {
        Criteria criteria = createEntityCriteria();
        return (List<Issue>) criteria.list();
    }

    @Override
    public Issue findByName(String name) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("name", name));
        return (Issue) criteria.uniqueResult();
    }
}
