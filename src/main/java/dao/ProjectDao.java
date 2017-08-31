package dao;

import model.Project;

import java.util.List;

public interface ProjectDao {
    void add(Project project);

    void update(Project project);

    void remove(int id);

    Project get(int id);

    List<Project> list();
}
