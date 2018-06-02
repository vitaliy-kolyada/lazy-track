package app.board.api;

import app.board.model.Sprint;
import app.board.model.dto.CreateSprintRequest;
import app.board.model.dto.EditSprintDto;
import app.controller.ApiController;
import app.model.Project;
import app.util.DateConverter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class SprintApiController extends ApiController {
  private static ArrayList<Sprint> sprints = new ArrayList<>();
  private RestTemplate restTemplate = new RestTemplate();
  private DateConverter dateConverter = new DateConverter();

  public boolean create(CreateSprintRequest request) {
    String url = "http://localhost:8080/api/sprint";
    HttpEntity<Object> requestEntity = new HttpEntity<>(request, getHeaders());
    try {
      ResponseEntity responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
      return responseEntity.getStatusCode().equals(HttpStatus.OK);
    } catch (Exception e) {
      return false;
    }
  }

  public boolean update(EditSprintDto sprint) {
    String url = "http://localhost:8080/api/sprint/edit";
    HttpEntity<Object> requestEntity = new HttpEntity<>(sprint, getHeaders());
    try {
      ResponseEntity responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
      return responseEntity.getStatusCode().equals(HttpStatus.OK);
    } catch (Exception e) {
      return false;
    }
  }

  public void remove(UUID id) {
    String url = "http://localhost:8080/api/sprint/" + id;
    HttpEntity<String> request = new HttpEntity<>(getHeaders());
    restTemplate.exchange(url, HttpMethod.DELETE, request, Project.class);
  }

  public boolean canCreateOnSelectedDate(UUID projectId, LocalDate startDate, LocalDate endDate) {
    String url = "http://localhost:8080/api/sprint/can-create/" +
        projectId + "/" + dateConverter.getMilis(startDate) + "/" + dateConverter.getMilis(endDate);
    HttpEntity<String> request = new HttpEntity<>(getHeaders());
    ResponseEntity<Boolean> response = restTemplate.exchange(url, HttpMethod.GET, request, Boolean.class);
    return response.getBody();
  }

  @SuppressWarnings("unchecked")
  public List<EditSprintDto> getEditSprintDtoList() {
    String url = "http://localhost:8080/api/sprint/edit-form";
    HttpEntity<String> request = new HttpEntity<>(getHeaders());
    ResponseEntity<List> response = restTemplate.exchange(url, HttpMethod.GET, request, List.class);
    List<LinkedHashMap<String, Object>> userStoriesMap =
        (List<LinkedHashMap<String, Object>>) response.getBody();
    List<EditSprintDto> dtos = new ArrayList<>();

    if (userStoriesMap != null) {
      for (LinkedHashMap<String, Object> map : userStoriesMap) {
        EditSprintDto dto = new EditSprintDto();
        dto.setId(UUID.fromString((String) map.get("id")));
        dto.setProjectId(UUID.fromString((String) map.get("projectId")));
        dto.setName((String) map.get("name"));
        dto.setGoal((String) map.get("goal"));
        dto.setStartDate(LocalDate.parse((CharSequence) map.get("startDate")));
        dto.setEndDate(LocalDate.parse((CharSequence) map.get("endDate")));
        dtos.add(dto);
      }
    }
    return dtos;
  }

  public ArrayList<Sprint> getAll(String projectName) {
    return null;
  }


  public Sprint get(String name) {
    for (Sprint sprint : sprints) {
      if (sprint.getName().equals(name))
        return sprint;
    }
    return null;
  }
}
