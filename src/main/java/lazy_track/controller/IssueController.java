package lazy_track.controller;

import lazy_track.model.Issue;
import lazy_track.service.IssueService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class IssueController {
    private IssueService issueService;

    @Autowired
    @Qualifier(value = "issueService")
    public void setIssueService(IssueService issueService) {
        this.issueService = issueService;
    }

    @RequestMapping(value = "/issue", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<Issue> addIssue(@RequestBody Issue issue) {
        if (issue.getId() == 0) {
            issueService.addIssue(issue);
        } else {
            issueService.updateIssue(issue);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/issue", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<Issue> updateCompany(@RequestBody Issue issue) {
        try {
            issueService.updateIssue(issue);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/issue/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<Issue> removeIssue(@PathVariable("id") int id) {
        try {
            issueService.removeIssue(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ObjectNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "issue/{id}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<Issue> getIssueById(@PathVariable("id") int id) {
        Issue issue;
        try {
            issue = issueService.getIssueById(id);
            return new ResponseEntity<>(issue, HttpStatus.OK);
        } catch (ObjectNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(value = "issue", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<List<Issue>> listIssues() {
        List<Issue> issueList = issueService.listIssues();
        return new ResponseEntity<>(issueList, HttpStatus.OK);
    }
}
