package lazy_track.service;

import lazy_track.dao.ProjectDao;
import lazy_track.model.Project;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    private ProjectDao projectDao;

    public void setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    @Override
    @Transactional
    public void add(Project project) {
        projectDao.add(project);
    }

    @Override
    @Transactional
    public void update(Project project) {
        projectDao.update(project);
    }

    @Override
    @Transactional
    public void remove(int id) {
        projectDao.remove(id);
    }

    @Override
    @Transactional
    public Project get(int id) {
        return projectDao.get(id);
    }

    @Override
    @Transactional
    public List<Project> list() {
        return projectDao.list();
    }
}
