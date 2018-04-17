package app.board.model;


import app.model.Project;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserStory implements Serializable {

  private int id;
  private String name;
  private String description;
  private Project project;

  public UserStory(String name, String description, Project project) {
    this.name = name;
    this.description = description;
    this.project = project;
  }

}
