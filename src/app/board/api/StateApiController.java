package app.board.api;

import app.board.model.dto.TableStateDto;
import app.controller.ApiController;
import app.model.Project;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class StateApiController extends ApiController {

  private RestTemplate restTemplate = new RestTemplate();

  @SuppressWarnings("unchecked")
  public List<TableStateDto> getTableStateDtos(UUID projectId) {
    String url = "http://localhost:8080/api/state/table/" + projectId;
    HttpEntity<String> request = new HttpEntity<>(getHeaders());
    ResponseEntity<List> response = restTemplate.exchange(url, HttpMethod.GET, request, List.class);
    List<LinkedHashMap<String, Object>> statesMap =
        (List<LinkedHashMap<String, Object>>) response.getBody();
    List<TableStateDto> dtos = new ArrayList<>();

    if (statesMap != null) {
      for (LinkedHashMap<String, Object> map : statesMap) {
        TableStateDto dto = new TableStateDto();
        dto.setStateId(UUID.fromString((String) map.get("stateId")));
        dto.setProjectId(UUID.fromString((String) map.get("projectId")));
        dto.setName((String) map.get("name"));
        dtos.add(dto);
      }
    }
    return dtos;
  }

  public boolean createState(TableStateDto dto) {
    String url = "http://localhost:8080/api/state/table";
    HttpEntity<Object> requestEntity = new HttpEntity<>(dto, getHeaders());
    try {
      restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  public boolean editState(TableStateDto dto) {
    String url = "http://localhost:8080/api/state/table";
    HttpEntity<Object> requestEntity = new HttpEntity<>(dto, getHeaders());
    try {
      restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  public void remove(UUID id) {
    String url = "http://localhost:8080/api/state/" + id;
    HttpEntity<String> request = new HttpEntity<>(getHeaders());
    restTemplate.exchange(url, HttpMethod.DELETE, request, Project.class);
  }
}
