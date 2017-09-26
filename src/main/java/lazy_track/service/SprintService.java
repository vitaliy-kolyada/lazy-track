package lazy_track.service;

import lazy_track.model.Sprint;

import java.util.List;

public interface SprintService {
    void addSprint(Sprint sprint);

    void updateSprint(Sprint sprint);

    void removeSprint(int id);

    Sprint getSprintById(int id);

    List<Sprint> listSprints();
}
