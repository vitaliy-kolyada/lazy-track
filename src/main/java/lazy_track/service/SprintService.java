package lazy_track.service;

import lazy_track.model.Sprint;

import java.util.List;

public interface SprintService {
    Sprint findById(long id);

    void save(Sprint sprint);

    void update(Sprint sprint);

    void deleteById(long id);

    List<Sprint> findAll();

    boolean isExist(Sprint sprint);
}
