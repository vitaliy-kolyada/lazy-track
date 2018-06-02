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
public class IssueSummary {
  private UUID id;
  private String name;
  private UUID stateId;
  private Severity severity;
  private Priority priority;
}
