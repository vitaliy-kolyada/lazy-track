package app.view;

import app.controller.ProjectApiController;
import app.controller.UserStoryApiController;
import app.model.Project;
import app.model.UserStory;
import app.util.Util;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class NewUserStoryController {
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
        ArrayList<Project> projects = projectApiController.getAll(Util.getCurrentUser().getCompany().getName());
        if (projects == null) {
            return;
        }
        for (Project project : projects) {
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
        if (userStoryApiController.create(userStory)) {
            errorLabel.setText("Created " + userStory.getName());
        } else {
            errorLabel.setText("Name must be unique");
        }
    }
}
