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
import java.util.ResourceBundle;
import java.util.concurrent.*;


public class Main extends Application implements Initializable, IObserver {
    @FXML
    private ListView modpackList;
    private ObservableList<String> items = FXCollections.observableArrayList();
    private IModpackLogic modpackLogic = ModpackFactory.CreateLogic();
    private IConfigLogic configLogic = ConfigFactory.CreateLogic();
    private static ArrayList<Modpack> modpackLists = new ArrayList<>();
    ExecutorService threadPool = Executors.newWorkStealingPool();
    ISocketLogic socketLogic = SocketFactory.CreateLogic();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        socketLogic.registerObserver(this);
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
                Modpack modpack = modpackLists.stream().filter(x -> x.getName() == modpackList.getSelectionModel().getSelectedItem()).findFirst().get();
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
            modpackLists.clear();
            items.clear();
            modpackLists = modpackLogic.GetAllModpacks();
            modpackList.setItems(items);
            for (Modpack modpack :
                    modpackLists) {
                if (!items.stream().filter(s -> s.equalsIgnoreCase(modpack.getName())).findFirst().isPresent()) {
                    items.add(modpack.getName());
                }
            }
            socketLogic.setModpackListForStatusCheck(modpackLists);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        threadPool.execute(() -> {
            try {
                modpackLists = modpackLogic.GetAllModpacks();
                if (!items.isEmpty()) {
                    modpackList.setItems(items);
                    for (Modpack modpack :
                            modpackLists) {
                        items.add(modpack.getName());
                    }
                }
                socketLogic.setModpackListForStatusCheck(modpackLists);
                socketLogic.checkStatus();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    @Override
    public void update(Object object) {
        this.modpackLists = (ArrayList<Modpack>) object;
        System.out.println("Received update, thank you.");
    }
}