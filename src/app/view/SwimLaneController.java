package app.view;

import app.controller.ProjectApiController;
import app.controller.UserStoryApiController;
import app.model.Project;
import app.model.UserStory;
import app.util.Util;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class SwimLaneController {
    private ProjectApiController projectApiController = new ProjectApiController();
    private UserStoryApiController userStoryApiController = new UserStoryApiController();
    @FXML
    private Button startButton;
    @FXML
    private Label sprintNameLabel;
    @FXML
    private Label uptimeLabel;
    @FXML
    private ComboBox<String> projectSelector;
    @FXML
    private ComboBox<String> userStorySelector;

    @FXML
    public void initialize() {
        projectSelector.getItems().clear();
        userStorySelector.getItems().clear();
        ArrayList<Project> projects = projectApiController.getAll(Util.getCurrentUser().getCompany().getName());
        if (projects == null) {
            return;
        }
        ArrayList<UserStory> userStories = new ArrayList<>();
        for (Project project : projects) {
            ArrayList<UserStory> local = userStoryApiController.getAll(project.getName());
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
    public void startNewProject() {
        try {
            Stage stage;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/NewProject.fxml"));
            Parent root1 = fxmlLoader.load();
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Start new project");
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void editProject() {
        try {
            Stage stage;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/EditProject.fxml"));
            Parent root1 = fxmlLoader.load();
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Edit project");
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void createUserStory() {
        try {
            Stage stage;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/NewUserStory.fxml"));
            Parent root1 = fxmlLoader.load();
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("New User story");
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void editUserStory() {
        try {
            Stage stage;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/EditUserStory.fxml"));
            Parent root1 = fxmlLoader.load();
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Edit User story");
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void createSprint() {
        try {
            Stage stage;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/NewSprint.fxml"));
            Parent root1 = fxmlLoader.load();
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("New Sprint");
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void editSprint() {
        try {
            Stage stage;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/EditSprint.fxml"));
            Parent root1 = fxmlLoader.load();
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Edit Sprint");
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
