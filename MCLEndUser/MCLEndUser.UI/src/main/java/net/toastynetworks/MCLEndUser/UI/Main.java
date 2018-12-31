package net.toastynetworks.MCLEndUser.UI;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private ObservableList<String> modpackNameList = FXCollections.observableArrayList();
    //TODO: See if we can create a hashmap with modpack ID as key and status as Boolean value. If so, we can link modpackNameList and Status list together so they will always be in the correct position.
    private ObservableList<Boolean> statusItems = FXCollections.observableArrayList();
    private IModpackLogic modpackLogic = ModpackFactory.CreateLogic();
    private IConfigLogic configLogic = ConfigFactory.CreateLogic();
    private static ArrayList<Modpack> retrievedModpackList = new ArrayList<>();
    private static List<Modpack> statusUpdatedModpacks = new ArrayList<>();
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
                retrievedModpackList.clear();
                modpackNameList.clear();
                statusItems.clear();
                retrievedModpackList = modpackLogic.GetAllModpacks();

                socketLogic.setModpackListForStatusCheck(retrievedModpackList);
                fillModpackListViews();

                modpackListView.setItems(modpackNameList);
                modpackStatusListView.setItems(statusItems);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    private void fillModpackListViews() {
        System.out.println("FillMopdackListViews: Number of objects in StatusUpadtedModpacks: " + statusUpdatedModpacks.size());
        for (Modpack freshModpack :
                retrievedModpackList) {
            Modpack statusCheckedModpack = statusUpdatedModpacks
                    .stream()
                    .filter(o -> o.getId() == freshModpack.getId())
                    .findAny()
                    .orElse(freshModpack);
            freshModpack.setOnline(statusCheckedModpack.isOnline());

            Platform.runLater(() -> {
                        modpackNameList.add(freshModpack.getName());
                        statusItems.add(freshModpack.isOnline());
                    });
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        threadPool.execute(() -> {
            try {
                socketLogic.registerObserver(this);
                retrievedModpackList = modpackLogic.GetAllModpacks();

                socketLogic.setModpackListForStatusCheck(retrievedModpackList);
                socketLogic.checkStatus();

                fillModpackListViews();

                modpackListView.setItems(modpackNameList);
                modpackStatusListView.setItems(statusItems);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void update(Object object) {
        try {
            statusUpdatedModpacks.clear();
            for (Modpack newModpack :
                    (ArrayList<Modpack>) object) {
                Platform.runLater(() -> statusUpdatedModpacks.add(newModpack));
            }
            ((ArrayList<Modpack>) object).size();
            Platform.runLater(() -> statusItems.clear());

            for (Modpack modpack :
                    retrievedModpackList) {
                Platform.runLater(() -> statusItems.add(modpack.isOnline()));
            }
            Platform.runLater(() -> modpackStatusListView.setItems(statusItems));
            System.out.println("Received update, thank you.");
            System.out.println("NotifyUpdate: Number of objects in statusUpdatedModpacks: " + statusUpdatedModpacks.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}