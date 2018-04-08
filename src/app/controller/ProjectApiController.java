package app.controller;

import app.model.Project;
import app.model.dto.CreatePtojectRequest;
import app.model.dto.EditProjectDto;
import app.model.dto.ProjectSelectorDto;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ProjectApiController extends ApiController {
  private static ArrayList<Project> projects = new ArrayList<>();

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
    RestTemplate restTemplate = new RestTemplate();
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
    RestTemplate restTemplate = new RestTemplate();
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

  public ArrayList<Project> getAll(String companyName) {
    ArrayList<Project> res = new ArrayList<>(projects);
    if (res.isEmpty()) {
      return null;
    } else
      return res;
  }

  public void remove(UUID id) {

  }

  @SuppressWarnings("unchecked")
  public boolean update(EditProjectDto project) {
    String url = "http://localhost:8080/api/project/edit-form";
    RestTemplate restTemplate = new RestTemplate();
    HttpEntity<Object> request = new HttpEntity<>(project, getHeaders());
    ResponseEntity<Integer> response = restTemplate.exchange(url, HttpMethod.PUT, request, Integer.class);
    int updatedRows = response.getBody();
//    if (usersMap != null) {
//      for (LinkedHashMap<String, Object> map : usersMap) {
//        EditProjectDto dto = new EditProjectDto();
//        dto.setId(UUID.fromString((String) map.get("id")));
//        dto.setName((String) map.get("name"));
//        dto.setDescription((String) map.get("description"));
//        dtos.add(dto);
//      }
//    }
    return updatedRows != 0;
  }

  public Project get(String name) {
    for (Project project : projects) {
      if (project.getName().equals(name))
        return project;
    }
    return null;
  }
}
