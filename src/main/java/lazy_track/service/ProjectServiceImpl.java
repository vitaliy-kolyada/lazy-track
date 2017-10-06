package lazy_track.service;

import lazy_track.dao.ProjectDao;
import lazy_track.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {

    private ProjectDao projectDao;

    @Autowired
    public void setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    @Override
    public List<Project> findAll() {
        return projectDao.findAll();
    }

    @Override
    public Project findById(long id) {
        return projectDao.findById(id);
    }

    @Override
    public void save(Project project) {
        projectDao.save(project);
    }

    @Override
    public void update(Project project) {
        projectDao.update(project);
    }

    @Override
    public void deleteById(long id) {
        projectDao.deleteById(id);
    }

    @Override
    public boolean isExist(Project project) {
        return projectDao.findByName(project.getName()) != null;
    }
}
