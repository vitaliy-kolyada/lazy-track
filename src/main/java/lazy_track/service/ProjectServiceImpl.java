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
    public void addProject(Project project) {
        projectDao.addProject(project);
    }

    @Override
    @Transactional
    public void updateProject(Project project) {
        projectDao.updateProject(project);
    }

    @Override
    @Transactional
    public void removeProject(int id) {
        projectDao.removeProject(id);
    }

    @Override
    @Transactional
    public Project getProjectById(int id) {
        return projectDao.getProjectById(id);
    }

    @Override
    @Transactional
    public List<Project> listProjects() {
        return projectDao.listProjects();
    }
}
