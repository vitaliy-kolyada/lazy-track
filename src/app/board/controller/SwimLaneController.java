package app.board.controller;

import app.board.model.dto.IssueSummary;
import app.board.model.dto.TableStateDto;
import app.board.model.enumeration.Severity;
import app.board.service.IssueService;
import app.board.service.ProjectService;
import app.board.service.SprintService;
import app.board.service.StateService;
import app.board.service.UserStoryService;
import app.util.Context;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class SwimLaneController {


  private SprintService sprintService = new SprintService();
  private StateService stateService = new StateService();
  private UserStoryService userStoryService = new UserStoryService();
  private ProjectService projectService = new ProjectService();
  private IssueService issueService = new IssueService();

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
  @SuppressWarnings("unchecked")
  public void initialize() {
    initProjectSelector();
    initUserStorySelector();
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
  public void selectUserStory(ActionEvent actionEvent) {
    String selectedName = userStorySelector.getSelectionModel().getSelectedItem();
    if (selectedName != null) {
      UUID projectId = userStoryService.getProjectIdByUserStoryName(selectedName);
      Context.setCurrentProjectId(projectId);

      UUID userStoryId = userStoryService
          .getUserStoryIdByName(userStorySelector.getSelectionModel().getSelectedItem());
      Context.setSelectedUserStoryId(userStoryId);

      projectSelector.getSelectionModel().select(projectService.getNameById(projectId));
      initTables();
    }
  }

  @FXML
  private void initTables() {
    String projectName = projectSelector.getSelectionModel().getSelectedItem();

    updateUserStorySelector();
    if (projectName != null) {
      UUID currentProjectId = projectService.getIdByName(projectName);
      Context.setCurrentProjectId(currentProjectId);
      List<TableStateDto> columns = stateService.getStates(currentProjectId);
      if (columns.isEmpty()) {
        createState();
      }

      if (Context.getSelectedUserStoryId() != null) {
        initColumns(columns);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private void initColumns(List<TableStateDto> states) {

    tableLayout.getChildren().clear();
    tableLayout.getColumnConstraints().clear();
    Map<UUID, List<IssueSummary>> issuesMap = issueService.getIssueMap(Context.getSelectedUserStoryId());

    for (int i = 0; i < states.size(); i++) {

      TableStateDto stateDto = states.get(i);

      TableView<IssueSummary> tableView = new TableView<>();
      tableView.autosize();
      tableView.addEventHandler(MouseEvent.MOUSE_CLICKED, this::tableClick);

      tableView.getSelectionModel().selectedItemProperty()
          .addListener((observable, oldValue, newValue) -> Context.setSelectedIssue(newValue.getId()));
      tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

      TableColumn<IssueSummary, String> column = new TableColumn<>(stateDto.getName());

      List<IssueSummary> issues = issuesMap.get(states.get(i).getStateId());

      column.setCellFactory(new Callback<TableColumn<IssueSummary, String>, TableCell<IssueSummary, String>>() {
        @Override
        public TableCell call(TableColumn param) {
          return new TableCell<IssueSummary, String>() {

            @Override
            public void updateItem(String item, boolean empty) {
              super.updateItem(item, empty);
              if (!isEmpty()) {
                Severity severity = issueService.getIssueSeverity(item, issues);
                if (severity != null)
                  switch (severity) {
                    case SHOW_STOPPER:
                      this.setTextFill(Color.rgb(139, 0, 0));
                      break;
                    case CRITICAL:
                      this.setTextFill(Color.rgb(255, 152, 0));
                      break;
                    case MAJOR:
                      this.setTextFill(Color.rgb(255, 230, 0));
                      break;
                    case NORMAL:
                      this.setTextFill(Color.rgb(34, 255, 0));
                      break;
                    case MINOR:
                      this.setTextFill(Color.rgb(0, 17, 143));
                      break;
                  }
                setText(item);
              }
            }
          };
        }
      });

      if (issues != null) {
        ObservableList<IssueSummary> issuesData =
            FXCollections.observableArrayList(issues);
        column.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableView.setItems(issuesData);
      }

      tableView.getColumns().add(column);
      tableLayout.addColumn(i, tableView);

      BigDecimal percentSize = BigDecimal.valueOf(100)
          .divide(BigDecimal.valueOf(states.size()), RoundingMode.UP);
      ColumnConstraints constraints = new ColumnConstraints();
      constraints.setPercentWidth(percentSize.doubleValue());
      tableLayout.getColumnConstraints().add(constraints);
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

  public void updateUserStorySelector() {
    userStorySelector.getItems().clear();
    String ptojectName = projectSelector.getSelectionModel().getSelectedItem();
    if (ptojectName != null) {
      userStorySelector.getItems().addAll(userStoryService.getUserStoryNameList(ptojectName));
    }
  }


  private void tableClick(MouseEvent mouseEvent) {

    int clickCount = mouseEvent.getClickCount();

    if (clickCount == 1) {
      showDialogWindow("fxml/Issue.fxml", "Create/Edit Issue");
    } else if (clickCount == 2) {
      createState();
    }
    initColumns(stateService.getStates(Context.getCurrentProjectId()));
  }

  @FXML
  private void createState() {
    showDialogWindow("fxml/EditTable.fxml", "Update state list");
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
}
