package lazy_track.dao;

import lazy_track.model.Sprint;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SprintDaoImpl implements SprintDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(SprintDaoImpl.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void add(Sprint sprint) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(sprint);
        LOGGER.info("Sprint successfully added. Sprint details: " + sprint);
    }

    @Override
    public void update(Sprint sprint) {
        Session session = sessionFactory.getCurrentSession();
        session.update(sprint);
        LOGGER.info("Sprint successfully updated. Sprint details: " + sprint);
    }

    @Override
    public void remove(int id) {
        Session session = sessionFactory.getCurrentSession();
        Sprint sprint = (Sprint) session.load(Sprint.class, id);
        if (sprint != null) {
            session.delete(sprint);
        }
        LOGGER.info("Sprint successfully removed. Sprint details: " + sprint);
    }

    @Override
    public Sprint get(int id) {
        Session session = sessionFactory.getCurrentSession();
        Sprint sprint = (Sprint) session.load(Sprint.class, id);
        LOGGER.info("Sprint successfully loaded. Sprint details: " + sprint);
        return sprint;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Sprint> list() {
        Session session = sessionFactory.getCurrentSession();
        List<Sprint> sprints = session.createQuery("from lazy_track.model.Sprint").list();
        for (Sprint sprint : sprints) {
            LOGGER.info("Sprint list: " + sprint);
        }
        return sprints;
    }
}
