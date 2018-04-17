package app.board.model.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TableStateDto {
  private UUID projectId;
  private UUID stateId;
  private String name;

  public TableStateDto(UUID stateId, String name) {
    this.stateId = stateId;
    this.name = name;
  }
}
