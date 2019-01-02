package net.toastynetworks.MCLEndUser.UI;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import net.toastynetworks.MCLEndUser.BLL.Interfaces.IConfigLogic;
import net.toastynetworks.MCLEndUser.BLL.Interfaces.IModpackLogic;
import net.toastynetworks.MCLEndUser.BLL.Interfaces.ISocketLogic;
import net.toastynetworks.MCLEndUser.Domain.IObserver;
import net.toastynetworks.MCLEndUser.Domain.Modpack;
import net.toastynetworks.MCLEndUser.Factory.ConfigFactory;
import net.toastynetworks.MCLEndUser.Factory.ModpackFactory;
import net.toastynetworks.MCLEndUser.Factory.SocketFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.*;


public class Main extends Application implements Initializable, IObserver {
    @FXML
    private ListView modpackListView;
    @FXML
    private ListView modpackStatusListView;
    private IModpackLogic modpackLogic = ModpackFactory.CreateLogic();
    private IConfigLogic configLogic = ConfigFactory.CreateLogic();
    private static ArrayList<Modpack> retrievedModpackList = new ArrayList<>();
    private static List<Modpack> statusUpdatedModpacks = new ArrayList<>();
    private static List<Modpack> modpackList = new ArrayList<>();
    ExecutorService threadPool = Executors.newWorkStealingPool();
    ISocketLogic socketLogic = SocketFactory.CreateLogic();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL fileLocation;
        if (configLogic.GetWorkSpaceFromConfig() != null) {
            fileLocation = getClass().getClassLoader().getResource("user-ui.fxml");
        } else {
            fileLocation = getClass().getClassLoader().getResource("SelectWorkspaceScene.fxml");
        }
        Parent root = FXMLLoader.load(fileLocation);
        primaryStage.setTitle("ToastyNetworks Launcher V2");
        primaryStage.setScene(new Scene(root, 1900, 1040));
        primaryStage.show();

        primaryStage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
            socketLogic.unregisterObserver(this);
        });

    }

    public void playButtonClicked() {
        threadPool.execute(() -> {
            try {
                Modpack modpack = retrievedModpackList.stream().filter(x -> x.getName() == modpackListView.getSelectionModel().getSelectedItem()).findFirst().get();
                System.out.println(modpack.getName());
                if (modpack.getDownloadUrl() != null) {
                    modpackLogic.downloadFile(modpack.getDownloadUrl(), configLogic.GetWorkSpaceFromConfig() + "\\" + modpack.getName());
                } else {
                    System.out.println("No valid download url could be found: " + modpack.getDownloadUrl());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void updateButtonClicked() {
            try {

                modpackList.clear();
                modpackList = modpackLogic.GetAllModpacks();
                socketLogic.setModpackListForStatusCheck(modpackList);

                modpackListView.getItems().clear();
                modpackStatusListView.getItems().clear();
                for (Modpack modpack :
                        modpackList) {
                    modpackListView.getItems().add(modpack.getName());
                    modpackStatusListView.getItems().add(modpack.isOnline());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        threadPool.execute(() -> {
            try {
                socketLogic.registerObserver(this);

                modpackList = modpackLogic.GetAllModpacks();
                socketLogic.setModpackListForStatusCheck(modpackList);
                for (Modpack modpack :
                        modpackList) {
                    modpackListView.getItems().add(modpack.getName());
                    modpackStatusListView.getItems().add(modpack.isOnline());
                }
                socketLogic.checkStatus();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void update(Object object) {
        try {
            Platform.runLater(() -> modpackStatusListView.getItems().clear());
            List<Modpack> tempList = (List<Modpack>) object;
            System.out.println("SIZE: " + tempList.size());
            for (Modpack newModpack :
                    tempList) {
                Modpack tempModpack = modpackList.stream().filter(o -> o.getId() == newModpack.getId()).findFirst().get();
                if (tempModpack.isOnline() != newModpack.isOnline()) {
                    modpackList.set(modpackList.indexOf(tempModpack), newModpack);
                }
                Platform.runLater(() -> modpackStatusListView.getItems().add(newModpack.isOnline()));
            }

            System.out.println("Received update, thank you.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}