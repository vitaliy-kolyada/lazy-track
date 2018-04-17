package app.board.controller;

import app.board.model.dto.EditProjectDto;
import app.controller.ProjectApiController;
import app.model.enumeration.LabelColor;
import java.util.List;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class EditProjectController {

  private List<EditProjectDto> projects;
  private EditProjectDto selectedProject;
  private ProjectApiController projectApiController;
  @FXML
  private TextField nameField;
  @FXML
  private TextArea descriptionArea;
  @FXML
  private Label errorLabel;
  @FXML
  private ComboBox projectSelector;

  public EditProjectController() {
    this.projectApiController = new ProjectApiController();
  }

  @FXML
  public void initialize() {
    updateSelector();
  }

  @SuppressWarnings("unchecked")
  private void updateSelector() {
    projectSelector.getItems().clear();
    projects = projectApiController.getEditProjectDtos();
    if (projects == null) {
      return;
    }
    for (EditProjectDto project : projects) {
      projectSelector.getItems().add(project.getName());
    }
  }

  @FXML
  public void select() {
    String selectedName = (String) projectSelector.getSelectionModel().getSelectedItem();
    selectedProject = getSelectedEditProjectDto(selectedName);
    if (selectedProject != null) {
      nameField.setText(selectedProject.getName());
      descriptionArea.setText(selectedProject.getDescription());
    }
  }

  private EditProjectDto getSelectedEditProjectDto(String projectName) {
    EditProjectDto selected = null;
    for (EditProjectDto project : projects) {
      if (project.getName().equals(projectName)) {
        selected = project;
      }
    }
    return selected;
  }

  @FXML
  public void handleSubmit() {
    if (selectedProject == null) {
      return;
    }
    if (nameField.getText().equals("")) {
      errorLabel.setTextFill(Color.web(LabelColor.ERROR.getCode()));
      errorLabel.setText("Project name is required");
      return;
    }
    selectedProject.setName(nameField.getText());
    selectedProject.setDescription(descriptionArea.getText());
    if (projectApiController.editProject(selectedProject)) {
      errorLabel.setTextFill(Color.web(LabelColor.OK.getCode()));
      errorLabel.setText("Successfully updated \"" + selectedProject.getName() + "\"");
    } else {
      errorLabel.setTextFill(Color.web(LabelColor.ERROR.getCode()));
      errorLabel.setText("Project name must be unique");
    }
    updateSelector();
  }

  @FXML
  public void handleDelete() {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirm deleting project");
    alert.setContentText("Do you really want to delete project \"" + selectedProject.getName() + "\" ?");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK) {
      if (selectedProject != null) {
        projectApiController.remove(selectedProject.getId());
        projectSelector.getItems().remove(selectedProject);
        nameField.setText("");
        descriptionArea.setText("");
        errorLabel.setTextFill(Color.web(LabelColor.OK.getCode()));
        errorLabel.setText("Removed " + selectedProject.getName());
      }
    }
    updateSelector();
  }
}
