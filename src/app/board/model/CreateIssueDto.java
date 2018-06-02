package app.board.model;


import app.board.model.enumeration.Priority;
import app.board.model.enumeration.Severity;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateIssueDto {

  private String name;

  private String description;

  private Severity severity;

  private Priority priority;

  private UUID requirementId;

  private UUID stateId;

  private UUID sprintId;

  private UUID userStoryId;

  private UUID userId;
}
