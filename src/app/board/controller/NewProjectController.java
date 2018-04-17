package app.board.controller;

import app.controller.ProjectApiController;
import app.board.model.dto.CreatePtojectRequest;
import app.model.enumeration.LabelColor;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class NewProjectController {
  private ProjectApiController projectApiController = new ProjectApiController();
  @FXML
  private Label errorLabel;
  @FXML
  private TextField nameField;
  @FXML
  private TextArea descriptionArea;

  @FXML
  public void handleSubmit() {
    errorLabel.setText("");
//    descriptionArea.setText("");
    if (nameField.getText().equals("") || descriptionArea.getText().equals("")) {
      errorLabel.setTextFill(Color.web(LabelColor.ERROR.getCode()));
      errorLabel.setText("You must fill all fields");
      return;
    }
    CreatePtojectRequest request =
        new CreatePtojectRequest(nameField.getText(), descriptionArea.getText());
    boolean created = projectApiController.create(request);
    if (created) {
      errorLabel.setTextFill(Color.web(LabelColor.OK.getCode()));
      errorLabel.setText("Project " + nameField.getText() + " successfully created.");
    }
  }
}
