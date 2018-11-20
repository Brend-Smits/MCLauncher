package net.toastynetworks.MCLAdmin.UI.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import net.toastynetworks.MCLAdmin.BLL.Interfaces.IModpackLogic;
import net.toastynetworks.MCLAdmin.Domain.Modpack;
import net.toastynetworks.MCLAdmin.Factory.ModpackFactory;
import net.toastynetworks.MCLAdmin.UI.Utilities.SwitchScene;

import static net.toastynetworks.MCLAdmin.UI.Controller.MainController.selectedModpack;

public class EditModpackController {

    private IModpackLogic modpackLogic = ModpackFactory.CreateLogic();
    @FXML
    private Button backButton;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField versionTextField;
    @FXML
    private TextField idTextField;

    public void backToMainMenuButton() {
        try {
            new SwitchScene(backButton, "fxml/main.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void saveModpackAction() {
        try {
            Modpack editedModpack = new Modpack(Integer.valueOf(idTextField.getText()), nameTextField.getText(), versionTextField.getText());
            modpackLogic.EditModpack(editedModpack);
            System.out.println("Saving modpack");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void initialize() {
        idTextField.setText(String.valueOf(selectedModpack.getId()));
        nameTextField.setText(selectedModpack.getName());
        versionTextField.setText(selectedModpack.getVersionType());
    }
}
