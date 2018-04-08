package app.view;

import app.controller.UserApiController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController extends MainController {

  private final static String SWIMLANE_RESOURCE = "fxml/SwimLane.fxml";
  private final static String REGISTER_FORM = "fxml/RegisterForm.fxml";

  private UserApiController apiController = new UserApiController();
  @FXML
  private TextField loginField;
  @FXML
  private PasswordField passwordField;
  @FXML
  private Button loginButton;
  @FXML
  private Button signInButton;
  @FXML
  private Label errorMessage;


  @FXML
  public void initialize() {
    loginField.setText("login");
    passwordField.setText("Password1!");
  }

  @FXML
  public void handleLogin() {
    if (emptyFields()) {
      errorMessage.setText("Fields are empty");
      return;
    }
    apiController.login(loginField.getText(), passwordField.getText());
    buildNewWindow(loginButton.getScene(), "Lazy track", SWIMLANE_RESOURCE);
  }

  private boolean emptyFields() {
    errorMessage.setText("");
    return loginField.getText().equals("") || passwordField.getText().equals("");
  }

  @FXML
  public void handleSignIn() {
    buildNewWindow(signInButton.getScene(), "Register", REGISTER_FORM);
  }
}
