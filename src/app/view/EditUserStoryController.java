package app.view;

import app.controller.ProjectApiController;
import app.controller.UserStoryApiController;
import app.model.Project;
import app.model.UserStory;
import app.util.Util;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.Optional;

public class EditUserStoryController {
    private UserStory selectedUserStory;
    private UserStoryApiController userStoryApiController = new UserStoryApiController();
    private ProjectApiController projectApiController = new ProjectApiController();

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

    @FXML
    @SuppressWarnings("unchecked")
    public void initialize() {
        updateSelector();
    }

    private void updateSelector() {
        projectSelector.getItems().clear();
        userStorySelector.getItems().clear();
        ArrayList<Project> projects = projectApiController.getAll(Util.getCurrentUser().getCompany().getName());
        if (projects == null) {
            return;
        }
        ArrayList<UserStory> userStories = new ArrayList<>();
        for (Project project : projects) {
            ArrayList<UserStory> local = userStoryApiController.getAll(project.getName());
            if (local == null) {
                return;
            }
            userStories.addAll(local);
        }

        for (Project project : projects) {
            projectSelector.getItems().add(project.getName());
        }
        for (UserStory userStory : userStories) {
            userStorySelector.getItems().add(userStory.getName());
        }
    }

    @FXML
    public void select() {
        selectedUserStory = userStoryApiController.get(userStorySelector.getSelectionModel().getSelectedItem());
        if (selectedUserStory != null) {
            nameField.setText(selectedUserStory.getName());
            descriptionArea.setText(selectedUserStory.getDescription());

            projectSelector.getSelectionModel().select(selectedUserStory.getProject().getName());
        }
    }

    @FXML
    public void handleSubmit() {
        errorLabel.setText("");
        if (selectedUserStory == null) {
            errorLabel.setText("Select user story");
            return;
        }
        if (nameField.getText().equals("")) {
            errorLabel.setText("User story name is required");
            return;
        }
        Project project = projectApiController.get(projectSelector.getSelectionModel().getSelectedItem());
        UserStory userStory = new UserStory(nameField.getText(), descriptionArea.getText(), project);
        if (userStoryApiController.update(selectedUserStory.getId(), userStory)) {
            errorLabel.setText("Successfully updated \"" + userStory.getName() + "\"");
        } else {
            errorLabel.setText("User story name must be unique");
        }
    }

    @FXML
    public void handleDelete() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm deleting user story");
        alert.setContentText("Do you really want to delete user story \"" + selectedUserStory.getName() + "\" ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            if (selectedUserStory != null) {
                projectApiController.remove(selectedUserStory.getId());
                projectSelector.getItems().remove(selectedUserStory);
                nameField.setText("");
                descriptionArea.setText("");
                errorLabel.setText("Removed " + selectedUserStory.getName());

            }
        }
        updateSelector();
    }
}
