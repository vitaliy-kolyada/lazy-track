package dao;

import model.User;

import java.util.List;

public interface UserDao {
    void add(User user);

    void update(User user);

    void remove(int id);

    User get(int id);

    List<User> list();
}
