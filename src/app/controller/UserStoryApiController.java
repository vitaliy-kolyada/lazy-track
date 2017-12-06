package app.controller;

import app.model.UserStory;

import java.util.ArrayList;

public class UserStoryApiController {
    private static ArrayList<UserStory> userStories = new ArrayList<>();

    public boolean create(UserStory userStory) {
        ArrayList<UserStory> localUserStories = getAll(userStory.getProject().getName());
        if (localUserStories != null) {
            for (UserStory story : localUserStories) {
                if (story.getName().equals(userStory.getName())) {
                    return false;
                }

            }
            userStories.add(userStory);
            return true;
        } else {
            userStories.add(userStory);
            return true;
        }
    }

    public ArrayList<UserStory> getAll(String projectName) {
        ArrayList<UserStory> res = new ArrayList<>();
        for (UserStory userStory : userStories) {
            if (userStory.getProject().getName().equals(projectName)) {
                res.add(userStory);
            }
        }
        if (res.isEmpty()) {
            return null;
        } else
            return res;
    }

    public void remove(int id) {
        userStories.remove(id);
    }

    public boolean update(int id, UserStory userStory) {
        userStories.remove(id);
        return create(userStory);
    }

    public UserStory get(String name) {
        for (UserStory userStory : userStories) {
            if (userStory.getName().equals(name))
                return userStory;
        }
        return null;
    }
}
