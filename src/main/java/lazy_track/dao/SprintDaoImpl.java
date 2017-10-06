package lazy_track.dao;

import lazy_track.model.Sprint;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("sprintDao")
@Transactional

public class SprintDaoImpl extends AbstractDao<Long, Sprint> implements SprintDao {
    @Override
    public void update(Sprint sprint) {
        getSession().update(sprint);
    }

    @Override
    public Sprint findById(long id) {
        return getByKey(id);
    }

    @Override
    public void save(Sprint sprint) {
        persist(sprint);
    }

    @Override
    public void deleteById(long id) {
        delete(findById(id));
    }

    @Transactional
    @Override
    @SuppressWarnings("unchecked")
    public List<Sprint> findAll() {
        Criteria criteria = createEntityCriteria();
        return (List<Sprint>) criteria.list();
    }

    @Override
    public Sprint findByName(String name) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("name", name));
        return (Sprint) criteria.uniqueResult();
    }
}
