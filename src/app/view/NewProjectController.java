package app.view;

import app.controller.ProjectApiController;
import app.model.Project;
import app.util.Util;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
        if (nameField.getText().equals("")) {
            errorLabel.setText("Project name is required");
        }
        Project project = new Project(Util.getCurrentUser().getCompany(), nameField.getText(), descriptionArea.getText());
        if (projectApiController.create(project)) {
            errorLabel.setText("Successfully created \"" + project.getName() + "\"");
        } else {
            errorLabel.setText("Project name must be unique");
        }
    }
}
