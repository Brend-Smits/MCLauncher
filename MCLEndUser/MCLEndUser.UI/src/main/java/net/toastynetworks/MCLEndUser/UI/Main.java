package net.toastynetworks.MCLEndUser.UI;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import net.toastynetworks.MCLEndUser.BLL.Interfaces.IModpackLogic;
import net.toastynetworks.MCLEndUser.Domain.Modpack;
import net.toastynetworks.MCLEndUser.Factory.ModpackFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class Main extends Application implements Initializable {
    @FXML
    private ListView modpackList;
    private List<Modpack> modpackLists;
    private ObservableList<String> items = FXCollections.observableArrayList();
    private IModpackLogic modpackLogic = ModpackFactory.CreateLogic();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("user-ui.fxml"));
        primaryStage.setTitle("ToastyNetworks Launcher V2");
        primaryStage.setScene(new Scene(root, 1920, 1080));
        primaryStage.show();
    }

    public void playButtonClicked() {
        try {
            Modpack modpack = modpackLists.stream().filter(x -> x.getName() == modpackList.getSelectionModel().getSelectedItem()).findFirst().get();
            System.out.println(modpack.getName());
            //TODO: Get download URL here from the object and then make a rest call to retrieve the file.
            //Temporary
            modpackLogic.downloadFile(modpack.getDownloadUrl(), "C:\\Users\\user\\AppData\\Roaming\\.MCLauncher\\test");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        modpackLists = modpackLogic.GetAllModpacks();
        modpackList.setItems(items);
        for (Modpack modpack :
                modpackLists) {
            items.add(modpack.getName());
        }

    }
}