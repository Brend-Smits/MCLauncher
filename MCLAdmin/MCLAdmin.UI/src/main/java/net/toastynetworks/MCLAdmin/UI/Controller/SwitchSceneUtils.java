package net.toastynetworks.MCLAdmin.UI.Controller;

import com.sun.javafx.stage.StageHelper;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SwitchSceneUtils {
    public SwitchSceneUtils(Button button, String newScene) throws Exception {
        Stage stage;

        stage = (Stage) button.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(newScene));
        Scene scene = new Scene(root, 1600, 900);
        stage.setScene(scene);

        stage.show();
    }
    public SwitchSceneUtils(String newScene) throws Exception {
        for (Stage stage :
                StageHelper.getStages()) {
            Platform.runLater(() -> stage.close());
        }
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(newScene));
        Scene scene = new Scene(root, 1600, 900);
        stage.setScene(scene);

        stage.show();
    }
}
