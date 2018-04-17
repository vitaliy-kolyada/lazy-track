package app.board.controller;

import app.board.model.dto.CreateSprintRequest;
import app.board.service.SprintService;
import app.model.enumeration.LabelColor;
import java.time.LocalDate;
import java.util.UUID;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class NewSprintController {
  private SprintService service;

  @FXML
  private DatePicker startDatePicker;
  @FXML
  private DatePicker endDatePicker;
  @FXML
  private TextField nameField;
  @FXML
  private ComboBox<String> projectSelector;
  @FXML
  private TextArea goalArea;
  @FXML
  private Label errorLabel;

  public NewSprintController() {
    this.service = new SprintService();
  }

  @FXML
  public void initialize() {
    projectSelector.getItems().clear();
    projectSelector.getItems().addAll(service.getProjectNameList());
  }

  @FXML
  public void handleSubmit() {
    validateSprintData();
    CreateSprintRequest request = buildRequest();
    boolean created = service.create(request);
    updateLabel(created, request.getName());
  }

  private void updateLabel(boolean created, String name) {
    if (created) {
      errorLabel.setTextFill(Color.web(LabelColor.OK.getCode()));
      errorLabel.setText("Created " + name);
      nameField.setText("");
      goalArea.setText("");
      startDatePicker.setValue(LocalDate.now());
    } else {
      errorLabel.setTextFill(javafx.scene.paint.Color.web(LabelColor.ERROR.getCode()));
      errorLabel.setText("Name must be unique");
    }
  }

  private CreateSprintRequest buildRequest() {
    CreateSprintRequest request = new CreateSprintRequest();
    request.setProjectId(getProjectId());
    request.setName(nameField.getText());
    request.setGoal(goalArea.getText());
    request.setStartDate(startDatePicker.getValue());
    request.setEndDate(endDatePicker.getValue());
    return request;
  }

  private UUID getProjectId() {
    String selectedProjectName = projectSelector.getSelectionModel().getSelectedItem();
    return service.getProjectId(selectedProjectName);
  }

  private void validateSprintData() {
    errorLabel.setText("");
    if (nameField.getText().equals("")) {
      errorLabel.setTextFill(Color.web(LabelColor.ERROR.getCode()));
      errorLabel.setText("Name is required");
      return;
    }
    String selectedProjectName = projectSelector.getSelectionModel().getSelectedItem();
    LocalDate startDate = startDatePicker.getValue();
    LocalDate endDate = endDatePicker.getValue();
    if (selectedProjectName == null) {
      errorLabel.setTextFill(Color.web(LabelColor.ERROR.getCode()));
      errorLabel.setText("Choose project");
      return;
    }

    UUID projectId = getProjectId();
    if (startDate == null || endDate == null) {
      errorLabel.setTextFill(Color.web(LabelColor.ERROR.getCode()));
      errorLabel.setText("Choose dates");
      return;
    }
    if (startDate.isBefore(LocalDate.now()) || endDate.isBefore(LocalDate.now())) {
      errorLabel.setTextFill(Color.web(LabelColor.ERROR.getCode()));
      errorLabel.setText("You can not set past date");
      return;
    }
    if (!service.canCreateOnSelectedDate(projectId, startDate, endDate)) {
      errorLabel.setTextFill(Color.web(LabelColor.ERROR.getCode()));
      errorLabel.setText("Can not create sprint at selected time.");
    }
  }
}
