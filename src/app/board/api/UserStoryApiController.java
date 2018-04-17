package app.board.api;

import app.board.model.UserStory;
import app.board.model.dto.CreateUserStoryRequest;
import app.board.model.dto.EditUserStoryDto;
import app.board.model.dto.UserStorySelectorDto;
import app.controller.ApiController;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class UserStoryApiController extends ApiController {
  private static ArrayList<UserStory> userStories = new ArrayList<>();
  private RestTemplate restTemplate = new RestTemplate();


  @SuppressWarnings("unchecked")
  public List<UserStorySelectorDto> getUserStorySelectorDtoList() {
    String url = "http://localhost:8080/api/user-story/selector";
    HttpEntity<String> request = new HttpEntity<>(getHeaders());
    ResponseEntity<List> response = restTemplate.exchange(url, HttpMethod.GET, request, List.class);
    List<LinkedHashMap<String, Object>> userStoriesMap = (List<LinkedHashMap<String, Object>>) response.getBody();
    List<UserStorySelectorDto> dtos = new ArrayList<>();

    if (userStoriesMap != null) {
      for (LinkedHashMap<String, Object> map : userStoriesMap) {
        UserStorySelectorDto dto = new UserStorySelectorDto();
        dto.setId(UUID.fromString((String) map.get("id")));
        dto.setName((String) map.get("name"));
        dtos.add(dto);
      }
    }
    return dtos;
  }


  public boolean create(CreateUserStoryRequest request) {
    String url = "http://localhost:8080/api/user-story";
    HttpEntity<Object> requestEntity = new HttpEntity<>(request, getHeaders());
    try {
      restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  @SuppressWarnings("unchecked")
  public List<EditUserStoryDto> getEditUserStoryDtos() {
    String url = "http://localhost:8080/api/user-story/edit-form";
    HttpEntity<String> request = new HttpEntity<>(getHeaders());
    ResponseEntity<List> response = restTemplate.exchange(url, HttpMethod.GET, request, List.class);
    List<LinkedHashMap<String, Object>> userStoriesMap = (List<LinkedHashMap<String, Object>>) response.getBody();
    List<EditUserStoryDto> dtos = new ArrayList<>();

    if (userStoriesMap != null) {
      for (LinkedHashMap<String, Object> map : userStoriesMap) {
        EditUserStoryDto dto = new EditUserStoryDto();
        dto.setId(UUID.fromString((String) map.get("id")));
        dto.setProjectId(UUID.fromString((String) map.get("projectId")));
        dto.setName((String) map.get("name"));
        dto.setDescription((String) map.get("description"));
        dtos.add(dto);
      }
    }
    return dtos;
  }

  public void remove(UUID id) {
    String url = "http://localhost:8080/api/user-story/" + id;
    HttpEntity<String> request = new HttpEntity<String>(getHeaders());
    restTemplate.exchange(url, HttpMethod.DELETE, request, UserStory.class);
  }

  public boolean editUserStory(EditUserStoryDto userStory) {
    String url = "http://localhost:8080/api/user-story/edit-form";
    HttpEntity<Object> request = new HttpEntity<>(userStory, getHeaders());
    ResponseEntity<Boolean> response = restTemplate.exchange(url, HttpMethod.PUT, request, Boolean.class);
    return response.getBody();
  }

  @SuppressWarnings("unchecked")
  public List<UserStorySelectorDto> getUserStorySelectorDtoListByProjectId(UUID projectId) {
    String url = "http://localhost:8080/api/user-story/selector/" + projectId;
    HttpEntity<String> request = new HttpEntity<>(getHeaders());
    ResponseEntity<List> response = restTemplate.exchange(url, HttpMethod.GET, request, List.class);
    List<LinkedHashMap<String, Object>> userStoriesMap = (List<LinkedHashMap<String, Object>>) response.getBody();
    List<UserStorySelectorDto> dtos = new ArrayList<>();

    if (userStoriesMap != null) {
      for (LinkedHashMap<String, Object> map : userStoriesMap) {
        UserStorySelectorDto dto = new UserStorySelectorDto();
        dto.setId(UUID.fromString((String) map.get("id")));
        dto.setName((String) map.get("name"));
        dtos.add(dto);
      }
    }
    return dtos;
  }

  public UserStory get(String name) {
    for (UserStory userStory : userStories) {
      if (userStory.getName().equals(name))
        return userStory;
    }
    return null;
  }
}
