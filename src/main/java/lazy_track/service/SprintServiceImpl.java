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
    public void addSprint(Sprint sprint) {
        sprintDao.addSprint(sprint);
    }

    @Override
    @Transactional
    public void updateSprint(Sprint sprint) {
        sprintDao.updateSprint(sprint);
    }

    @Override
    @Transactional
    public void removeSprint(int id) {
        sprintDao.removeSprint(id);
    }

    @Override
    @Transactional
    public Sprint getSprintById(int id) {
        return sprintDao.getSprintById(id);
    }

    @Override
    @Transactional
    public List<Sprint> listSprints() {
        return sprintDao.listSprints();
    }
}
