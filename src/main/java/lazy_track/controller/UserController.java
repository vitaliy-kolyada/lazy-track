package lazy_track.controller;

import lazy_track.model.User;
import lazy_track.service.UserService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<User> addUser(@RequestBody User user) {
        if (user.getId() == 0) {
            userService.addUser(user);
        } else {
            userService.updateUser(user);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<User> updateUser(@RequestBody User user) {
        try {
            userService.updateUser(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<User> removeUser(@PathVariable("id") int id) {
        try {
            userService.removeUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ObjectNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<User> getUserById(@PathVariable("id") int id) {
        User user;
        try {
            user = userService.getUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (ObjectNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(value = "user", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<List<User>> listUsers() {
        List<User> userList = userService.listUsers();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
}
