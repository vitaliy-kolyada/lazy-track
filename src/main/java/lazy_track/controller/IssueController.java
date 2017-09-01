package lazy_track.controller;

import lazy_track.model.Issue;
import lazy_track.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IssueController {
    private IssueService issueService;

    @Autowired
    @Qualifier(value = "issueService")
    public void setIssueService(IssueService issueService) {
        this.issueService = issueService;
    }


    @RequestMapping(value = "issues", method = RequestMethod.GET)
    public String listIssues(Model model) {
        model.addAttribute("issue", new Issue());
        model.addAttribute("listIssues", issueService.list());
        return "issues";
    }

    @RequestMapping(value = "/issues/add", method = RequestMethod.POST)
    public String addIssue(@ModelAttribute("issue") Issue issue) {
        if (issue.getId() == 0) {
            issueService.add(issue);
        } else {
            issueService.update(issue);
        }
        return "redirect:/issue";
    }

    @RequestMapping("/remove/{id}")
    public String removeIssue(@PathVariable("id") int id) {
        issueService.remove(id);
        return "redirect:/issue";
    }

    @RequestMapping("edit/{id}")
    public String editIssue(@PathVariable("id") int id, Model model) {
        model.addAttribute("issue", issueService.get(id));
        model.addAttribute("listIssues", issueService.list());
        return "issues";
    }

    @RequestMapping("issue/{id}")
    public String issueData(@PathVariable("id") int id, Model model) {
        model.addAttribute("issue", issueService.get(id));
        return "issue";
    }
}
