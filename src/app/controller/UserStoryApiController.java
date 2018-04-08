package app.controller;

import app.model.UserStory;
import app.model.dto.CreateUserStoryRequest;
import app.model.dto.UserStorySelectorDto;
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

  @SuppressWarnings("unchecked")
  public List<UserStorySelectorDto> getUserStorySelectorDtoList() {
    String url = "http://localhost:8080/api/user-story/selector";
    RestTemplate restTemplate = new RestTemplate();
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
    RestTemplate restTemplate = new RestTemplate();
    String url = "http://localhost:8080/api/user-story";
    HttpEntity<Object> requestEntity = new HttpEntity<>(request, getHeaders());
    try {
      restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  public ArrayList<UserStory> getAll(String projectName) {
    ArrayList<UserStory> res = new ArrayList<>();
    for (UserStory userStory : userStories) {
      if (userStory.getProject().getName().equals(projectName)) {
        res.add(userStory);
      }
    }
    if (res.isEmpty()) {
      return null;
    } else
      return res;
  }

  public void remove(int id) {
    userStories.remove(id);
  }

  public boolean update(UserStory userStory) {
    return true;
  }

  public UserStory get(String name) {
    for (UserStory userStory : userStories) {
      if (userStory.getName().equals(name))
        return userStory;
    }
    return null;
  }

  @SuppressWarnings("unchecked")
  public List<UserStorySelectorDto> getUserStorySelectorDtoListByProjectId(UUID projectId) {
    String url = "http://localhost:8080/api/user-story/selector/" + projectId;
    RestTemplate restTemplate = new RestTemplate();
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
}
