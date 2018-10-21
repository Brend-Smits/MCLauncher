package net.toastynetworks.MCLEndUser.UI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import net.toastynetworks.MCLEndUser.BLL.Interfaces.IModpackLogic;
import net.toastynetworks.MCLEndUser.Factory.ModpackFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private ListView modpackList;

    private ObservableList<String> items = FXCollections.observableArrayList();
    private IModpackLogic modpackLogic = ModpackFactory.CreateLogic();

    public void playButtonClicked(){
        System.out.println("Used started modpack....");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        modpackList.setItems(items);
        for (String modpackName :
                GetAllModpacks()) {
            items.add(modpackName);
        }

    }
    public List<String> GetAllModpacks() {
        return modpackLogic.GetAllModpacks();
    }

}
