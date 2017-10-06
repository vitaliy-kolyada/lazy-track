package lazy_track.controller;

import lazy_track.model.UserStory;
import lazy_track.service.UserStoryService;
import lazy_track.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/userstory")
public class UserStoryRestApiController {

    private static final Logger logger = LoggerFactory.getLogger(UserStoryRestApiController.class);

    private UserStoryService userStoryService; //Service which will do all data retrieval/manipulation work

    @Autowired
    public void setUserStoryService(UserStoryService userStoryService) {
        this.userStoryService = userStoryService;
    }
// -------------------Retrieve All UserStories -------------------------------------------------------------------------

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<UserStory>> listAllUserStories() {
        List<UserStory> userStories = userStoryService.findAll();
        if (userStories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(userStories, HttpStatus.OK);
    }

    // -------------------Retrieve Single UserStory --------------------------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserStory(@PathVariable("id") long id) {
        logger.info("Fetching UserStory with id {}", id);
        UserStory userStory = userStoryService.findById(id);
        if (userStory == null) {
            logger.error("UserStory with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("UserStory with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userStory, HttpStatus.OK);
    }

    // -------------------Create a UserStory ---------------------------------------------------------------------------

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> createUserStory(@RequestBody UserStory userStory, UriComponentsBuilder ucBuilder) {
        logger.info("Creating UserStory : {}", userStory);

        if (userStoryService.isExist(userStory)) {
            logger.error("Unable to create. A UserStory with name {} already exist", userStory.getName());
            return new ResponseEntity<>(new CustomErrorType("Unable to create. A UserStory with name " +
                    userStory.getName() + " already exist."), HttpStatus.CONFLICT);
        }
        userStoryService.save(userStory);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/userstory/{id}").buildAndExpand(userStory.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a UserStory---------------------------------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUserStory(@PathVariable("id") long id, @RequestBody UserStory userStory) {
        logger.info("Updating UserStory with id {}", id);

        UserStory currentUserStory = userStoryService.findById(id);

        if (currentUserStory == null) {
            logger.error("Unable to update. UserStory with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Unable to update. UserStory with id " + id +
                    " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentUserStory.setName(userStory.getName());
        currentUserStory.setDescription(userStory.getDescription());
        currentUserStory.setProject(userStory.getProject());

        userStoryService.update(currentUserStory);
        return new ResponseEntity<>(currentUserStory, HttpStatus.OK);
    }

    // ------------------- Delete a UserStory---------------------------------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUserStory(@PathVariable("id") long id) {
        logger.info("Fetching & Deleting UserStory with id {}", id);

        UserStory userStory = userStoryService.findById(id);
        if (userStory == null) {
            logger.error("Unable to delete. UserStory with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Unable to delete. UserStory with id " + id +
                    " not found."),
                    HttpStatus.NOT_FOUND);
        }
        userStoryService.deleteById(id);
        return new ResponseEntity<UserStory>(HttpStatus.NO_CONTENT);
    }
}