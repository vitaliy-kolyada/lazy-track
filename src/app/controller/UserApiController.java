package app.controller;

import app.model.Company;
import app.model.User;

import java.util.ArrayList;

public class UserApiController {
    private static ArrayList<User> users = new ArrayList<>();

    static {
        User testUser = new User();
        testUser.setLogin("1");
        testUser.setPassword("1");
        users.add(testUser);
        testUser.setCompany(new Company("name", "desc"));
    }

    public User getUser(String login, String password) {
        for (User user : users)
            if (user.getLogin().equals(login) && user.getPassword().equals(password))
                return user;
        return null;
    }

    public boolean register(User user) {
        for (User user1 : users) {
            if (user1.getLogin().equals(user.getLogin())) {
                return false;
            }
        }
        users.add(user);
        return true;
    }
}
