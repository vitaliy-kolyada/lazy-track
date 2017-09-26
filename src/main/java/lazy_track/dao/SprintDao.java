package lazy_track.dao;

import lazy_track.model.Sprint;

import java.util.List;

public interface SprintDao {
    void addSprint(Sprint sprint);

    void updateSprint(Sprint sprint);

    void removeSprint(int id);

    Sprint getSprintById(int id);

    List<Sprint> listSprints();

}
