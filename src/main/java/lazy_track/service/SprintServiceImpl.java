package lazy_track.service;

import lazy_track.dao.SprintDao;
import lazy_track.model.Sprint;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SprintServiceImpl implements SprintService {
    private SprintDao sprintDao;

    public void setSprintDao(SprintDao sprintDao) {
        this.sprintDao = sprintDao;
    }

    @Override
    @Transactional
    public void add(Sprint sprint) {
        sprintDao.add(sprint);
    }

    @Override
    @Transactional
    public void update(Sprint sprint) {
        sprintDao.update(sprint);
    }

    @Override
    @Transactional
    public void remove(int id) {
        sprintDao.remove(id);
    }

    @Override
    @Transactional
    public Sprint get(int id) {
        return sprintDao.get(id);
    }

    @Override
    @Transactional
    public List<Sprint> list() {
        return sprintDao.list();
    }
}
