package lazy_track.dao;

import lazy_track.model.User;

import java.util.List;

public interface UserDao {

    void update(User user);

    User findById(long id);

    void save(User user);

    void deleteById(long id);

    List<User> findAll();

    User findByName(String name);

}
