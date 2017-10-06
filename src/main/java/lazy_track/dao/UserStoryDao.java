package lazy_track.dao;

import lazy_track.model.UserStory;

import java.util.List;

public interface UserStoryDao {
    void update(UserStory userStory);

    UserStory findById(long id);

    void save(UserStory userStory);

    void deleteById(long id);

    List<UserStory> findAll();

    UserStory findByName(String name);

}
