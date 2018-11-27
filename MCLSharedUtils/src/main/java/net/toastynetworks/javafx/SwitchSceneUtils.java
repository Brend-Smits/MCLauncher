package net.toastynetworks.javafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SwitchSceneUtils {
    public SwitchSceneUtils(Button button, String newScene) throws Exception {
        Stage stage;
        Parent root;

        stage = (Stage) button.getScene().getWindow();
        root = FXMLLoader.load(getClass().getClassLoader().getResource(newScene));
        Scene scene = new Scene(root, 1600, 900);
        stage.setScene(scene);

        stage.show();
    }
}
