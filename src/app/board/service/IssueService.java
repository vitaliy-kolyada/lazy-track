package app.board.service;

import app.board.api.IssueApiController;
import app.board.model.CreateIssueDto;
import app.board.model.dto.IssueSummary;
import java.util.List;
import java.util.UUID;

public class IssueService {

  private IssueApiController issueApiController = new IssueApiController();

  public List<IssueSummary> getTableSummaries(UUID userStoryId) {

    return issueApiController.getIssuesByUserStory(userStoryId);
  }

  public void submitCreate(CreateIssueDto createIssueDto) {
    issueApiController.submitCreate(createIssueDto);
  }
}
