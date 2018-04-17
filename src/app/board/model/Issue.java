package app.board.model;

import app.model.User;
import java.io.Serializable;
import java.util.Objects;

public class Issue implements Serializable {

  private int id;
  private UserStory userStory;
  private String name;
  private User createdBy;
  private int priority;
  private int type;
  private int state;
  private int severity;
  private User sign;
  private int storyPoints;

  public Issue(UserStory userStory,
               String name,
               User createdBy,
               int priority,
               int type,
               int state,
               int severity,
               User sign) {
    this.userStory = userStory;
    this.name = name;
    this.createdBy = createdBy;
    this.priority = priority;
    this.type = type;
    this.state = state;
    this.severity = severity;
    this.sign = sign;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public UserStory getUserStory() {
    return userStory;
  }

  public void setUserStory(UserStory userStory) {
    this.userStory = userStory;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public User getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(User createdBy) {
    this.createdBy = createdBy;
  }

  public int getPriority() {
    return priority;
  }

  public void setPriority(int priority) {
    this.priority = priority;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }

  public int getSeverity() {
    return severity;
  }

  public void setSeverity(int severity) {
    this.severity = severity;
  }

  public User getSign() {
    return sign;
  }

  public void setSign(User sign) {
    this.sign = sign;
  }

  public int getStoryPoints() {
    return storyPoints;
  }

  public void setStoryPoints(int storyPoints) {
    this.storyPoints = storyPoints;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Issue issue = (Issue) o;
    return id == issue.id &&
        priority == issue.priority &&
        type == issue.type &&
        state == issue.state &&
        severity == issue.severity &&
        storyPoints == issue.storyPoints &&
        Objects.equals(userStory, issue.userStory) &&
        Objects.equals(name, issue.name) &&
        Objects.equals(createdBy, issue.createdBy) &&
        Objects.equals(sign, issue.sign);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userStory, name, createdBy, priority, type, state, severity, sign, storyPoints);
  }

  @Override
  public String toString() {
    return "Issue{" +
        "id=" + id +
        ", userStory=" + userStory +
        ", name='" + name + '\'' +
        ", createdBy=" + createdBy +
        ", priority=" + priority +
        ", type=" + type +
        ", state=" + state +
        ", severity=" + severity +
        ", sign=" + sign +
        ", storyPoints=" + storyPoints +
        '}';
  }
}
