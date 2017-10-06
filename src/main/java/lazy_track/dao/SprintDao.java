package lazy_track.dao;

import lazy_track.model.Sprint;

import java.util.List;

public interface SprintDao {
    void update(Sprint sprint);

    Sprint findById(long id);

    void save(Sprint sprint);

    void deleteById(long id);

    List<Sprint> findAll();

    Sprint findByName(String name);
}
