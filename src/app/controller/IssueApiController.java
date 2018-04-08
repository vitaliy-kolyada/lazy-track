package app.controller;

import app.model.Issue;

import java.util.ArrayList;

public class IssueApiController {
    private static ArrayList<Issue> issues = new ArrayList<>();
    private static UserStoryApiController userStoryApiController = new UserStoryApiController();

    static {
        issues.add(new Issue(userStoryApiController.get("user story"), "opened issue", null, 1, 1, 1, 1, null));
        issues.add(new Issue(userStoryApiController.get("user story"), "one more  issue", null, 1, 1, 1, 1, null));
        issues.add(new Issue(userStoryApiController.get("user story"), "bla bla", null, 1, 1, 1, 1, null));
        issues.add(new Issue(userStoryApiController.get("user story"), "create", null, 1, 1, 1, 1, null));
        issues.add(new Issue(userStoryApiController.get("user story"), "update", null, 1, 1, 1, 1, null));
        issues.add(new Issue(userStoryApiController.get("user story"), "in progress", null, 1, 1, 2, 1, null));
        issues.add(new Issue(userStoryApiController.get("user story"), "reopen", null, 1, 1, 3, 1, null));
        issues.add(new Issue(userStoryApiController.get("user story"), "fixed", null, 1, 1, 4, 1, null));
        issues.add(new Issue(userStoryApiController.get("user story"), "verified", null, 1, 1, 5, 1, null));
    }

    public boolean create(Issue issue) {
        for (Issue issue1 : issues) {
            if (issue1.getName().equals(issue.getName())) {
                return false;
            }
        }
        issues.add(issue);
        return true;
    }

    public ArrayList<Issue> getAll(String userStoryName) {
        ArrayList<Issue> res = new ArrayList<>();
        for (Issue issue : issues) {
            if (issue.getUserStory().getName().equals(userStoryName)) {
                res.add(issue);
            }
        }
        if (res.isEmpty()) {
            return null;
        } else
            return res;
    }

    public ArrayList<Issue> getAll(String userStoryName, int state) {
        ArrayList<Issue> issues = getAll(userStoryName);
        if (issues != null) {
            ArrayList<Issue> res = new ArrayList<>();
            for (Issue issue : issues) {
                if (issue.getState() == state) {
                    res.add(issue);
                }
            }
            return res;
        }
        return null;
    }

    public void remove(int id) {
        issues.remove(id);
    }

    public boolean update(int id, Issue issue) {
        issues.remove(id);
        return create(issue);
    }

    public Issue get(String name) {
        for (Issue issue : issues) {
            if (issue.getName().equals(name))
                return issue;
        }
        return null;
    }
}
