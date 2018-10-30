package net.toastynetworks.MCLAdmin.UI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import net.toastynetworks.MCLAdmin.BLL.Interfaces.IModpackLogic;
import net.toastynetworks.MCLAdmin.Factory.ModpackFactory;
import net.toastynetworks.MCLAdmin.Domain.Modpack;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private IModpackLogic modpackLogic = ModpackFactory.CreateLogic();
    @FXML
    private TableView<Modpack> modpackTable;
    @FXML
    private TableColumn<Modpack, String> modpackNameColumn;
    @FXML
        private TableColumn<Modpack, String> modpackVersionColumn;

    public ObservableList<Modpack> getModpacks() {
        ObservableList<Modpack> modpacks = FXCollections.observableArrayList();
        modpacks.add(new Modpack("SF3", "Beta"));
        return modpacks;
    }


    public void initialize(URL location, ResourceBundle resources) {
        modpackNameColumn.setCellValueFactory(new PropertyValueFactory<Modpack, String>("modpackName"));
        modpackVersionColumn.setCellValueFactory(new PropertyValueFactory<Modpack, String>("modpackVersionType"));


        modpackTable.setItems(getModpacks());

    }
}
