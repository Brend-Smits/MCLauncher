package net.toastynetworks.MCLAdmin.UI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import net.toastynetworks.MCLAdmin.BLL.Interfaces.IModpackLogic;
import net.toastynetworks.MCLAdmin.Factory.ModpackFactory;
import net.toastynetworks.MCLAdmin.Domain.Modpack;

import java.io.IOException;
import java.net.URL;
import java.util.List;
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

    public void addModpackButton(ActionEvent event) {
        try {
            Parent addModpackViewParent = FXMLLoader.load(Main.class.getClassLoader().getResource("AddModpackScene.fxml"));
            Scene addModpackViewScene = new Scene(addModpackViewParent);

            Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
            window.setScene(addModpackViewScene);
            window.show();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
