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
    public static Logger LOGGER = LoggerFactory.getLogger(CompanyDaoImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addSprint(Sprint sprint) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(sprint);
        LOGGER.info("Sprint added:" + sprint);
    }

    @Override
    public void updateSprint(Sprint sprint) {
        Session session = sessionFactory.getCurrentSession();
        session.update(sprint);
        LOGGER.info("Sprint updated:" + sprint);
    }

    @Override
    public void removeSprint(int id) {
        Session session = sessionFactory.getCurrentSession();
        Sprint sprint = (Sprint) session.load(Sprint.class, id);

        if (sprint != null) {
            session.delete(sprint);
        }
        LOGGER.info("Sprint deleted: " + sprint);
    }

    @Override
    public Sprint getSprintById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Sprint sprint = (Sprint) session.load(Sprint.class, id);
        LOGGER.info("Sprint loaded:" + sprint);
        return sprint;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Sprint> listSprints() {
        Session session = sessionFactory.getCurrentSession();
        List<Sprint> sprints = session.createQuery("from lazy_track.model.Sprint").list();
        for (Sprint s : sprints) {
            LOGGER.info("Sprint list:" + s);
        }
        return sprints;
    }
}
