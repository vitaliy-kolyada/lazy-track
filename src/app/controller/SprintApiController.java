package app.controller;

import app.model.Sprint;

import java.util.ArrayList;

public class SprintApiController {
    private static ArrayList<Sprint> sprints = new ArrayList<>();

    public boolean create(Sprint sprint) {
        ArrayList<Sprint> localSprints = getAll(sprint.getProject().getName());
        if (localSprints != null) {
            for (Sprint sprint1 : localSprints) {
                if (sprint1.getName().equals(sprint.getName())) {
                    return false;
                }

            }
            sprints.add(sprint);
            return true;
        } else {
            sprints.add(sprint);
            return true;
        }
    }

    public ArrayList<Sprint> getAll(String projectName) {
        ArrayList<Sprint> res = new ArrayList<>();
        for (Sprint sprint : sprints) {
            if (sprint.getProject().getName().equals(projectName)) {
                res.add(sprint);
            }
        }
        if (res.isEmpty()) {
            return null;
        } else
            return res;
    }

    public void remove(int id) {
        sprints.remove(id);
    }

    public boolean update(int id, Sprint sprint) {
        sprints.remove(id);
        return create(sprint);
    }

    public Sprint get(String name) {
        for (Sprint sprint : sprints) {
            if (sprint.getName().equals(name))
                return sprint;
        }
        return null;
    }
}
