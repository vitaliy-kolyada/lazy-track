package app.board.controller;

import app.board.service.ProjectService;
import app.board.service.SprintService;
import app.board.service.UserStoryService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javafx.collections.ObservableList;
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
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class SwimLaneController {
  private SprintService sprintService = new SprintService();
  private UserStoryService userStoryService = new UserStoryService();
  private ProjectService projectService = new ProjectService();
  // Table objects
  @FXML
  private TableView table;
//  @FXML
//  private TableView<ObservableList> openTable;
//  @FXML
//  private TableColumn<Issue, String> openColumn;
//  @FXML
//  private TableView<Issue> inProgressTable;
//  @FXML
//  private TableColumn<Issue, String> inProgressColumn;
//  @FXML
//  private TableView<Issue> reopenTable;
//  @FXML
//  private TableColumn<Issue, String> reopenColumn;
//  @FXML
//  private TableView<Issue> fixedTable;
//  @FXML
//  private TableColumn<Issue, String> fixedColumn;
//  @FXML
//  private TableView<Issue> verifiedTable;
//  @FXML
//  private TableColumn<Issue, String> verifiedColumn;

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
    if (projectSelector.getSelectionModel().getSelectedItem() == null
        && userStorySelector.getSelectionModel().getSelectedItem() != null) {
      String userStoryName = userStorySelector.getSelectionModel().getSelectedItem();
      UUID projectId = userStoryService.getProjectIdByUserStoryName(userStoryName);
      projectSelector.getSelectionModel().select(projectService.getNameById(projectId));
    }

//    if (userStoryName != null) {
//      ObservableList<Issue> openList = FXCollections.observableArrayList();
//      openList.addAll(issueApiController.getAll(userStoryName, 1));
//      openTable.setItems(openList);
//      openColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));


//    ObservableList<Issue> inProgressList = FXCollections.observableArrayList();
//    inProgressList.addAll(issueApiController.getAll(userStoryName, 2));
//    inProgressTable.setItems(inProgressList);
//    inProgressColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
//        cellData.getValue().getName()));
//
//    ObservableList<Issue> reopenList = FXCollections.observableArrayList();
//    reopenList.addAll(issueApiController.getAll(userStoryName, 3));
//    reopenTable.setItems(reopenList);
//    reopenColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
//        cellData.getValue().getName()));
//
//    ObservableList<Issue> fixedList = FXCollections.observableArrayList();
//    fixedList.addAll(issueApiController.getAll(userStoryName, 4));
//    fixedTable.setItems(fixedList);
//    fixedColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
//        cellData.getValue().getName()));
//
//    ObservableList<Issue> verifyList = FXCollections.observableArrayList();
//    verifyList.addAll(issueApiController.getAll(userStoryName, 5));
//    verifiedTable.setItems(verifyList);
//    verifiedColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
//        cellData.getValue().getName()));
  }


  @FXML
  @SuppressWarnings("unchecked")
  public void initialize() {

    List<String> columns = new ArrayList<>();
    columns.add("Open");
    columns.add("In progress");
    columns.add("To verify");
    columns.add("Reopen");
    columns.add("Done");

    TableColumn[] tableColumns = new TableColumn[columns.size()];
    int columnIndex = 0;
    for (String columName : columns) {
      tableColumns[columnIndex++] = new TableColumn(columName);
    }

    Screen screen = Screen.getPrimary();
//    double tableWidth = screen.getBounds().getHeight();
//
//    for (TableColumn tableColumn : tableColumns) {
//      tableColumn.setMinWidth(tableWidth / tableColumns.length);
//    }
    table.getColumns().addAll(tableColumns);

    ObservableList<TableColumn<String, String>> tableColumnList = table.getColumns();

    projectSelector.getItems().clear();
    userStorySelector.getItems().clear();
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

  @FXML
  public void updateUserStorySelector(ActionEvent actionEvent) {
    userStorySelector.getItems().clear();
    String ptojectName = projectSelector.getSelectionModel().getSelectedItem();
    if (ptojectName != null) {
      userStorySelector.getItems().addAll(userStoryService.getUserStoryNameList(ptojectName));
    }
  }

  @FXML
  public void tableClick(MouseEvent mouseEvent) {
    if (mouseEvent.getClickCount() == 2) {
      createState();
    }
  }

  @FXML
  private void createState() {
    showDialogWindow("fxml/EditTable.fxml", "Update state list");
  }
}
