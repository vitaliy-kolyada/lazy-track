package app.board.controller;

import app.board.model.dto.EditUserStoryDto;
import app.board.service.UserStoryService;
import app.model.enumeration.LabelColor;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class EditUserStoryController {
  private UserStoryService service;

  @FXML
  private TextArea descriptionArea;
  @FXML
  private Label errorLabel;
  @FXML
  private ComboBox<String> userStorySelector;
  @FXML
  private ComboBox<String> projectSelector;
  @FXML
  private TextField nameField;

  public EditUserStoryController() {
    service = new UserStoryService();
  }

  @FXML
  public void initInputFields() {
    EditUserStoryDto dto = service.getUserStoryByName(
        userStorySelector.getSelectionModel().getSelectedItem());
    if (dto != null) {
      projectSelector.getSelectionModel().select(service.getProjectNameById(dto.getProjectId()));
      nameField.setText(dto.getName());
      descriptionArea.setText(dto.getDescription());
      service.setSelectedUserStory(dto);
    }
  }


  @FXML
  public void handleSubmit() {
    errorLabel.setText("");
    if (service.getSelectedUserStory() == null) {
      errorLabel.setTextFill(Color.web(LabelColor.ERROR.getCode()));
      errorLabel.setText("Select user story");
      return;
    }
    if (nameField.getText().equals("")) {
      errorLabel.setTextFill(Color.web(LabelColor.ERROR.getCode()));
      errorLabel.setText("User story name is required");
      return;
    }
    service.getSelectedUserStory().setName(nameField.getText());
    service.getSelectedUserStory().setDescription(descriptionArea.getText());
    if (service.update(service.getSelectedUserStory())) {
      errorLabel.setTextFill(Color.web(LabelColor.OK.getCode()));
      errorLabel.setText("Successfully updated \"" + service.getSelectedUserStory().getName() + "\"");
    } else {
      errorLabel.setText("User story name must be unique");
    }
  }

  @FXML
  public void handleDelete() {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirm deleting user story");
    alert.setContentText("Do you really want to delete user story \""
        + service.getSelectedUserStory().getName() + "\" ?");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK) {
      if (service.getSelectedUserStory() != null) {
        service.remove(service.getSelectedUserStory().getId());
        projectSelector.getItems().remove(service.getSelectedUserStory().getName());
        nameField.setText("");
        descriptionArea.setText("");
        errorLabel.setTextFill(Color.web(LabelColor.OK.getCode()));
        errorLabel.setText("Removed " + service.getSelectedUserStory().getName());
        service.updateUserStorySelector();
      }
    }
  }

  @FXML
  public void initProjectSelector(MouseEvent mouseEvent) {
    projectSelector.getItems().clear();
    projectSelector.getItems().addAll(service.getProjectNameList());
  }

  @FXML
  public void initUserStorySelector(MouseEvent mouseEvent) {
    userStorySelector.getItems().clear();
    String selectedProjectName = projectSelector.getSelectionModel().getSelectedItem();
    if (selectedProjectName != null) {
      userStorySelector.getItems().addAll(service.getUserStoryNameList(selectedProjectName));
    } else {
      userStorySelector.getItems().addAll(service.getUserStoryNameList());
    }
  }

}
