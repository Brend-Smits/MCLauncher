package net.toastynetworks.MCLAdmin.UI.Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.toastynetworks.MCLAdmin.BLL.Interfaces.IConfigLogic;
import net.toastynetworks.MCLAdmin.Factory.ConfigFactory;

import java.net.URL;

public class Main extends Application {
private IConfigLogic configLogic = ConfigFactory.CreateLogic();

    @Override
    public void start(Stage primaryStage) throws Exception{
        URL fileLocation;
        if (configLogic.GetWorkSpaceFromConfig() != null) {
            fileLocation = getClass().getClassLoader().getResource("fxml/main.fxml");
        } else {
            fileLocation = getClass().getClassLoader().getResource("fxml/SelectWorkspaceScene.fxml");
        }
        Parent root = FXMLLoader.load(fileLocation);
        primaryStage.setTitle("MCL-Admin");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}