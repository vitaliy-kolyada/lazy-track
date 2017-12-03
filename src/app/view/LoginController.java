package app.view;

import app.controller.UserApiController;
import app.model.User;
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
    private User user;
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
        String login = loginField.getText();
        String password = passwordField.getText();
        user = apiController.getUser(login, password);
        if (user == null) {
            errorMessage.setVisible(true);
        }
    }

    @FXML
    public void handleSignIn() {
        try {
            //Close current
            Stage stage = (Stage) signInButton.getScene().getWindow();
            // do what you have to do
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
