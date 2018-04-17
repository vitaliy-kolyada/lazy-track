package app.controller;

import app.board.model.dto.CreatePtojectRequest;
import app.board.model.dto.EditProjectDto;
import app.board.model.dto.ProjectSelectorDto;
import app.model.Project;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ProjectApiController extends ApiController {

  private RestTemplate restTemplate;

  public ProjectApiController() {
    this.restTemplate = new RestTemplate();
  }

  public boolean create(CreatePtojectRequest request) {
    RestTemplate restTemplate = new RestTemplate();
    String url = "http://localhost:8080/api/project";
    HttpEntity<Object> requestEntity = new HttpEntity<>(request, getHeaders());
    try {
      restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  @SuppressWarnings("unchecked")
  public List<ProjectSelectorDto> getProjectSelectorDtos() {
    String url = "http://localhost:8080/api/project/selector";
    HttpEntity<String> request = new HttpEntity<>(getHeaders());
    ResponseEntity<List> response = restTemplate.exchange(url, HttpMethod.GET, request, List.class);
    List<LinkedHashMap<String, Object>> usersMap = (List<LinkedHashMap<String, Object>>) response.getBody();
    List<ProjectSelectorDto> dtos = new ArrayList<>();

    if (usersMap != null) {
      for (LinkedHashMap<String, Object> map : usersMap) {
        ProjectSelectorDto dto = new ProjectSelectorDto();
        dto.setId(UUID.fromString((String) map.get("id")));
        dto.setName((String) map.get("name"));
        dtos.add(dto);
      }
    }
    return dtos;
  }

  @SuppressWarnings("unchecked")
  public List<EditProjectDto> getEditProjectDtos() {
    String url = "http://localhost:8080/api/project/edit-form";
    HttpEntity<String> request = new HttpEntity<>(getHeaders());
    ResponseEntity<List> response = restTemplate.exchange(url, HttpMethod.GET, request, List.class);
    List<LinkedHashMap<String, Object>> usersMap = (List<LinkedHashMap<String, Object>>) response.getBody();
    List<EditProjectDto> dtos = new ArrayList<>();

    if (usersMap != null) {
      for (LinkedHashMap<String, Object> map : usersMap) {
        EditProjectDto dto = new EditProjectDto();
        dto.setId(UUID.fromString((String) map.get("id")));
        dto.setName((String) map.get("name"));
        dto.setDescription((String) map.get("description"));
        dtos.add(dto);
      }
    }
    return dtos;
  }


  public void remove(UUID id) {
    String url = "http://localhost:8080/api/project/" + id;
    HttpEntity<String> request = new HttpEntity<String>(getHeaders());
    restTemplate.exchange(url, HttpMethod.DELETE, request, Project.class);
  }

  @SuppressWarnings("unchecked")
  public boolean editProject(EditProjectDto project) {
    String url = "http://localhost:8080/api/project/edit-form";
    HttpEntity<Object> request = new HttpEntity<>(project, getHeaders());
    ResponseEntity<Boolean> response = restTemplate.exchange(url, HttpMethod.PUT, request, Boolean.class);
    return response.getBody();
  }

  public Project get(String name) {
//    for (Project project : projects) {
//      if (project.getName().equals(name))
//        return project;
//    }
    return null;
  }

  public ArrayList<Project> getAll(String companyName) {
//    ArrayList<Project> res = new ArrayList<>(projects);
//    if (res.isEmpty()) {
//      return null;
//    } else
//      return res;
    return null;
  }
}
