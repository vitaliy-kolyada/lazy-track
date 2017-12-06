package app.controller;

import app.model.Project;

import java.util.ArrayList;

public class ProjectApiController {
    private static ArrayList<Project> projects = new ArrayList<>();

    public boolean create(Project project) {
        for (Project project1 : projects) {
            if (project.getName().equals(project1.getName())) {
                return false;
            }
        }
        projects.add(project);
        return true;
    }

    public ArrayList<Project> getAll(String companyName) {
        ArrayList<Project> res = new ArrayList<>();
        for (Project project : projects) {
            if (project.getCompany().getName().equals(companyName)) {
                res.add(project);
            }
        }
        if (res.isEmpty()) {
            return null;
        } else
            return res;
    }

    public void remove(int id) {
        projects.remove(id);
    }

    public boolean update(int id, Project project) {
        projects.remove(id);
        return create(project);
    }

    public Project get(String name) {
        for (Project project : projects) {
            if (project.getName().equals(name))
                return project;
        }
        return null;
    }
}
