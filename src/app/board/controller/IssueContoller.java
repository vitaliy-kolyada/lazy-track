package app.board.controller;

import app.board.model.CreateIssueDto;
import app.board.model.dto.EditIssueDto;
import app.board.model.enumeration.Priority;
import app.board.model.enumeration.Severity;
import app.board.service.IssueService;
import app.board.service.StateService;
import app.util.Context;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class IssueContoller {

  private final IssueService issueService = new IssueService();
  private final StateService stateService = new StateService();

  private EditIssueDto editIssueDtoContext;

  @FXML
  private TabPane tabPane;

  @FXML
  private Tab editTab;
  @FXML
  private TextField editNameField;
  @FXML
  private ComboBox<String> editSeverityComboBox;
  @FXML
  private ComboBox<String> editPriorityComboBox;
  @FXML
  private ComboBox<String> editStateComboBox;
  @FXML
  private ComboBox editUserComboBox;
  @FXML
  private TextArea editDescriptionArea;
  @FXML
  private Button editButton;
  @FXML
  private Button deleteButton;


  @FXML
  private Tab createTab;
  @FXML
  private TextArea newDescriptionArea;
  @FXML
  private TextField newNameField;
  @FXML
  private ComboBox<String> newSeverityComboBox;
  @FXML
  private ComboBox<String> newPriorityComboBox;
  @FXML
  private ComboBox<String> newStateComboBox;
  @FXML
  private ComboBox newUserComboBox;
  @FXML
  private Button createButton;


  @FXML
  @SuppressWarnings("unchecked")
  public void initialize() {
    initNewTab();

    SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();

    if (Context.getSelectedIssueId() == null) {
      editTab.setDisable(true);
      selectionModel.select(1);
    } else {
      initEditTab();
    }

    Context.setSelectedIssue(null);
  }

  private void initNewTab() {
    newSeverityComboBox.getItems().clear();
    newSeverityComboBox.getItems().addAll(Severity.names());

    newPriorityComboBox.getItems().clear();
    newPriorityComboBox.getItems().addAll(Priority.names());

    newStateComboBox.getItems().clear();
    newStateComboBox.getItems().addAll(stateService.getStateNames(Context.getCurrentProjectId()));
  }

  private void initEditTab() {
    this.editIssueDtoContext = issueService.getSelectedIssue();

    editNameField.setText(editIssueDtoContext.getName());
    editDescriptionArea.setText(editIssueDtoContext.getDescription());

    editSeverityComboBox.getItems().clear();
    editSeverityComboBox.getItems().addAll(Severity.names());
    editSeverityComboBox.getSelectionModel().select(editIssueDtoContext.getSeverity().getName());

    editPriorityComboBox.getItems().clear();
    editPriorityComboBox.getItems().addAll(Priority.names());
    editPriorityComboBox.getSelectionModel().select(editIssueDtoContext.getPriority().getName());

    editStateComboBox.getItems().clear();
    editStateComboBox.getItems().addAll(stateService.getStateNames(Context.getCurrentProjectId()));
    editStateComboBox.getSelectionModel().select(editIssueDtoContext.getStateName());

  }

  @FXML
  public void submitCreate(ActionEvent actionEvent) {
    CreateIssueDto createIssueDto = new CreateIssueDto();
    createIssueDto.setName(newNameField.getText());
    createIssueDto.setDescription(newDescriptionArea.getText());
    createIssueDto.setSeverity(
        Severity.of(newSeverityComboBox.getSelectionModel().getSelectedItem()));
    createIssueDto.setPriority(
        Priority.of(newPriorityComboBox.getSelectionModel().getSelectedItem()));
    createIssueDto.setStateId(
        stateService.getStateId(newStateComboBox.getSelectionModel().getSelectedItem()));

    boolean success = issueService.submitCreate(createIssueDto);
  }

  public void submitUpdate(ActionEvent actionEvent) {
    editIssueDtoContext.setName(editNameField.getText());
    editIssueDtoContext.setDescription(editDescriptionArea.getText());
    editIssueDtoContext.setName(editNameField.getText());

    editIssueDtoContext.setSeverity(
        Severity.of(editSeverityComboBox.getSelectionModel().getSelectedItem()));
    editIssueDtoContext.setPriority(
        Priority.of(editPriorityComboBox.getSelectionModel().getSelectedItem()));
    editIssueDtoContext.setStateId(
        stateService.getStateId(editStateComboBox.getSelectionModel().getSelectedItem()));

    boolean success = issueService.submitUpdate(editIssueDtoContext);
  }

  public void submitDelete(ActionEvent actionEvent) {

  }
}
