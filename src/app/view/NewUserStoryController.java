package app.view;

import app.controller.ProjectApiController;
import app.controller.UserStoryApiController;
import app.model.Project;
import app.model.UserStory;
import app.model.dto.CreateUserStoryRequest;
import app.model.dto.ProjectSelectorDto;
import java.util.List;
import java.util.UUID;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class NewUserStoryController {
  private List<ProjectSelectorDto> projects;
  private UserStoryApiController userStoryApiController = new UserStoryApiController();
  private ProjectApiController projectApiController = new ProjectApiController();
  @FXML
  private TextField nameField;
  @FXML
  private ComboBox projectSelector;
  @FXML
  private TextArea descriptionArea;
  @FXML
  private Label errorLabel;

  @FXML
  @SuppressWarnings("unchecked")
  public void initialize() {
    projectSelector.getItems().clear();
    this.projects = projectApiController.getProjectSelectorDtos();
    if (projects == null) {
      return;
    }
    for (ProjectSelectorDto project : projects) {
      projectSelector.getItems().add(project.getName());
    }
  }

  @FXML
  public void handleSubmit() {
    errorLabel.setText("");
    if (nameField.getText().equals("")) {
      errorLabel.setText("Name is required");
      return;
    }
    if (projectSelector.getSelectionModel().getSelectedItem() == null) {
      errorLabel.setText("Choose project");
      return;
    }

    Project project = projectApiController.get((String) projectSelector.getSelectionModel().getSelectedItem());
    UserStory userStory = new UserStory(nameField.getText(), descriptionArea.getText(), project);

    CreateUserStoryRequest request = new CreateUserStoryRequest(getSelectedProjectId(),
        nameField.getText(), descriptionArea.getText());


    if (userStoryApiController.create(request)) {
      errorLabel.setText("Created " + userStory.getName());
      nameField.setText("");
      descriptionArea.setText("");
    } else {
      errorLabel.setText("Name must be unique");
    }
  }

  private UUID getSelectedProjectId() {
    String projectName = (String) projectSelector.getSelectionModel().getSelectedItem();
    UUID projectId = null;

    for (ProjectSelectorDto project : projects) {
      if (project.getName().equals(projectName)) {
        projectId = project.getId();
      }
    }
    return projectId;
  }
}
