package lazy_track.controller;

import lazy_track.model.Company;
import lazy_track.model.Project;
import lazy_track.service.ProjectService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProjectController {
    private ProjectService projectService;

    @Autowired
    @Qualifier(value = "projectService")
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    @RequestMapping(value = "/project", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<Project> addProject(@RequestBody Project project) {
        if (project.getId() == 0) {
            projectService.addProject(project);
        } else {
            projectService.updateProject(project);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/project", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<Project> updateCompany(@RequestBody Project project) {
        try {
            projectService.updateProject(project);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/project/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<Project> removeProject(@PathVariable("id") int id) {
        try {
            projectService.removeProject(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ObjectNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "project/{id}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<Project> getProjectById(@PathVariable("id") int id) {
        Project project;
        try {
            project = projectService.getProjectById(id);
            return new ResponseEntity<>(project, HttpStatus.OK);
        } catch (ObjectNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(value = "project", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<List<Project>> listCompanies() {
        List<Project> projectList = projectService.listProjects();
        return new ResponseEntity<>(projectList, HttpStatus.OK);
    }
}
