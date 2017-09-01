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
    public void add(UserStory userStory) {
        userStoryDao.add(userStory);
    }

    @Override
    @Transactional
    public void update(UserStory userStory) {
        userStoryDao.update(userStory);
    }

    @Override
    @Transactional
    public void remove(int id) {
        userStoryDao.remove(id);
    }

    @Override
    @Transactional
    public UserStory get(int id) {
        return userStoryDao.get(id);
    }

    @Override
    @Transactional
    public List<UserStory> list() {
        return userStoryDao.list();
    }
}
