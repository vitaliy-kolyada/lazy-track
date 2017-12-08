package app.view;

import app.controller.ProjectApiController;
import app.controller.SprintApiController;
import app.model.Project;
import app.model.Sprint;
import app.util.Util;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class NewSprintController {
    private ProjectApiController projectApiController = new ProjectApiController();
    private SprintApiController sprintApiController = new SprintApiController();
    @FXML
    private TextField nameField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ComboBox<String> projectSelector;
    @FXML
    private TextArea goalArea;
    @FXML
    private Label errorLabel;

    @FXML
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
        if (datePicker.getValue() == null) {
            errorLabel.setText("Choose deadline date");
            return;
        }
        if (datePicker.getValue().isBefore(LocalDate.now())) {
            errorLabel.setText("You can not set past date");
            return;
        }
        Project project = projectApiController.get(projectSelector.getSelectionModel().getSelectedItem());
        Sprint sprint = new Sprint(nameField.getText(), goalArea.getText(), project, datePicker.getValue());
        if (sprintApiController.create(sprint)) {
            errorLabel.setText("Created " + sprint.getName());
        } else {
            errorLabel.setText("Name must be unique");
        }
    }
}
