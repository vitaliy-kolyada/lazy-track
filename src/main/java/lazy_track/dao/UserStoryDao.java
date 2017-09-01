package lazy_track.dao;

import lazy_track.model.UserStory;

import java.util.List;

public interface UserStoryDao {
    void add(UserStory userStory);

    void update(UserStory userStory);

    void remove(int id);

    UserStory get(int id);

    List<UserStory> list();
}
