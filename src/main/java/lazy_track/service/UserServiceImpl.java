package lazy_track.service;

import lazy_track.dao.UserDao;
import lazy_track.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public User findById(long id) {
        return userDao.findById(id);
    }

    public User findByName(String name) {
        return userDao.findByName(name);
    }

    public void save(User user) {
        userDao.save(user);
    }

    public void update(User user) {
        userDao.update(user);
    }

    public void deleteById(long id) {
        userDao.deleteById(id);
    }

    public boolean isExist(User user) {
        return findByName(user.getName()) != null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User applicationUser = userDao.findByName(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(
                applicationUser.getLogin(), applicationUser.getPassword(), Collections.emptyList());
    }
}
