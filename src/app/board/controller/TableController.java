package app.board.controller;

import app.board.model.dto.TableStateDto;
import app.board.service.StateService;
import app.model.enumeration.LabelColor;
import app.util.Util;
import java.util.UUID;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class TableController {

  private final StateService stateService = new StateService();

  @FXML
  private ComboBox stateComboBox;
  @FXML
  private TextField nameField;
  @FXML
  private Button removeButton;
  @FXML
  private Label errorLabel;
  @FXML
  private Button submitButton;

  @FXML
  @SuppressWarnings("unchecked")
  private void initialize() {
    if (Util.getCurrentProjectId() == null) {
      errorLabel.setTextFill(Color.web(LabelColor.ERROR.getCode()));
      errorLabel.setText("You must select project before editing board");
      return;
    }
    stateComboBox.getItems().clear();
    stateComboBox.getItems().addAll(stateService.getStateNames(Util.getCurrentProjectId()));
  }

  @FXML
  public void select(ActionEvent actionEvent) {
    nameField.setText((String) stateComboBox.getSelectionModel().getSelectedItem());
  }

  @FXML
  public void handleSubmit(ActionEvent actionEvent) {
    errorLabel.setText("");
    String selectedStateName = (String) stateComboBox.getSelectionModel().getSelectedItem();
    if (selectedStateName == null) {
      TableStateDto dto = new TableStateDto();
      dto.setProjectId(Util.getCurrentProjectId());
      dto.setName(nameField.getText());
      boolean created = stateService.createState(dto);
      createdUpdateLabel(created, dto.getName());
      nameField.setText("");
    } else {
      TableStateDto dto = stateService.getDtoByName(selectedStateName);
      dto.setName(nameField.getText());
      boolean updated = stateService.editState(dto);
      renamedUpdateLabel(updated, dto.getName());
    }
    stateService.updateTableStateDtoList(Util.getCurrentProjectId());
    initialize();
  }

  private void createdUpdateLabel(boolean success, String name) {
    updateErrorLabel(success, name, "Created");
  }

  private void renamedUpdateLabel(boolean success, String name) {
    updateErrorLabel(success, name, "Renamed");
  }

  private void updateErrorLabel(boolean success, String name, String action) {
    if (success) {
      errorLabel.setTextFill(Color.web(LabelColor.OK.getCode()));
      errorLabel.setText(action + " \"" + name + "\"");
      nameField.setText("");
    } else {
      errorLabel.setTextFill(Color.web(LabelColor.ERROR.getCode()));
      errorLabel.setText("Name must be unique");
    }
  }

  @FXML
  public void handleRemove(ActionEvent actionEvent) {
    errorLabel.setText("");
    String selectedStateName = (String) stateComboBox.getSelectionModel().getSelectedItem();

    if (selectedStateName != null) {
      UUID stateId = stateService.getStateId(selectedStateName);
      stateService.remove(stateId);
      errorLabel.setTextFill(Color.web(LabelColor.OK.getCode()));
      errorLabel.setText("Removed \"" + selectedStateName + "\"");
      stateComboBox.getItems().remove(selectedStateName);
      stateService.updateTableStateDtoList(Util.getCurrentProjectId());
      initialize();
      nameField.setText("");
    } else {
      errorLabel.setTextFill(Color.web(LabelColor.ERROR.getCode()));
      errorLabel.setText("Select state");
    }
  }
}
