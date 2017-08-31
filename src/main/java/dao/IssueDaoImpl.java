package dao;

import model.Issue;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IssueDaoImpl implements IssueDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(IssueDaoImpl.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Issue issue) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(issue);
        LOGGER.info("Issue successfully added. Issue details: " + issue);
    }

    @Override
    public void update(Issue issue) {
        Session session = sessionFactory.getCurrentSession();
        session.update(issue);
        LOGGER.info("Issue successfully updated. Issue details: " + issue);
    }

    @Override
    public void remove(int id) {
        Session session = sessionFactory.getCurrentSession();
        Issue issue = (Issue) session.load(Issue.class, id);
        if (issue != null) {
            session.delete(issue);
        }
        LOGGER.info("Issue successfully removed. Issue details: " + issue);
    }

    @Override
    public Issue get(int id) {
        Session session = sessionFactory.getCurrentSession();
        Issue issue = (Issue) session.load(Issue.class, id);
        LOGGER.info("Issue successfully loaded. Issue details: " + issue);
        return issue;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Issue> list() {
        Session session = sessionFactory.getCurrentSession();
        List<Issue> issues = session.createQuery("from model.Issue").list();
        for (Issue issue : issues) {
            LOGGER.info("Issue list: " + issue);
        }
        return issues;
    }
}
