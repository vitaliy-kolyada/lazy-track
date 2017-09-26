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
    public static Logger LOGGER = LoggerFactory.getLogger(CompanyDaoImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addUserStory(UserStory userStory) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(userStory);
        LOGGER.info("User story added:" + userStory);
    }

    @Override
    public void updateUserStory(UserStory userStory) {
        Session session = sessionFactory.getCurrentSession();
        session.update(userStory);
        LOGGER.info("User story updated:" + userStory);
    }

    @Override
    public void removeUserStory(int id) {
        Session session = sessionFactory.getCurrentSession();
        UserStory userStory = (UserStory) session.load(UserStory.class, id);

        if (userStory != null) {
            session.delete(userStory);
        }
        LOGGER.info("User story deleted: " + userStory);
    }

    @Override
    public UserStory getUserStoryById(int id) {
        Session session = sessionFactory.getCurrentSession();
        UserStory userStory = (UserStory) session.load(UserStory.class, id);
        LOGGER.info("User story loaded:" + userStory);
        return userStory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<UserStory> listUserStories() {
        Session session = sessionFactory.getCurrentSession();
        List<UserStory> userStories = session.createQuery("from lazy_track.model.UserStory").list();
        for (UserStory us : userStories) {
            LOGGER.info("User story list:" + us);
        }
        return userStories;
    }
}
