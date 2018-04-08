package app.view;

import app.controller.IssueApiController;
import app.controller.ProjectApiController;
import app.controller.UserStoryApiController;
import app.model.Issue;
import app.model.dto.ProjectSelectorDto;
import app.model.dto.UserStorySelectorDto;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SwimLaneController {
  private List<UserStorySelectorDto> userStories;
  private List<ProjectSelectorDto> projects;
  private IssueApiController issueApiController = new IssueApiController();
  private ProjectApiController projectApiController = new ProjectApiController();
  private UserStoryApiController userStoryApiController = new UserStoryApiController();
  // Table objects
  @FXML
  private TableView<Issue> openTable;
  @FXML
  private TableColumn<Issue, String> openColumn;
  @FXML
  private TableView<Issue> inProgressTable;
  @FXML
  private TableColumn<Issue, String> inProgressColumn;
  @FXML
  private TableView<Issue> reopenTable;
  @FXML
  private TableColumn<Issue, String> reopenColumn;
  @FXML
  private TableView<Issue> fixedTable;
  @FXML
  private TableColumn<Issue, String> fixedColumn;
  @FXML
  private TableView<Issue> verifiedTable;
  @FXML
  private TableColumn<Issue, String> verifiedColumn;

  // Menu bar
  @FXML
  private Menu userStoryMenu;
  @FXML
  private Menu sprintMenu;

  // Selectors
  @FXML
  private ComboBox<String> projectSelector;
  @FXML
  private ComboBox<String> userStorySelector;

  // Buttons
  @FXML
  private Button startButton;

  // Info
  @FXML
  private Label sprintNameLabel;
  @FXML
  private Label uptimeLabel;

  @FXML
  @SuppressWarnings("unchecked")
  private void initTables() {
    String userStoryName = userStorySelector.getSelectionModel().getSelectedItem();

    if (userStoryName != null) {
      ObservableList<Issue> openList = FXCollections.observableArrayList();
      openList.addAll(issueApiController.getAll(userStoryName, 1));
      openTable.setItems(openList);
      openColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));


      ObservableList<Issue> inProgressList = FXCollections.observableArrayList();
      inProgressList.addAll(issueApiController.getAll(userStoryName, 2));
      inProgressTable.setItems(inProgressList);
      inProgressColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
          cellData.getValue().getName()));

      ObservableList<Issue> reopenList = FXCollections.observableArrayList();
      reopenList.addAll(issueApiController.getAll(userStoryName, 3));
      reopenTable.setItems(reopenList);
      reopenColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
          cellData.getValue().getName()));

      ObservableList<Issue> fixedList = FXCollections.observableArrayList();
      fixedList.addAll(issueApiController.getAll(userStoryName, 4));
      fixedTable.setItems(fixedList);
      fixedColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
          cellData.getValue().getName()));

      ObservableList<Issue> verifyList = FXCollections.observableArrayList();
      verifyList.addAll(issueApiController.getAll(userStoryName, 5));
      verifiedTable.setItems(verifyList);
      verifiedColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
          cellData.getValue().getName()));
    }
  }


  @FXML
  public void initialize() {
    projectSelector.getItems().clear();
    userStorySelector.getItems().clear();
    initProjectSelector();
    initUserStorySelector();
  }

  @FXML
  public void initProjectSelector() {
    projectSelector.getItems().clear();

    this.projects = projectApiController.getProjectSelectorDtos();
    if (projects == null) {
      sprintMenu.setDisable(true);
      userStoryMenu.setDisable(true);
      return;
    }
    for (ProjectSelectorDto project : projects) {
      projectSelector.getItems().add(project.getName());
    }

    sprintMenu.setDisable(false);
    userStoryMenu.setDisable(false);
  }

  @FXML
  public void initUserStorySelector() {
    String name = projectSelector.getSelectionModel().getSelectedItem();
    userStorySelector.getItems().clear();
    this.userStories = userStoryApiController.getUserStorySelectorDtoList();

    if (name != null) {
      this.userStories = userStoryApiController
          .getUserStorySelectorDtoListByProjectId(getProjectId(name));
    }

    if (userStories != null) {
      for (UserStorySelectorDto userStory : userStories) {
        userStorySelector.getItems().add(userStory.getName());
      }
    }
  }

  private UUID getProjectId(String projectName) {
    UUID res = null;
    for (ProjectSelectorDto project : projects) {
      if (project.getName().equals(projectName)) {
        res = project.getId();
      }
    }
    return res;
  }

  private void showDialogWindow(String fxmlPath, String title) {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
      Parent root = fxmlLoader.load();
      Stage stage = new Stage();
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setTitle(title);
      stage.setScene(new Scene(root));
      stage.setResizable(false);
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  public void startNewProject() {
    showDialogWindow("fxml/NewProject.fxml", "Start new project");
  }

  @FXML
  public void editProject() {
    showDialogWindow("fxml/EditProject.fxml", "Edit project");
    initProjectSelector();
  }

  @FXML
  public void createUserStory() {
    showDialogWindow("fxml/NewUserStory.fxml", "New User story");
  }

  @FXML
  public void editUserStory() {
    showDialogWindow("fxml/EditUserStory.fxml", "Edit User story");
  }

  @FXML
  public void createSprint() {
    showDialogWindow("fxml/NewSprint.fxml", "New Sprint");
  }

  @FXML
  public void editSprint() {
    showDialogWindow("fxml/EditSprint.fxml", "Edit Sprint");
  }
}
