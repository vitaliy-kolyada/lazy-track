package lazy_track.service;

import lazy_track.model.Project;

import java.util.List;

public interface ProjectService {
    void addProject(Project project);

    void updateProject(Project project);

    void removeProject(int id);

    Project getProjectById(int id);

    List<Project> listProjects();
}
