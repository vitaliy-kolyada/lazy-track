package lazy_track.dao;

import lazy_track.model.UserStory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("userStoryDao")
@Transactional
public class UserStoryDaoImpl extends AbstractDao<Long, UserStory> implements UserStoryDao {

    @Override
    public void update(UserStory userStory) {
        getSession().update(userStory);
    }

    @Override
    public UserStory findById(long id) {
        return getByKey(id);
    }

    @Override
    public void save(UserStory userStory) {
        persist(userStory);
    }

    @Override
    public void deleteById(long id) {
        delete(findById(id));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<UserStory> findAll() {
        Criteria criteria = createEntityCriteria();
        return (List<UserStory>) criteria.list();
    }

    @Override
    public UserStory findByName(String name) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("name", name));
        return (UserStory) criteria.uniqueResult();
    }
}
