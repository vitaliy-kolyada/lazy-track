package lazy_track.controller;

import lazy_track.model.UserStory;
import lazy_track.service.UserStoryService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserStoryController {
    private UserStoryService userStoryService;

    @Autowired
    @Qualifier(value = "userStoryService")
    public void setCompanyService(UserStoryService userStoryService) {
        this.userStoryService = userStoryService;
    }

    @RequestMapping(value = "/userstory", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<UserStory> addUserStory(@RequestBody UserStory userStory) {

        if (userStory.getId() == 0) {
            userStoryService.addUserStory(userStory);
        } else {
            userStoryService.updateUserStory(userStory);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/userstory", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<UserStory> updateUserStory(@RequestBody UserStory userStory) {
        try {
            userStoryService.updateUserStory(userStory);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/userstory/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<UserStory> removeUserStory(@PathVariable("id") int id) {
        try {
            userStoryService.removeUserStory(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ObjectNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "userstory/{id}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<UserStory> getUserStoryById(@PathVariable("id") int id) {
        UserStory userStory;
        try {
            userStory = userStoryService.getUserStoryById(id);
            return new ResponseEntity<>(userStory, HttpStatus.OK);
        } catch (ObjectNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(value = "/userstory", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<List<UserStory>> listUserStories() {
        List<UserStory> userStoryList = userStoryService.listUserStories();
        return new ResponseEntity<>(userStoryList, HttpStatus.OK);
    }
}
