package lazy_track.service;

import lazy_track.dao.UserStoryDao;
import lazy_track.model.UserStory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserStoryServiceImpl implements UserStoryService {
    private UserStoryDao userStoryDao;

    public void setUserStoryDao(UserStoryDao userStoryDao) {
        this.userStoryDao = userStoryDao;
    }

    @Override
    @Transactional
    public void addUserStory(UserStory userStory) {
        userStoryDao.addUserStory(userStory);
    }

    @Override
    @Transactional
    public void updateUserStory(UserStory userStory) {
        userStoryDao.updateUserStory(userStory);
    }

    @Override
    @Transactional
    public void removeUserStory(int id) {
        userStoryDao.removeUserStory(id);
    }

    @Override
    @Transactional
    public UserStory getUserStoryById(int id) {
        return userStoryDao.getUserStoryById(id);
    }

    @Override
    @Transactional
    public List<UserStory> listUserStories() {
        return userStoryDao.listUserStories();
    }
}
