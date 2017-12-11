package app.view;

import app.controller.CompanyApiController;
import app.controller.UserApiController;
import app.model.Company;
import app.model.User;
import app.util.Util;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Pattern;

public class RegisterController {
    private UserApiController userApiController = new UserApiController();
    private CompanyApiController companyApiController = new CompanyApiController();
    @FXML
    private TextField loginField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField companyNameField;
    @FXML
    private TextField descriptionField;
    @FXML
    private Label errorLabel;

    private boolean isValidPassword(String password) {
        String regex = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(password).matches();
    }

    private boolean isValidEMail(String eMail) {
        String regex = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(eMail).matches();

    }

    private boolean isValidPhone(String phone) {
        String regex = "^((8|\\+\\d)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(phone).matches();
    }

    private boolean areDefaultValues() {
        return loginField.getText().equals("login") ||
                passwordField.getText().equals("qwerty123") ||
                nameField.getText().equals("Johnson") ||
                emailField.getText().equals("LazyUser@gmail.com") ||
                phoneField.getText().equals("+380971234567") ||
                companyNameField.getText().equals("Evil corp.") ||
                descriptionField.getText().equals("Big angry corporation");
    }

    private boolean areEmptyFields() {
        return loginField.getText().equals("") ||
                passwordField.getText().equals("") ||
                nameField.getText().equals("") ||
                emailField.getText().equals("") ||
                phoneField.getText().equals("") ||
                companyNameField.getText().equals("") ||
                descriptionField.getText().equals("");
    }

    private boolean startsWithSpace() {
        return loginField.getText().startsWith(" ") ||
                nameField.getText().startsWith(" ") ||
                companyNameField.getText().startsWith(" ") ||
                descriptionField.getText().startsWith(" ");
    }

    private void initErrorMessage() {
        errorLabel.setText("");
        if (areEmptyFields()) {
            errorLabel.setText("All fields are required");
        } else if (!isValidPassword(passwordField.getText())) {
            errorLabel.setText("Password must contain 8 chars and numbers");
        } else if (!isValidEMail(emailField.getText())) {
            errorLabel.setText("Not valid e-mail");
        } else if (!isValidPhone(phoneField.getText())) {
            errorLabel.setText("Not valid phone format");
        } else if (areDefaultValues()) {
            errorLabel.setText("You must have your own opinion");
        } else if (startsWithSpace()) {
            errorLabel.setText("Fields can not start with space");
        }
    }

    private User initUser() {
        User user = new User();
        user.setResponsibilities(1);
        user.setLogin(loginField.getText());
        user.setPassword(passwordField.getText());
        user.seteMail(emailField.getText());
        user.setPhone(phoneField.getText());
        return user;
    }

    private Company initCompany() {
        Company company = new Company();
        company.setName(companyNameField.getText());
        company.setDescription(companyNameField.getText());
        return company;
    }

    @FXML
    public void handleSubmit() {
        initErrorMessage();
        if (!errorLabel.getText().equals(""))
            return;
        User user = initUser();
        Company company = initCompany();

        if (companyApiController.create(company)) {
            user.setPosition("CEO " + company.getName());
            user.setCompany(company);
            if (userApiController.register(user)) {
                errorLabel.setText("Ok");
                Util.setCurrentUser(user);
                try {
                    Stage stage = (Stage) errorLabel.getScene().getWindow();
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
            } else {
                errorLabel.setText("This login is busy");
            }
        } else {
            errorLabel.setText("This company already exists");
        }
    }
}
