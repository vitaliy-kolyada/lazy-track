package app.view;

import app.controller.UserApiController;
import app.model.User;
import app.util.Util;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
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
    public void handleLogin() {
        errorMessage.setText("");
        if (loginField.getText().equals("") || passwordField.getText().equals("")) {
            errorMessage.setText("Fields are empty");
            return;
        }
        User user = apiController.getUser(loginField.getText(), passwordField.getText());
        if (user == null) {
            errorMessage.setText("Invalid login or password");
            return;
        }
        Util.setCurrentUser(user);
        try {
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/SwimLane.fxml"));
            Parent root1 = fxmlLoader.load();
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("LazyTrack");
            stage.setScene(new Scene(root1));
            stage.setResizable(true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleSignIn() {
        try {
            Stage stage = (Stage) signInButton.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/RegisterForm.fxml"));
            Parent root1 = fxmlLoader.load();
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Register");
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
