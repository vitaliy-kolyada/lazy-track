package app.model;

import java.io.Serializable;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Project implements Serializable {

  private int id;
  private UUID companyId;
  private String name;
  private String description;

  public Project() {
  }

  public Project(String name, String description) {
    this.name = name;
    this.description = description;
  }
}
