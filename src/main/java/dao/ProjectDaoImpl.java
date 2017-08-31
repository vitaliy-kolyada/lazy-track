package dao;

import model.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectDaoImpl implements ProjectDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDaoImpl.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void add(Project project) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(project);
        LOGGER.info("Project successfully added. Project details: " + project);
    }

    @Override
    public void update(Project project) {
        Session session = sessionFactory.getCurrentSession();
        session.update(project);
        LOGGER.info("Project successfully updated. Project details: " + project);
    }

    @Override
    public void remove(int id) {
        Session session = sessionFactory.getCurrentSession();
        Project project = (Project) session.load(Project.class, id);
        if (project != null) {
            session.delete(project);
        }
        LOGGER.info("Project successfully removed. Project details: " + project);
    }

    @Override
    public Project get(int id) {
        Session session = sessionFactory.getCurrentSession();
        Project project = (Project) session.load(Project.class, id);
        LOGGER.info("Project successfully loaded. Project details: " + project);
        return project;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Project> list() {
        Session session = sessionFactory.getCurrentSession();
        List<Project> projects = session.createQuery("from model.Project").list();
        for (Project project : projects) {
            LOGGER.info("Project list: " + project);
        }
        return projects;
    }
}
