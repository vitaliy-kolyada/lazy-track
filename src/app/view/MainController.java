package app.view;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController {
  protected void buildNewWindow(Scene scene, String title, String resourcePath) {
    try {
      Stage stage = (Stage) scene.getWindow();
      stage.close();
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(resourcePath));
      Parent root1 = fxmlLoader.load();
      stage = new Stage();
      stage.initModality(Modality.NONE);
      stage.setTitle(title);
      stage.setScene(new Scene(root1));
      stage.setResizable(true);
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}