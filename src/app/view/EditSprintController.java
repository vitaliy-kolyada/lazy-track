package app.view;

import app.controller.ProjectApiController;
import app.controller.SprintApiController;
import app.model.Project;
import app.model.Sprint;
import app.util.Util;
import java.util.ArrayList;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class EditSprintController {
  private Sprint selectedSprint;
  private SprintApiController sprintApiController = new SprintApiController();
  private ProjectApiController projectApiController = new ProjectApiController();
  @FXML
  private TextField nameField;
  @FXML
  private ComboBox<String> projectSelector;
  @FXML
  private ComboBox<String> sprintSelector;
  @FXML
  private DatePicker datePicker;
  @FXML
  private TextArea goalArea;
  @FXML
  private Label errorLabel;

  @FXML
  public void initialize() {
    updateSelector();
  }

  private void updateSelector() {
    projectSelector.getItems().clear();
    sprintSelector.getItems().clear();
    ArrayList<Project> projects = projectApiController.getAll(Util.getCurrentUser().getCompany().getName());
    if (projects == null) {
      return;
    }
    ArrayList<Sprint> sprints = new ArrayList<>();
    for (Project project : projects) {
      ArrayList<Sprint> local = sprintApiController.getAll(project.getName());
      if (local == null) {
        return;
      }
      sprints.addAll(local);
    }

    for (Project project : projects) {
      projectSelector.getItems().add(project.getName());
    }
    for (Sprint sprint : sprints) {
      sprintSelector.getItems().add(sprint.getName());
    }
  }

  @FXML
  public void select() {
    selectedSprint = sprintApiController.get(sprintSelector.getSelectionModel().getSelectedItem());
    if (selectedSprint != null) {
      nameField.setText(selectedSprint.getName());
      goalArea.setText(selectedSprint.getGoal());
      projectSelector.getSelectionModel().select(selectedSprint.getProject().getName());
      datePicker.setValue(selectedSprint.getDate());
    }
  }

  @FXML
  public void handleSubmit() {
    errorLabel.setText("");
    if (selectedSprint == null) {
      return;
    }
    if (nameField.getText().equals("")) {
      errorLabel.setText("Sprint name is required");
      return;
    }
    if (datePicker.getValue() == null) {
      errorLabel.setText("Choose deadline date");
      return;
    }
    if (datePicker.getValue().isBefore(selectedSprint.getDate())) {
      errorLabel.setText("You can not set past date");
      return;
    }
    Project project = projectApiController.get(projectSelector.getSelectionModel().getSelectedItem());
    Sprint sprint = new Sprint(nameField.getText(), goalArea.getText(), project, datePicker.getValue());

    if (sprintApiController.update(selectedSprint.getId(), sprint)) {
      errorLabel.setText("Successfully updated \"" + sprint.getName() + "\"");
    } else {
      errorLabel.setText("Sprint name must be unique");
    }
  }

  @FXML
  public void handleDelete() {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirm deleting sprint");
    alert.setContentText("Do you really want to delete sprint \"" + selectedSprint.getName() + "\" ?");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK) {
      if (selectedSprint != null) {
//                projectApiController.remove(selectedSprint.getId());
        projectSelector.getItems().remove(selectedSprint);
        nameField.setText("");
        goalArea.setText("");
        errorLabel.setText("Removed " + selectedSprint.getName());

      }
    }
    updateSelector();
  }
}
