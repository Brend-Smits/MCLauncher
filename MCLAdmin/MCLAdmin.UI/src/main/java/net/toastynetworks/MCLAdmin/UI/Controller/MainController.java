package net.toastynetworks.MCLAdmin.UI.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import net.toastynetworks.MCLAdmin.BLL.Interfaces.IConfigLogic;
import net.toastynetworks.MCLAdmin.BLL.Interfaces.IModpackLogic;
import net.toastynetworks.MCLAdmin.BLL.Interfaces.IModpackUploadLogic;
import net.toastynetworks.MCLAdmin.DAL.Contexts.ModpackUploadRestApiContext;
import net.toastynetworks.MCLAdmin.Factory.ConfigFactory;
import net.toastynetworks.MCLAdmin.Factory.ModpackFactory;
import net.toastynetworks.MCLAdmin.Domain.Modpack;
import net.toastynetworks.MCLAdmin.Factory.ModpackUploadFactory;
import net.toastynetworks.MCLAdmin.UI.Utilities.SwitchScene;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private IModpackLogic modpackLogic = ModpackFactory.CreateLogic();
    private IConfigLogic configLogic = ConfigFactory.CreateLogic();
    private IModpackUploadLogic modpackUploadLogic = ModpackUploadFactory.CreateLogic();
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
    private Button deleteModpackButton;
    @FXML
    private Button uploadModpackButton;

    public static Modpack selectedModpack;

    public ObservableList<Modpack> getModpacks() {
        ObservableList<Modpack> modpacks = FXCollections.observableArrayList();
        for (Modpack modpack :
                modpackLogic.GetAllModpacks()) {
            modpacks.add(modpack);
        }
        return modpacks;
    }



    public void initialize(URL location, ResourceBundle resources) {
        modpackNameColumn.setCellValueFactory(new PropertyValueFactory<Modpack, String>("name"));
        modpackVersionColumn.setCellValueFactory(new PropertyValueFactory<Modpack, String>("versionType"));
        modpackIdColumn.setCellValueFactory(new PropertyValueFactory<Modpack, Integer>("id"));

        modpackTable.setItems(getModpacks());
        configLogic.PrepareWorkspace(modpackLogic.GetAllModpacks());
    }


    public void addModpackButton() {
        try {
            new SwitchScene(addNewModpackButton, "fxml/AddModpackScene.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void editModpackButton() {
        try {
            selectedModpack = modpackTable.getSelectionModel().getSelectedItem();
            new SwitchScene(editModpackButton, "fxml/EditModpackScene.fxml");
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
        try {
            Modpack modpack = modpackTable.getSelectionModel().getSelectedItem();
            String uploadDirectory = configLogic.GetWorkSpaceFromConfig() + "/" + String.valueOf(modpack.getId()) + "-" + modpack.getName();
            File dir = new File(uploadDirectory);
            System.out.println(dir);
            if (dir != null) {
                System.out.println(dir.getAbsolutePath());
                for (File file :
                        dir.listFiles()) {
                    System.out.println(file.getName());
                    modpackUploadLogic.uploadSingleFile(file);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
