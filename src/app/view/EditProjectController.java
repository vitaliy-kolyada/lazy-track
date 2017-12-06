package app.view;

import app.controller.ProjectApiController;
import app.model.Project;
import app.util.Util;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class EditProjectController {
    private ProjectApiController projectApiController = new ProjectApiController();
    private Project selectedProject;
    @FXML
    private TextField nameField;
    @FXML
    private TextArea descriptionArea;
    @FXML
    private Label errorLabel;
    @FXML
    private ComboBox projectSelector;

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
    public void select() {
        selectedProject = projectApiController.get((String) projectSelector.getSelectionModel().getSelectedItem());
        nameField.setText(selectedProject.getName());
        descriptionArea.setText(selectedProject.getDescription());
    }

    @FXML
    public void handleSubmit() {
        if (selectedProject == null) {
            return;
        }
        if (nameField.getText().equals("")) {
            errorLabel.setText("Project name is required");
            return;
        }
        Project project = new Project(Util.getCurrentUser().getCompany(), nameField.getText(), descriptionArea.getText());
        if (projectApiController.update(selectedProject.getId(), project)) {
            errorLabel.setText("Successfully updated \"" + project.getName() + "\"");
        } else {
            errorLabel.setText("Project name must be unique");
        }
    }

    @FXML
    public void handleDelete() {
        if (selectedProject != null) {
            projectApiController.remove(selectedProject.getId());
            projectSelector.getItems().remove(selectedProject);
            nameField.setText("");
            descriptionArea.setText("");
        }
    }
}
