package net.toastynetworks.MCLAdmin.UI.Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.toastynetworks.MCLAdmin.BLL.Interfaces.IConfigLogic;
import net.toastynetworks.MCLAdmin.Factory.ConfigFactory;

import java.util.HashMap;

public class Main extends Application {
//    public static HashMap<String, String> localWorkSpaces = new HashMap<String, String>();
private IConfigLogic configLogic = ConfigFactory.CreateLogic();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/main.fxml"));
        primaryStage.setTitle("MCL-Admin");
        primaryStage.setScene(new Scene(root,  1800, 1000));
        primaryStage.show();
        configLogic.CreateConfig();
    }

    public static void main(String[] args) {
        launch(args);
    }

}