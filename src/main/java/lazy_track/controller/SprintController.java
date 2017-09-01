package lazy_track.controller;

import lazy_track.model.Sprint;
import lazy_track.service.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SprintController {
    private SprintService sprintService;

    @Autowired
    @Qualifier(value = "sprintService")
    public void setSprintService(SprintService sprintService) {
        this.sprintService = sprintService;
    }


    @RequestMapping(value = "sprint", method = RequestMethod.GET)
    public String listSprints(Model model) {
        model.addAttribute("sprint", new Sprint());
        model.addAttribute("listSprints", sprintService.list());
        return "sprints";
    }

    @RequestMapping(value = "/sprint/add", method = RequestMethod.POST)
    public String addSprint(@ModelAttribute("sprint") Sprint sprint) {
        if (sprint.getId() == 0) {
            sprintService.add(sprint);
        } else {
            sprintService.update(sprint);
        }
        return "redirect:/sprint";
    }

    @RequestMapping("/remove/{id}")
    public String removeSprint(@PathVariable("id") int id) {
        sprintService.remove(id);
        return "redirect:/sprints";
    }

    @RequestMapping("edit/{id}")
    public String editSprint(@PathVariable("id") int id, Model model) {
        model.addAttribute("sprint", sprintService.get(id));
        model.addAttribute("listSprints", sprintService.list());
        return "sprints";
    }

    @RequestMapping("sprint/{id}")
    public String sprintData(@PathVariable("id") int id, Model model) {
        model.addAttribute("sprint", sprintService.get(id));
        return "sprint";
    }
}
