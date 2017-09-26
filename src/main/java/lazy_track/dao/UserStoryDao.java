package lazy_track.dao;

import lazy_track.model.UserStory;

import java.util.List;

public interface UserStoryDao {
    void addUserStory(UserStory userStory);

    void updateUserStory(UserStory userStory);

    void removeUserStory(int id);

    UserStory getUserStoryById(int id);

    List<UserStory> listUserStories();

}
