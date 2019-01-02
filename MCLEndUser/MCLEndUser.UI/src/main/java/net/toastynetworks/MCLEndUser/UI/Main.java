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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main extends Application implements Initializable, IObserver {
    @FXML
    private TableView<Modpack> modpackTableView;
    @FXML
    private TableColumn<Modpack, String> modpackNameColumn;
    @FXML
    private TableColumn<Modpack, Boolean> modpackStatusColumn;
    private IModpackLogic modpackLogic = ModpackFactory.CreateLogic();
    private IConfigLogic configLogic = ConfigFactory.CreateLogic();
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
                Modpack modpack = modpackList.stream().filter(x -> x.getId() == modpackTableView.getSelectionModel().getSelectedItem().getId()).findFirst().get();
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

            List<Modpack> tempModpackList = modpackLogic.GetAllModpacks();

            for (Modpack newModpack :
                    tempModpackList) {
                if (!modpackList.stream().filter(o -> o.getId() == newModpack.getId()).findFirst().isPresent()) {
                    modpackList.add(newModpack);
                    modpackTableView.getItems().add(newModpack);
                    modpackTableView.refresh();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ObservableList<Modpack> getModpacks() {
        ObservableList<Modpack> modpacks = FXCollections.observableArrayList();
        for (Modpack modpack :
                modpackLogic.GetAllModpacks()) {
            modpacks.add(modpack);
            modpackList.add(modpack);
        }
        return modpacks;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        threadPool.execute(() -> {
            try {
                socketLogic.registerObserver(this);

                socketLogic.setModpackListForStatusCheck(modpackList);
                modpackNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
                modpackStatusColumn.setCellValueFactory(new PropertyValueFactory<>("onlineStatus"));

                modpackTableView.setItems(getModpacks());

                modpackTableView.refresh();
                socketLogic.checkStatus();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void update(Object object) {
        try {
            List<Modpack> tempList = (List<Modpack>) object;
            for (Modpack newModpack :
                    tempList) {
                Modpack tempModpack = modpackList.stream().filter(o -> o.getId() == newModpack.getId()).findFirst().get();
                modpackList.set(modpackList.indexOf(tempModpack), newModpack);
                Platform.runLater(() -> {
                    modpackTableView.getItems().stream().filter(o -> o.getId() == tempModpack.getId()).findAny().get().setOnlineStatus(tempModpack.getOnlineStatus());
                    modpackTableView.refresh();
                });
            }

            System.out.println("Received update, thank you.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}