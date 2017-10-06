package lazy_track.service;

import lazy_track.model.Project;

import java.util.List;

public interface ProjectService {
    Project findById(long id);

    void save(Project project);

    void update(Project project);

    void deleteById(long id);

    List<Project> findAll();

    boolean isExist(Project project);
}
