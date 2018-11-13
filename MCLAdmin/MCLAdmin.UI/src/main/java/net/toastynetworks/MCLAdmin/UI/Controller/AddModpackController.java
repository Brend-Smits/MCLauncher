package net.toastynetworks.MCLAdmin.UI.Controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import net.toastynetworks.MCLAdmin.BLL.Interfaces.IModpackLogic;
import net.toastynetworks.MCLAdmin.Domain.Modpack;
import net.toastynetworks.MCLAdmin.Factory.ModpackFactory;
import net.toastynetworks.MCLAdmin.UI.Utilities.SwitchScene;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AddModpackController {

    private IModpackLogic modpackLogic = ModpackFactory.CreateLogic();
    @FXML
    private Button backButton;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField versionTextField;
    @FXML
    private Button SelectWorkspaceButton;

    public void AddModpack() {
        modpackLogic.AddModpack(new Modpack(nameTextField.getText(), versionTextField.getText()));
        try {
            new SwitchScene(backButton, "fxml/main.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void backToMainMenuButton() {
        try {
            new SwitchScene(backButton, "fxml/main.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void SelectWorkspace() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File dir = directoryChooser.showDialog(SelectWorkspaceButton.getScene().getWindow());
        if (dir != null) {
            System.out.println(dir.getAbsolutePath());
            for (File file :
                    dir.listFiles()) {
                try {
                    System.out.println(calcSHA1(file));
                } catch (Exception e ) {
                    System.out.println(e);
                }
            }
        } else {

        }
    }
    /**
     * Read the file and calculate the SHA-1 checksum
     *
     * @param file
     *            the file to read
     * @return the hex representation of the SHA-1 using uppercase chars
     * @throws FileNotFoundException
     *             if the file does not exist, is a directory rather than a
     *             regular file, or for some other reason cannot be opened for
     *             reading
     * @throws IOException
     *             if an I/O error occurs
     * @throws NoSuchAlgorithmException
     *             should never happen
     */
    private static String calcSHA1(File file) throws FileNotFoundException,
            IOException, NoSuchAlgorithmException {

        MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
            InputStream input = new FileInputStream(file);
            byte[] buffer = new byte[8192];
            int len = input.read(buffer);

            while (len != -1) {
                sha1.update(buffer, 0, len);
                len = input.read(buffer);
            }

            return new HexBinaryAdapter().marshal(sha1.digest());
    }
}
