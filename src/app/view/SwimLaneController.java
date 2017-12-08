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
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class SwimLaneController {
    @FXML
    public ImageView background;
    @FXML
    private ComboBox<String> projectSelector;
    @FXML
    private ComboBox<String> userStorySelector;
    private ProjectApiController projectApiController = new ProjectApiController();
    private UserStoryApiController userStoryApiController = new UserStoryApiController();
    @FXML
    private Button startButton;
    @FXML
    private Label sprintNameLabel;
    @FXML
    private Label uptimeLabel;

    @FXML
    public void initialize() {
        projectSelector.getItems().clear();
        userStorySelector.getItems().clear();
        initProjectSelector();
    }

    @FXML
    public void initProjectSelector() {
        projectSelector.getItems().clear();

        ArrayList<Project> projects = projectApiController.getAll(Util.getCurrentUser().getCompany().getName());
        if (projects == null) {
            return;
        }
        for (Project project : projects) {
            projectSelector.getItems().add(project.getName());
        }
    }
    //Can help  https://toster.ru/q/247642

    @FXML
    public void initUserStorySelector() {
        String name = projectSelector.getSelectionModel().getSelectedItem();
        userStorySelector.getItems().clear();
        ArrayList<UserStory> userStories = userStoryApiController.getAll(name);
        if (userStories != null) {
            for (UserStory userStory : userStories) {
                userStorySelector.getItems().add(userStory.getName());
            }
        }

    }

    @FXML
    public void startNewProject() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/NewProject.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Start new project");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
            initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void editProject() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/EditProject.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Edit project");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void createUserStory() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/NewUserStory.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("New User story");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void editUserStory() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/EditUserStory.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Edit User story");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void createSprint() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/NewSprint.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("New Sprint");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void editSprint() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/EditSprint.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Edit Sprint");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
