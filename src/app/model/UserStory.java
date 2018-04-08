package app.model;


import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class UserStory implements Serializable {

  private int id;
  private String name;
  private String description;
  private Project project;

  public UserStory() {
  }

  public UserStory(String name, String description, Project project) {
    this.name = name;
    this.description = description;
    this.project = project;
  }

  public UUID getId() {
//        return id;
    return null;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserStory userStory = (UserStory) o;
    return id == userStory.id &&
        Objects.equals(name, userStory.name) &&
        Objects.equals(description, userStory.description) &&
        Objects.equals(project, userStory.project);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, project);
  }

  @Override
  public String toString() {
    return "UserStoryDao{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", description='" + description + '\'' +
        ", project=" + project +
        '}';
  }
}
