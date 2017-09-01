package lazy_track.dao;

import lazy_track.model.UserStory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserStoryDaoImpl implements UserStoryDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserStoryDaoImpl.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(UserStory userStory) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(userStory);
        LOGGER.info("User story successfully added. User story details: " + userStory);
    }

    @Override
    public void update(UserStory userStory) {
        Session session = sessionFactory.getCurrentSession();
        session.update(userStory);
        LOGGER.info("User story successfully updated. User story details: " + userStory);
    }

    @Override
    public void remove(int id) {
        Session session = sessionFactory.getCurrentSession();
        UserStory userStory = (UserStory) session.load(UserStory.class, id);
        if (userStory != null) {
            session.delete(userStory);
        }
        LOGGER.info("User story successfully removed. User story details: " + userStory);
    }

    @Override
    public UserStory get(int id) {
        Session session = sessionFactory.getCurrentSession();
        UserStory userStory = (UserStory) session.load(UserStory.class, id);
        LOGGER.info("User story successfully loaded. User story details: " + userStory);
        return userStory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<UserStory> list() {
        Session session = sessionFactory.getCurrentSession();
        List<UserStory> usersStories = session.createQuery("from lazy_track.model.UserStory").list();
        for (UserStory userStory : usersStories) {
            LOGGER.info("Users list: " + userStory);
        }
        return usersStories;
    }
}
