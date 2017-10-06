package lazy_track.service;

import lazy_track.model.UserStory;

import java.util.List;

public interface UserStoryService {
    UserStory findById(long id);

    void save(UserStory userStory);

    void update(UserStory userStory);

    void deleteById(long id);

    List<UserStory> findAll();

    boolean isExist(UserStory userStory);
}
