package lazy_track.service;

import lazy_track.model.User;

import java.util.List;

public interface UserService {
    void add(User user);

    void update(User user);

    void remove(int id);

    User get(int id);

    List<User> list();
}
