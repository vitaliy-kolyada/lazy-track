package lazy_track.dao;

import lazy_track.model.Project;

import java.util.List;

public interface ProjectDao {
    void update(Project project);

    Project findById(long id);

    void save(Project project);

    void deleteById(long id);

    List<Project> findAll();

    Project findByName(String name);
}
