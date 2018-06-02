package app.util;

import app.model.User;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

public class Util {
  private static UUID currentProjectId;
  private static User currentUser;
  private static UUID selectedIssue;

  public static User getCurrentUser() {
    return currentUser;
  }

  public static void setCurrentUser(User currentUser) {
    Util.currentUser = currentUser;
  }

  public static UUID getCurrentProjectId() {
    return currentProjectId;
  }

  public static void setCurrentProjectId(UUID currentProjectId) {
    Util.currentProjectId = currentProjectId;
  }

  public static UUID getSelectedIssue() {
    return selectedIssue;
  }

  public static void setSelectedIssue(UUID selectedIssue) {
    Util.selectedIssue = selectedIssue;
  }
}
