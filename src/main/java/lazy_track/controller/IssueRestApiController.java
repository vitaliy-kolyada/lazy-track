package lazy_track.controller;

import lazy_track.model.Issue;
import lazy_track.model.User;
import lazy_track.service.IssueService;
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
@RequestMapping("/issue")
public class IssueRestApiController {

    private static final Logger logger = LoggerFactory.getLogger(IssueRestApiController.class);

    private IssueService issueService; //Service which will do all data retrieval/manipulation work

    @Autowired
    public void setIssueService(IssueService issueService) {
        this.issueService = issueService;
    }
// -------------------Retrieve All Issues ------------------------------------------------------------------------------

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Issue>> listAllIssues() {
        List<Issue> issues = issueService.findAll();
        if (issues.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(issues, HttpStatus.OK);
    }

    // -------------------Retrieve Single Issue ------------------------------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getIssue(@PathVariable("id") long id) {
        logger.info("Fetching Issue with id {}", id);
        Issue issue = issueService.findById(id);
        if (issue == null) {
            logger.error("Issue with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Issue with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(issue, HttpStatus.OK);
    }

    // -------------------Create a Issue -------------------------------------------------------------------------------

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody Issue issue, UriComponentsBuilder ucBuilder) {
        logger.info("Creating Issue : {}", issue);

        if (issueService.isExist(issue)) {
            logger.error("Unable to create. A Issue with name {} already exist", issue.getName());
            return new ResponseEntity<>(new CustomErrorType("Unable to create. A Issue with name " +
                    issue.getName() + " already exist."), HttpStatus.CONFLICT);
        }
        issueService.save(issue);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/issue/{id}").buildAndExpand(issue.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a User -------------------------------------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateIssue(@PathVariable("id") long id, @RequestBody Issue issue) {
        logger.info("Updating Issue with id {}", id);

        Issue currentIssue = issueService.findById(id);

        if (currentIssue == null) {
            logger.error("Unable to update. Issue with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Unable to update. Issue with id "
                    + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentIssue.setSprint(issue.getSprint());
        currentIssue.setCreatedBy(issue.getCreatedBy());
        currentIssue.setPriority(issue.getPriority());
        currentIssue.setType(issue.getType());
        currentIssue.setState(issue.getState());
        currentIssue.setSeverity(issue.getSeverity());
        currentIssue.setSign(issue.getSign());
        currentIssue.setStoryPoints(issue.getStoryPoints());

        issueService.update(currentIssue);
        return new ResponseEntity<>(currentIssue, HttpStatus.OK);
    }

    // ------------------- Delete a Issue ------------------------------------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteIssue(@PathVariable("id") long id) {
        logger.info("Fetching & Deleting Issue with id {}", id);

        Issue issue = issueService.findById(id);
        if (issue == null) {
            logger.error("Unable to delete. Issue with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Unable to delete. Issue with id "
                    + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        issueService.deleteById(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
}