package app.board.controller;

import app.board.model.CreateIssueDto;
import app.board.model.enumeration.Priority;
import app.board.model.enumeration.Severity;
import app.board.service.IssueService;
import app.board.service.StateService;
import app.util.Util;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class IssueContoller {

  private final IssueService issueService = new IssueService();
  private final StateService stateService = new StateService();
  @FXML
  private TextArea newDescriptionArea;
  @FXML
  private Tab createTab;
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
  private Tab editTab;

  @FXML
  @SuppressWarnings("unchecked")
  public void initialize() {
    newSeverityComboBox.getItems().clear();
    newSeverityComboBox.getItems().addAll(Severity.names());

    newPriorityComboBox.getItems().clear();
    newPriorityComboBox.getItems().addAll(Priority.names());

    newStateComboBox.getItems().clear();
    newStateComboBox.getItems().addAll(stateService.getStateNames(Util.getCurrentProjectId()));
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

    issueService.submitCreate(createIssueDto);
  }
}
