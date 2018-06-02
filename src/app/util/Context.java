package app.util;

import app.model.User;
import java.util.UUID;

public class Context {
  private static UUID currentProjectId;
  private static User currentUser;
  private static UUID selectedIssue;
  private static UUID selectedUserStoryId;

  public static User getCurrentUser() {
    return currentUser;
  }

  public static void setCurrentUser(User currentUser) {
    Context.currentUser = currentUser;
  }

  public static UUID getCurrentProjectId() {
    return currentProjectId;
  }

  public static void setCurrentProjectId(UUID currentProjectId) {
    Context.currentProjectId = currentProjectId;
  }

  public static UUID getSelectedIssueId() {
    return selectedIssue;
  }

  public static void setSelectedIssue(UUID selectedIssue) {
    Context.selectedIssue = selectedIssue;
  }

  public static UUID getSelectedUserStoryId() {
    return selectedUserStoryId;
  }

  public static void setSelectedUserStoryId(UUID selectedUserStoryId) {
    Context.selectedUserStoryId = selectedUserStoryId;
  }
}
