package app.board.service;

import app.board.api.IssueApiController;
import app.board.model.CreateIssueDto;
import app.board.model.dto.EditIssueDto;
import app.board.model.dto.IssueSummary;
import app.board.model.enumeration.Priority;
import app.board.model.enumeration.Severity;
import app.util.Context;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class IssueService {

  private IssueApiController issueApiController = new IssueApiController();

  public List<IssueSummary> getTableSummaries(UUID userStoryId) {

    return issueApiController.getIssuesByUserStory(userStoryId);
  }

  public boolean submitCreate(CreateIssueDto createIssueDto) {
    createIssueDto.setProjectId(Context.getCurrentProjectId());
    createIssueDto.setUserStoryId(Context.getSelectedUserStoryId());
    return issueApiController.submitCreate(createIssueDto);
  }

  public Map<UUID, List<IssueSummary>> getIssueMap(UUID userStoryId) {
    List<IssueSummary> summaries = getTableSummaries(userStoryId);

    return summaries.stream()
        .collect(Collectors.groupingBy(IssueSummary::getStateId));
  }

  public EditIssueDto getEditIssueDto(UUID issueId) {
    return issueApiController.getEditIssueDto(issueId);
  }

  public EditIssueDto getSelectedIssue() {
    return getEditIssueDto(Context.getSelectedIssueId());
  }

  public Severity getIssueSeverity(String name, List<IssueSummary> issues) {
    for (IssueSummary issue : issues) {
      if (issue.getName().equals(name)) {
        return issue.getSeverity();
      }
    }
    return null;
  }

  public Priority getIssuePriority(String name, List<IssueSummary> issues) {
    for (IssueSummary issue : issues) {
      if (issue.getName().equals(name)) {
        return issue.getPriority();
      }
    }
    return null;
  }

  public boolean submitUpdate(EditIssueDto editIssueDtoContext) {
    return issueApiController.submitUpdate(editIssueDtoContext);
  }
}
