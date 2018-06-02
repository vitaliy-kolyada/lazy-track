package app.board.api;

import app.board.model.CreateIssueDto;
import app.board.model.dto.IssueSummary;
import app.controller.ApiController;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class IssueApiController extends ApiController {
  private RestTemplate restTemplate = new RestTemplate();

  @SuppressWarnings("unchecked")
  public List<IssueSummary> getIssuesByUserStory(UUID userStoryId) {
    String url = "http://localhost:8080/api/issue/summary/" + userStoryId;
    HttpEntity<String> request = new HttpEntity<>(getHeaders());
    ResponseEntity<List> response = restTemplate.exchange(url, HttpMethod.GET, request, List.class);
    List<LinkedHashMap<String, Object>> statesMap =
        (List<LinkedHashMap<String, Object>>) response.getBody();
    List<IssueSummary> dtos = new ArrayList<>();

    if (statesMap != null) {
      for (LinkedHashMap<String, Object> map : statesMap) {

        IssueSummary issueSummary = new IssueSummary();
        issueSummary.setId(UUID.fromString((String) map.get("id")));
        issueSummary.setName((String) map.get("name"));
      }
    }
    return dtos;
  }

  public void submitCreate(CreateIssueDto createIssueDto) {

  }
}
