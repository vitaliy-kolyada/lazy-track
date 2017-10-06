package lazy_track.controller;

import lazy_track.model.Sprint;
import lazy_track.service.SprintService;
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
@RequestMapping("/sprint")
public class SprintRestApiController {

    private static final Logger logger = LoggerFactory.getLogger(SprintRestApiController.class);

    private SprintService sprintService; //Service which will do all data retrieval/manipulation work

    @Autowired
    public void setSprintService(SprintService sprintService) {
        this.sprintService = sprintService;
    }
// -------------------Retrieve All Sprints------------------------------------------------------------------------------

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Sprint>> listAllSprints() {
        List<Sprint> sprints = sprintService.findAll();
        if (sprints.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(sprints, HttpStatus.OK);
    }

    // -------------------Retrieve Single Sprint------------------------------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getSprint(@PathVariable("id") long id) {
        logger.info("Fetching Sprint with id {}", id);
        Sprint sprint = sprintService.findById(id);
        if (sprint == null) {
            logger.error("Sprint with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Sprint with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(sprint, HttpStatus.OK);
    }

    // -------------------Create a Sprint-------------------------------------------------------------------------------

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> createSprint(@RequestBody Sprint sprint, UriComponentsBuilder ucBuilder) {
        logger.info("Creating Sprint : {}", sprint);

        if (sprintService.isExist(sprint)) {
            logger.error("Unable to create. A Sprint with name {} already exist", sprint.getName());
            return new ResponseEntity<>(new CustomErrorType("Unable to create. A Sprint with name " +
                    sprint.getName() + " already exist."), HttpStatus.CONFLICT);
        }
        sprintService.save(sprint);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/sprint/{id}").buildAndExpand(sprint.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a Sprint -----------------------------------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateSprint(@PathVariable("id") long id, @RequestBody Sprint sprint) {
        logger.info("Updating Sprint with id {}", id);

        Sprint currentSprint = sprintService.findById(id);

        if (currentSprint == null) {
            logger.error("Unable to update. Sprint with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Unable to update. Sprint with id "
                    + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        currentSprint.setName(sprint.getName());
        currentSprint.setGoal(sprint.getGoal());
        currentSprint.setDate(sprint.getDate());
        currentSprint.setUserStory(sprint.getUserStory());

        sprintService.update(currentSprint);
        return new ResponseEntity<>(currentSprint, HttpStatus.OK);
    }

    // ------------------- Delete a Sprint -------------------------------------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteSprint(@PathVariable("id") long id) {
        logger.info("Fetching & Deleting Sprint with id {}", id);

        Sprint sprint = sprintService.findById(id);
        if (sprint == null) {
            logger.error("Unable to delete. Sprint with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Unable to delete. Sprint with id "
                    + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        sprintService.deleteById(id);
        return new ResponseEntity<Sprint>(HttpStatus.NO_CONTENT);
    }
}