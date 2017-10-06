package lazy_track.controller;

import lazy_track.model.Project;
import lazy_track.service.ProjectService;
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
@RequestMapping("/project")
public class ProjectRestApiController {

    private static final Logger logger = LoggerFactory.getLogger(ProjectRestApiController.class);

    private ProjectService projectService; //Service which will do all data retrieval/manipulation work

    @Autowired
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }
// -------------------Retrieve All Projects ----------------------------------------------------------------------------

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Project>> listAllProjects() {
        List<Project> projects = projectService.findAll();
        if (projects.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    // -------------------Retrieve Single Project-----------------------------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getProject(@PathVariable("id") long id) {
        logger.info("Fetching Project with id {}", id);
        Project project = projectService.findById(id);
        if (project == null) {
            logger.error("Project with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Project with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    // -------------------Create a User --------------------------------------------------------------------------------

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> createProject(@RequestBody Project project, UriComponentsBuilder ucBuilder) {
        logger.info("Creating Project : {}", project);

        if (projectService.isExist(project)) {
            logger.error("Unable to create. A Project with name {} already exist", project.getName());
            return new ResponseEntity<>(new CustomErrorType("Unable to create. A Project with name " +
                    project.getName() + " already exist."), HttpStatus.CONFLICT);
        }
        projectService.save(project);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/project/{id}").buildAndExpand(project.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a Project -------------------------------------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateProject(@PathVariable("id") long id, @RequestBody Project project) {
        logger.info("Updating Project with id {}", id);

        Project currentProject = projectService.findById(id);

        if (currentProject == null) {
            logger.error("Unable to update. Project with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Unable to update. Project with id " +
                    id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        currentProject.setCompany(project.getCompany());
        currentProject.setName(project.getName());
        currentProject.setDescription(project.getDescription());

        projectService.update(currentProject);
        return new ResponseEntity<>(currentProject, HttpStatus.OK);
    }

    // ------------------- Delete a Project ----------------------------------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProject(@PathVariable("id") long id) {
        logger.info("Fetching & Deleting Project with id {}", id);

        Project project = projectService.findById(id);
        if (project == null) {
            logger.error("Unable to delete. Project with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Unable to delete. Project with id "
                    + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        projectService.deleteById(id);
        return new ResponseEntity<Project>(HttpStatus.NO_CONTENT);
    }
}