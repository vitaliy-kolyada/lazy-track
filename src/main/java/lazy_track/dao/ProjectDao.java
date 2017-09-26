package lazy_track.dao;

import lazy_track.model.Project;

import java.util.List;

public interface ProjectDao {
    void addProject(Project project);

    void updateProject(Project project);

    void removeProject(int id);

    Project getProjectById(int id);

    List<Project> listProjects();
}
