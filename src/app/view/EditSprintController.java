package app.view;

import app.controller.ProjectApiController;
import app.controller.SprintApiController;
import app.model.Project;
import app.model.Sprint;
import app.util.Util;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;

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
        projectSelector.getItems().clear();
        sprintSelector.getItems().clear();
        ArrayList<Project> projects = projectApiController.getAll(Util.getCurrentUser().getCompany().getName());
        if (projects == null) {
            return;
        }
        ArrayList<Sprint> sprints = new ArrayList<>();
        for (Project project : projects) {
            ArrayList<Sprint> local = sprintApiController.getAll(project.getName());
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
        nameField.setText(selectedSprint.getName());
        goalArea.setText(selectedSprint.getGoal());

        projectSelector.getSelectionModel().select(selectedSprint.getProject().getName());
        datePicker.setValue(selectedSprint.getDate());
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
        if (selectedSprint != null) {
            sprintApiController.remove(selectedSprint.getId());
            projectSelector.getItems().remove(selectedSprint.getName());
            nameField.setText("");
            goalArea.setText("");
        }
    }
}
