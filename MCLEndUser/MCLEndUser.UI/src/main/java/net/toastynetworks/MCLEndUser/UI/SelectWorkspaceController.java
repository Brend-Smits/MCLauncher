package net.toastynetworks.MCLEndUser.UI;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import net.toastynetworks.MCLEndUser.BLL.Interfaces.IConfigLogic;
import net.toastynetworks.MCLEndUser.Factory.ConfigFactory;
import net.toastynetworks.javafx.SwitchSceneUtils;

import java.io.File;


public class SelectWorkspaceController {
    private IConfigLogic configLogic = ConfigFactory.CreateLogic();

    @FXML
    private Button changeWorkspaceButton;
    @FXML
    private TextField workspaceTextField;
    @FXML
    private Button nextButton;

    public void changeButtonClick() {
        try {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File dir = directoryChooser.showDialog(changeWorkspaceButton.getScene().getWindow());
            if (dir != null) {
                workspaceTextField.setText(dir.getAbsolutePath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void nextButtonClick() {
        try {
            if (workspaceTextField.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("MCL-Error");
                alert.setContentText("Please select a workspace");
                alert.showAndWait();
            } else {
                try {
                    configLogic.CreateConfig();
                    configLogic.EditConfig(workspaceTextField.getText());
                    new SwitchSceneUtils(nextButton, "fxml/main.fxml");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
