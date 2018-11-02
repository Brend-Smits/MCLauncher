package net.toastynetworks.MCLAdmin.UI.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import net.toastynetworks.MCLAdmin.BLL.Interfaces.IModpackLogic;
import net.toastynetworks.MCLAdmin.Domain.Modpack;
import net.toastynetworks.MCLAdmin.Factory.ModpackFactory;

public class AddModpackController {

    private IModpackLogic modpackLogic = ModpackFactory.CreateLogic();
    @FXML
    private Button AddModpackButton;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField versionTextField;

    public void AddModpack() {
        modpackLogic.AddModpack(new Modpack(nameTextField.getText(), versionTextField.getText()));

    }

}
