package lazy_track.service;

import lazy_track.model.UserStory;

import java.util.List;

public interface UserStoryService {
    void addUserStory(UserStory userStory);

    void updateUserStory(UserStory userStory);

    void removeUserStory(int id);

    UserStory getUserStoryById(int id);

    List<UserStory> listUserStories();
}
