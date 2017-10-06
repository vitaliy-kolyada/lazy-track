package lazy_track.service;


import lazy_track.model.User;

import java.util.List;

public interface UserService {

    User findById(long id);

    User findByName(String name);

    void save(User user);

    void update(User user);

    void deleteById(long id);

    List<User> findAll();

    boolean isExist(User user);
}
