package lazy_track.controller;

import lazy_track.model.Project;
import lazy_track.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProjectController {
    private ProjectService projectService;

    @Autowired
    @Qualifier(value = "projectService")
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }


    @RequestMapping(value = "projects", method = RequestMethod.GET)
    public String listProjects(Model model) {
        model.addAttribute("project", new Project());
        model.addAttribute("listProjects", projectService.list());
        return "listProjects";
    }

    @RequestMapping(value = "/project/add", method = RequestMethod.POST)
    public String addProject(@ModelAttribute("project") Project project) {
        if (project.getId() == 0) {
            projectService.add(project);
        } else {
            projectService.update(project);
        }
        return "redirect:/project";
    }

    @RequestMapping("/remove/{id}")
    public String removeProject(@PathVariable("id") int id) {
        projectService.remove(id);
        return "redirect:/projects";
    }

    @RequestMapping("edit/{id}")
    public String editProject(@PathVariable("id") int id, Model model) {
        model.addAttribute("project", projectService.get(id));
        model.addAttribute("listProject", projectService.list());
        return "projects";
    }

    @RequestMapping("project/{id}")
    public String projectData(@PathVariable("id") int id, Model model) {
        model.addAttribute("project", projectService.get(id));
        return "project";
    }
}
