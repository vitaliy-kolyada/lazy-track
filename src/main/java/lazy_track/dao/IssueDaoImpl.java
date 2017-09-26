package lazy_track.dao;

import lazy_track.model.Issue;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IssueDaoImpl implements IssueDao {
    public static Logger LOGGER = LoggerFactory.getLogger(CompanyDaoImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addIssue(Issue issue) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(issue);
        LOGGER.info("Issue added:" + issue);
    }

    @Override
    public void updateIssue(Issue issue) {
        Session session = sessionFactory.getCurrentSession();
        session.update(issue);
        LOGGER.info("Issue updated:" + issue);
    }

    @Override
    public void removeIssue(int id) {
        Session session = sessionFactory.getCurrentSession();
        Issue issue = (Issue) session.load(Issue.class, id);

        if (issue != null) {
            session.delete(issue);
        }
        LOGGER.info("Issue deleted: " + issue);
    }

    @Override
    public Issue getIssueById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Issue issue = (Issue) session.load(Issue.class, id);
        LOGGER.info("Issue loaded:" + issue);
        return issue;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Issue> listIssues() {
        Session session = sessionFactory.getCurrentSession();
        List<Issue> issues = session.createQuery("from lazy_track.model.Issue").list();
        for (Issue i : issues) {
            LOGGER.info("Issue list:" + i);
        }
        return issues;
    }
}
