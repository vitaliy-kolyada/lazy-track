package lazy_track.dao;

import lazy_track.model.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("userDao")
@Transactional

public class UserDaoImpl extends AbstractDao<Long, User> implements UserDao {

    @Override
    public void update(User user) {
        getSession().update(user);
    }

    @Override
    public User findById(long id) {
        return getByKey(id);
    }

    @Override
    public void save(User user) {
        persist(user);
    }

    @Override
    public void deleteById(long id) {
        delete(findById(id));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        Criteria criteria = createEntityCriteria();
        return (List<User>) criteria.list();
    }

    @Override
    public User findByName(String name) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("name", name));
        return (User) criteria.uniqueResult();
    }
}
