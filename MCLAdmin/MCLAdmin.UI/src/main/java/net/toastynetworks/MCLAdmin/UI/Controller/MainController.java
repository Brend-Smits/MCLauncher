package net.toastynetworks.MCLAdmin.UI.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import net.toastynetworks.MCLAdmin.BLL.Interfaces.IModpackLogic;
import net.toastynetworks.MCLAdmin.Factory.ModpackFactory;
import net.toastynetworks.MCLAdmin.Domain.Modpack;
import net.toastynetworks.MCLAdmin.UI.Utilities.SwitchScene;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private IModpackLogic modpackLogic = ModpackFactory.CreateLogic();
    @FXML
    private TableView<Modpack> modpackTable;
    @FXML
    private TableColumn<Modpack, String> modpackNameColumn;
    @FXML
    private TableColumn<Modpack, String> modpackVersionColumn;
    @FXML
    private Button addNewModpackButton;
    @FXML
    private Button editModpackButton;

    public ObservableList<Modpack> getModpacks() {
        ObservableList<Modpack> modpacks = FXCollections.observableArrayList();
        for (Modpack modpack :
                GetAllModpacks()) {
            modpacks.add(modpack);
        }
        return modpacks;
    }

    public List<Modpack> GetAllModpacks() {
        return modpackLogic.GetAllModpacks();
    }


    public void initialize(URL location, ResourceBundle resources) {
        modpackNameColumn.setCellValueFactory(new PropertyValueFactory<Modpack, String>("modpackName"));
        modpackVersionColumn.setCellValueFactory(new PropertyValueFactory<Modpack, String>("modpackVersionType"));

        modpackTable.setItems(getModpacks());
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
            Modpack modpack = modpackTable.getSelectionModel().getSelectedItem();
            EditModpackController editModpackController = new EditModpackController();
            editModpackController.objectPassThrough(modpack);
            new SwitchScene(editModpackButton, "fxml/EditModpackScene.fxml");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
