package app.view;

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

public class SwimLaneController {

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
            stage.setTitle("New User story");
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
