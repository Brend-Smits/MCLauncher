package net.toastynetworks.MCLAdmin.UI.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import net.toastynetworks.MCLAdmin.BLL.Interfaces.IModpackLogic;
import net.toastynetworks.MCLAdmin.Domain.Modpack;
import net.toastynetworks.MCLAdmin.Factory.ModpackFactory;
import net.toastynetworks.javafx.SwitchSceneUtils;


public class AddModpackController {

    private IModpackLogic modpackLogic = ModpackFactory.CreateLogic();
    @FXML
    private Button backButton;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField versionTextField;
    @FXML
    private TextField hostTextField;

    public void AddModpack() {
        modpackLogic.AddModpack(new Modpack(nameTextField.getText(), versionTextField.getText(), hostTextField.getText()));
        try {
            new SwitchSceneUtils(backButton, "fxml/main.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void backToMainMenuButton() {
        try {
            new SwitchSceneUtils(backButton, "fxml/main.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
