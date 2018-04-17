package app.board.controller;

import app.board.model.dto.EditSprintDto;
import app.board.service.SprintService;
import app.model.enumeration.LabelColor;
import java.time.LocalDate;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class EditSprintController {

  private SprintService sprintService = new SprintService();
  private EditSprintDto selectedSprint;


  @FXML
  private TextField nameField;
  @FXML
  private DatePicker startPicker;
  @FXML
  private DatePicker endDatePicker;
  @FXML
  private ComboBox<String> projectSelector;
  @FXML
  private ComboBox<String> sprintSelector;
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
    projectSelector.getItems().addAll(sprintService.getProjectNameList());
    sprintSelector.getItems().clear();
    sprintSelector.getItems().addAll(sprintService.getSprintNameList());
  }

  @FXML
  public void select() {
    selectedSprint = sprintService.getByName(
        sprintSelector.getSelectionModel().getSelectedItem());

    if (selectedSprint != null) {
      nameField.setText(selectedSprint.getName());
      goalArea.setText(selectedSprint.getGoal());
      projectSelector.getSelectionModel()
          .select(sprintService.getProjectName(selectedSprint.getProjectId()));
      startPicker.setValue(selectedSprint.getStartDate());
      endDatePicker.setValue(selectedSprint.getEndDate());
    }
  }

  @FXML
  public void handleSubmit() {
    validSprintData();
    EditSprintDto editSprintDto = sprintService.getByName(
        sprintSelector.getSelectionModel().getSelectedItem());

    editSprintDto.setName(nameField.getText());
    editSprintDto.setGoal(goalArea.getText());
    editSprintDto.setStartDate(startPicker.getValue());
    editSprintDto.setEndDate(startPicker.getValue());

    boolean updated = sprintService.update(editSprintDto);
    updateLabedText(updated, editSprintDto.getName());

  }

  private void updateLabedText(boolean updated, String name) {
    if (updated) {
      errorLabel.setTextFill(Color.web(LabelColor.OK.getCode()));
      errorLabel.setText("Successfully updated \"" + name + "\"");
    } else {
      errorLabel.setTextFill(Color.web(LabelColor.ERROR.getCode()));
      errorLabel.setText("Sprint name must be unique");
    }
  }

  private void validSprintData() {
    errorLabel.setText("");
    if (selectedSprint == null) {
      return;
    }
    if (nameField.getText().equals("")) {
      errorLabel.setTextFill(Color.web(LabelColor.ERROR.getCode()));
      errorLabel.setText("Sprint name is required");
      return;
    }
    if (startPicker.getValue() == null
        || endDatePicker.getValue() == null) {
      errorLabel.setTextFill(Color.web(LabelColor.ERROR.getCode()));
      errorLabel.setText("Choose deadline date");
      return;
    }
    if (startPicker.getValue().isBefore(LocalDate.now())
        || endDatePicker.getValue().isBefore(LocalDate.now())) {
      errorLabel.setTextFill(Color.web(LabelColor.ERROR.getCode()));
      errorLabel.setText("You can not set past date");
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
        sprintService.remove(selectedSprint.getId());
        projectSelector.getItems().remove(selectedSprint.getName());
        nameField.setText("");
        goalArea.setText("");
        errorLabel.setTextFill(Color.web(LabelColor.OK.getCode()));
        errorLabel.setText("Removed " + selectedSprint.getName());

      }
    }
    updateSelector();
  }
}
