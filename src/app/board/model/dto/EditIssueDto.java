package app.board.model.dto;

import app.board.model.enumeration.Priority;
import app.board.model.enumeration.Severity;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EditIssueDto {
  private UUID id;
  private String name;
  private Severity severity;
  private Priority priority;
  private UUID stateId;
  private String stateName;

  private String userName;
  private UUID userId;

  private String description;
}
