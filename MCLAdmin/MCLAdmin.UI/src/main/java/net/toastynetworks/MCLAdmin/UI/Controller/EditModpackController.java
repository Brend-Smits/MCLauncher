package net.toastynetworks.MCLAdmin.UI.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import net.toastynetworks.MCLAdmin.BLL.Interfaces.IConfigLogic;
import net.toastynetworks.MCLAdmin.BLL.Interfaces.IModpackLogic;
import net.toastynetworks.MCLAdmin.Domain.Modpack;
import net.toastynetworks.MCLAdmin.Factory.ConfigFactory;
import net.toastynetworks.MCLAdmin.Factory.ModpackFactory;
import net.toastynetworks.MCLAdmin.UI.Utilities.SwitchScene;

import java.io.File;

import static net.toastynetworks.MCLAdmin.UI.Controller.MainController.selectedModpack;

public class EditModpackController {

    private IModpackLogic modpackLogic = ModpackFactory.CreateLogic();
    private IConfigLogic configLogic = ConfigFactory.CreateLogic();
    @FXML
    private Button backButton;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField versionTextField;
    @FXML
    private TextField idTextField;
    @FXML
    private TextField workspaceTextField;
    @FXML
    private Button changeWorkspaceButton;

    public void backToMainMenuButton() {
        try {
            new SwitchScene(backButton, "fxml/main.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void changeWorkspaceButtonClicked() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File dir = directoryChooser.showDialog(changeWorkspaceButton.getScene().getWindow());
        if (dir != null) {
            workspaceTextField.setText(dir.getAbsolutePath());
            configLogic.EditConfig(selectedModpack.getModpackName(), workspaceTextField.getText());
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
        idTextField.setText(String.valueOf(selectedModpack.getModpackId()));
        nameTextField.setText(selectedModpack.getModpackName());
        versionTextField.setText(selectedModpack.getModpackVersionType());
        workspaceTextField.setText(configLogic.GetWorkSpaceFromConfig(selectedModpack.getModpackName()));

    }
}
