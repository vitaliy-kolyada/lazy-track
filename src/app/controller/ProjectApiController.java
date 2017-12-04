package app.controller;

import app.model.Project;

import java.util.ArrayList;
import java.util.List;

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

    public List<Project> getAll(String companyName) {
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
}
