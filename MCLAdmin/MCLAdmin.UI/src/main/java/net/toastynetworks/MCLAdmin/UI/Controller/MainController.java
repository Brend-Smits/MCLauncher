package net.toastynetworks.MCLAdmin.UI.Controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import net.toastynetworks.MCLAdmin.BLL.Interfaces.IClientLogic;
import net.toastynetworks.MCLAdmin.BLL.Interfaces.IConfigLogic;
import net.toastynetworks.MCLAdmin.BLL.Interfaces.IModpackLogic;
import net.toastynetworks.MCLAdmin.BLL.Interfaces.IModpackUploadLogic;
import net.toastynetworks.MCLAdmin.Domain.IObserver;
import net.toastynetworks.MCLAdmin.Domain.Modpack;
import net.toastynetworks.MCLAdmin.Factory.ClientFactory;
import net.toastynetworks.MCLAdmin.Factory.ConfigFactory;
import net.toastynetworks.MCLAdmin.Factory.ModpackFactory;
import net.toastynetworks.MCLAdmin.Factory.ModpackUploadFactory;
import net.toastynetworks.javafx.SwitchSceneUtils;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class MainController extends Application implements Initializable, IObserver {

    public static Modpack selectedModpack;
    ExecutorService threadPool = Executors.newWorkStealingPool();
    ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
    private IModpackLogic modpackLogic = ModpackFactory.CreateLogic();
    private IConfigLogic configLogic = ConfigFactory.CreateLogic();
    private String workspace = configLogic.GetWorkSpaceFromConfig();
    private IModpackUploadLogic modpackUploadLogic = ModpackUploadFactory.CreateLogic();
    private IClientLogic clientSocketLogic = ClientFactory.CreateLogic();


    @FXML
    private TableView<Modpack> modpackTable;
    @FXML
    private TableColumn<Modpack, String> modpackNameColumn;
    @FXML
    private TableColumn<Modpack, String> modpackVersionColumn;
    @FXML
    private TableColumn<Modpack, Integer> modpackIdColumn;
    @FXML
    private Button addNewModpackButton;
    @FXML
    private Button editModpackButton;
    @FXML
    private ProgressBar progressBar = new ProgressBar();
    @FXML
    private Label progressLabel;
    @FXML
    private Label statusLabel;
    private double progressPercentage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL fileLocation;
        if (configLogic.GetWorkSpaceFromConfig() != null) {
            fileLocation = getClass().getClassLoader().getResource("fxml/LoginScene.fxml");
        } else {
            fileLocation = getClass().getClassLoader().getResource("fxml/SelectWorkspaceScene.fxml");
        }
        Parent root = FXMLLoader.load(fileLocation);
        primaryStage.setTitle("MCL-Admin");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private ObservableList<Modpack> getModpacks() {
        ObservableList<Modpack> modpacks = FXCollections.observableArrayList();
        for (Modpack modpack :
                modpackLogic.GetAllModpacks()) {
            modpacks.add(modpack);
        }
        return modpacks;
    }

    public void initialize(URL location, ResourceBundle resources) {
        try {
            populateModpackTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void populateModpackTable() {
        modpackNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        modpackVersionColumn.setCellValueFactory(new PropertyValueFactory<>("versionType"));
        modpackIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        modpackTable.setItems(getModpacks());
        configLogic.PrepareWorkspace(modpackLogic.GetAllModpacks());
    }

    public void addModpackButton() {
        try {
            new SwitchSceneUtils(addNewModpackButton, "fxml/AddModpackScene.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editModpackButton() {
        try {
            selectedModpack = modpackTable.getSelectionModel().getSelectedItem();
            new SwitchSceneUtils(editModpackButton, "fxml/EditModpackScene.fxml");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteModpackButtonClick() {
        try {
            modpackLogic.DeleteModpack(modpackTable.getSelectionModel().getSelectedItem().getId());
            modpackTable.setItems(getModpacks());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void uploadModpackButtonClick() {
        threadPool.execute(() -> {
            try {
                Thread.currentThread().setName("MCL-UploadModpack-Thread");

                modpackUploadLogic.registerObserver(this);
                Modpack modpack = modpackTable.getSelectionModel().getSelectedItem();
                modpackUploadLogic.uploadModpack(modpack, workspace);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                progressBar.setProgress(1);
                Platform.runLater(() -> {
                    progressLabel.setText("Done!");
                    modpackUploadLogic.resetUploadProgress();
                    statusLabel.setText("");
                });
                modpackUploadLogic.unregisterObserver(this);
            }
        });
    }

    @Override
    public void update(Object object) {
        progressPercentage = (Double) object;
        progressBar.setProgress(((float) progressPercentage));
        Platform.runLater(() -> {
            progressLabel.setText(String.format("%.2f", (progressPercentage * 100)) + "%");
            statusLabel.setText(modpackUploadLogic.getUploadStatus());


        });
    }
}
