package app.board.controller;

import app.board.model.dto.IssueSummary;
import app.board.service.ProjectService;
import app.board.service.SprintService;
import app.board.service.StateService;
import app.board.service.UserStoryService;
import app.util.Util;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import javafx.event.ActionEvent;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SwimLaneController {


  private SprintService sprintService = new SprintService();
  private StateService stateService = new StateService();
  private UserStoryService userStoryService = new UserStoryService();
  private ProjectService projectService = new ProjectService();


  // Table objects
  @FXML
  private GridPane tableLayout;
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
  private void initTables() {
    String projectName = projectSelector.getSelectionModel().getSelectedItem();
    if (projectName != null) {
      UUID currentProjectId = projectService.getIdByName(projectName);
      Util.setCurrentProjectId(currentProjectId);
      List<String> columns = stateService.getStateNames(currentProjectId);
      if (columns.isEmpty()) {
        createState();
      }
      initColumns(columns);
    }
  }

  @SuppressWarnings("unchecked")
  private void initColumns(List<String> columns) {

    tableLayout.getChildren().clear();

    for (int i = 0; i < columns.size(); i++) {
      TableView<IssueSummary> tableView = new TableView();
      tableView.addEventHandler(MouseEvent.MOUSE_CLICKED, this::tableClick);
      tableView.getSelectionModel().selectedItemProperty()
          .addListener((observable, oldValue, newValue) -> {
            Util.setSelectedIssue(oldValue.getId());
          });

      tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
      tableView.getColumns().add(new TableColumn<>(columns.get(i)));

      tableLayout.addColumn(i, tableView);
    }
  }

  @SuppressWarnings("unchecked")
  private void populateColumns(TableView tableView) {

  }

  private List<List<String>> getData() {
//    List<List<String>> res = new ArrayList<>();
//    int tableSize = table.getColumns().size();
//
//    for (int i = 0; i < tableSize; i++) {
//      int dataSize = 20;
//      ArrayList<String> list = new ArrayList<>();
//      for (int j = 0; j < dataSize; j++) {
//        list.add("DATA " + i + " " + j);
//      }
//      res.add(list);
//    }
//    return res;
    return Collections.emptyList();
  }

  @FXML
  @SuppressWarnings("unchecked")
  public void initialize() {
    initProjectSelector();
    initUserStorySelector();
  }

  @FXML
  public void initProjectSelector() {
    projectSelector.getItems().clear();

    List<String> projects = projectService.getProjectNames();
    if (projects.isEmpty()) {
      sprintMenu.setDisable(true);
      userStoryMenu.setDisable(true);
      return;
    }
    projectSelector.getItems().addAll(projects);

    sprintMenu.setDisable(false);
    userStoryMenu.setDisable(false);
  }

  @FXML
  public void initUserStorySelector() {
    String selectedProjectName = projectSelector.getSelectionModel().getSelectedItem();
    userStorySelector.getItems().clear();
    List<String> userStories;

    if (selectedProjectName != null) {
      userStories = userStoryService.getUserStoryNameList(selectedProjectName);
    } else {
      userStories = userStoryService.getUserStoryNameList();
    }
    userStorySelector.getItems().addAll(userStories);
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

  @FXML
  public void updateUserStorySelector(ActionEvent actionEvent) {
    userStorySelector.getItems().clear();
    String ptojectName = projectSelector.getSelectionModel().getSelectedItem();
    if (ptojectName != null) {
      userStorySelector.getItems().addAll(userStoryService.getUserStoryNameList(ptojectName));
    }
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

  private void tableClick(MouseEvent mouseEvent) {

    int clickCount = mouseEvent.getClickCount();

    if (clickCount == 1) {
      showDialogWindow("fxml/Issue.fxml", "Create/Edit Issue");
    } else if (clickCount == 2) {
      createState();
    }
    initColumns(stateService.getStateNames(Util.getCurrentProjectId()));
  }

  @FXML
  private void createState() {
    showDialogWindow("fxml/EditTable.fxml", "Update state list");
  }

  @FXML
  public void selectUserStory(ActionEvent actionEvent) {
    String selectedName = userStorySelector.getSelectionModel().getSelectedItem();
    if (selectedName != null) {
      UUID projectId = userStoryService.getProjectIdByUserStoryName(selectedName);
      Util.setCurrentProjectId(projectId);
      projectSelector.getSelectionModel().select(projectService.getNameById(projectId));
      initTables();
    }
  }
}
