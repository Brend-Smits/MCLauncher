package net.toastynetworks.MCLAdmin.UI.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import net.toastynetworks.MCLAdmin.Domain.Modpack;
import net.toastynetworks.MCLAdmin.UI.Utilities.SwitchScene;

import static net.toastynetworks.MCLAdmin.UI.Controller.MainController.selectedModpack;

public class EditModpackController {

    @FXML
    private Button backButton;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField versionTextField;


    public void backToMainMenuButton() {
        try {
            new SwitchScene(backButton, "fxml/admin-ui.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void saveModpackAction() {
        try {
            //logic here to save modpack
            Modpack editedModpack = new Modpack(nameTextField.getText(), versionTextField.getText());
            System.out.println("Saving modpack");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void initialize() {
        nameTextField.setText(selectedModpack.getModpackName());
        versionTextField.setText(selectedModpack.getModpackVersionType());
    }
}
