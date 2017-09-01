package lazy_track.dao;

import lazy_track.model.Sprint;

import java.util.List;

public interface SprintDao {
    void add(Sprint sprint);

    void update(Sprint sprint);

    void remove(int id);

    Sprint get(int id);

    List<Sprint> list();
}
