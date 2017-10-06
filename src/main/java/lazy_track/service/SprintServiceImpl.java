package lazy_track.service;

import lazy_track.dao.SprintDao;
import lazy_track.model.Sprint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("sprintService")
public class SprintServiceImpl implements SprintService {

    private SprintDao sprintDao;

    @Autowired
    public void setSprintDao(SprintDao sprintDao) {
        this.sprintDao = sprintDao;
    }

    @Override
    public List<Sprint> findAll() {
        return sprintDao.findAll();
    }

    @Override
    public Sprint findById(long id) {
        return sprintDao.findById(id);
    }

    @Override
    public void save(Sprint sprint) {
        sprintDao.save(sprint);
    }

    @Override
    public void update(Sprint sprint) {
        sprintDao.update(sprint);
    }

    @Override
    public void deleteById(long id) {
        sprintDao.deleteById(id);
    }

    @Override
    public boolean isExist(Sprint sprint) {
        return sprintDao.findByName(sprint.getName()) != null;
    }

}
