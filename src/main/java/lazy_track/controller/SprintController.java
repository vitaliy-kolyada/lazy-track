package lazy_track.controller;

import lazy_track.model.Sprint;
import lazy_track.service.SprintService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SprintController {
    private SprintService sprintService;

    @Autowired
    @Qualifier(value = "sprintService")
    public void setSprintService(SprintService sprintService) {
        this.sprintService = sprintService;
    }

    @RequestMapping(value = "/sprint", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<Sprint> addSprint(@RequestBody Sprint sprint) {
        if (sprint.getId() == 0) {
            sprintService.addSprint(sprint);
        } else {
            sprintService.updateSprint(sprint);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/sprint", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<Sprint> updateSprint(@RequestBody Sprint sprint) {
        try {
            sprintService.updateSprint(sprint);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/sprint/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<Sprint> removeSprint(@PathVariable("id") int id) {
        try {
            sprintService.removeSprint(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ObjectNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "sprint/{id}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<Sprint> getSprintById(@PathVariable("id") int id) {
        Sprint sprint;
        try {
            sprint = sprintService.getSprintById(id);
            return new ResponseEntity<>(sprint, HttpStatus.OK);
        } catch (ObjectNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(value = "sprint", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<List<Sprint>> listSprints() {
        List<Sprint> sprintList = sprintService.listSprints();
        return new ResponseEntity<>(sprintList, HttpStatus.OK);
    }
}
