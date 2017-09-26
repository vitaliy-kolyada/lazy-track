package lazy_track.dao;

import lazy_track.model.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectDaoImpl implements ProjectDao {

    public static Logger LOGGER = LoggerFactory.getLogger(ProjectDaoImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addProject(Project project) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(project);
        LOGGER.info("Project added:" + project);
    }

    @Override
    public void updateProject(Project project) {
        Session session = sessionFactory.getCurrentSession();
        session.update(project);
        LOGGER.info("Project updated:" + project);
    }

    @Override
    public void removeProject(int id) {
        Session session = sessionFactory.getCurrentSession();
        Project project = (Project) session.load(Project.class, id);

        if (project != null) {
            session.delete(project);
        }
        LOGGER.info("Project deleted: " + project);
    }

    @Override
    public Project getProjectById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Project project = (Project) session.load(Project.class, id);
        LOGGER.info("Project loaded:" + project);
        return project;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Project> listProjects() {
        Session session = sessionFactory.getCurrentSession();
        List<Project> projects = session.createQuery("from lazy_track.model.Project").list();
        for (Project p : projects) {
            LOGGER.info("Project list:" + p);
        }
        return projects;
    }
}
