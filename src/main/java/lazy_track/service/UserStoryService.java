package lazy_track.service;

import lazy_track.model.UserStory;
import java.util.List;

public interface UserStoryService {
    void add(UserStory userStory);

    void update(UserStory userStory);

    void remove(int id);

    UserStory get(int id);

    List<UserStory> list();
}
