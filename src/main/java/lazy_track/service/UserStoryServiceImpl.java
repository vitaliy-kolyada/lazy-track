package lazy_track.service;

import lazy_track.dao.UserStoryDao;
import lazy_track.model.UserStory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userStoryService")
public class UserStoryServiceImpl implements UserStoryService {

    private UserStoryDao userStoryDao;

    @Autowired
    public void setUserStoryDao(UserStoryDao userStoryDao) {
        this.userStoryDao = userStoryDao;
    }

    @Override
    public List<UserStory> findAll() {
        return userStoryDao.findAll();
    }

    @Override
    public UserStory findById(long id) {
        return userStoryDao.findById(id);
    }

    @Override
    public void save(UserStory userStory) {
        userStoryDao.save(userStory);
    }

    @Override
    public void update(UserStory userStory) {
        userStoryDao.update(userStory);
    }

    @Override
    public void deleteById(long id) {
        userStoryDao.deleteById(id);
    }

    @Override
    public boolean isExist(UserStory userStory) {
        return userStoryDao.findByName(userStory.getName()) != null;
    }
}
