package app.board.api;

import app.board.model.CreateIssueDto;
import app.board.model.dto.EditIssueDto;
import app.board.model.dto.IssueSummary;
import app.board.model.enumeration.Priority;
import app.board.model.enumeration.Severity;
import app.controller.ApiController;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class IssueApiController extends ApiController {
  private RestTemplate restTemplate = new RestTemplate();

  @SuppressWarnings("unchecked")
  public List<IssueSummary> getIssuesByUserStory(UUID userStoryId) {
    String url = "http://localhost:8080/api/issue/summary/" + userStoryId.toString();
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
        issueSummary.setStateId(UUID.fromString((String) map.get("stateId")));
        issueSummary.setSeverity(Severity.valueOf((String) map.get("severity")));
        issueSummary.setPriority(Priority.valueOf((String) map.get("priority")));

        dtos.add(issueSummary);
      }
    }

    return dtos;
  }

  @SuppressWarnings("unchecked")
  public EditIssueDto getEditIssueDto(UUID issueId) {
    String url = "http://localhost:8080/api/issue/" + issueId;
    HttpEntity<String> request = new HttpEntity<>(getHeaders());
    ResponseEntity<EditIssueDto> response =
        restTemplate.exchange(url, HttpMethod.GET, request, EditIssueDto.class);
    return response.getBody();
  }

  public boolean submitCreate(CreateIssueDto createIssueDto) {
    String url = "http://localhost:8080/api/issue";
    HttpEntity<Object> requestEntity = new HttpEntity<>(createIssueDto, getHeaders());
    try {
      ResponseEntity responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
      return responseEntity.getStatusCode().equals(HttpStatus.OK);
    } catch (Exception e) {
      return false;
    }
  }

  public boolean submitUpdate(EditIssueDto editIssueDtoContext) {
    String url = "http://localhost:8080/api/issue";
    HttpEntity<Object> requestEntity = new HttpEntity<>(editIssueDtoContext, getHeaders());
    try {
      ResponseEntity responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
      return responseEntity.getStatusCode().equals(HttpStatus.OK);
    } catch (Exception e) {
      return false;
    }
  }
}
