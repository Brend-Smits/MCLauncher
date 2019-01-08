package net.toastynetworks.MCLAdmin.UI.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import net.toastynetworks.MCLAdmin.BLL.Interfaces.IUserLogic;
import net.toastynetworks.MCLAdmin.Domain.User;
import net.toastynetworks.MCLAdmin.Factory.UserFactory;
import net.toastynetworks.javafx.SwitchSceneUtils;

public class LoginController {
    private IUserLogic userLogic = UserFactory.CreateLogic();
    public User loggedInUser;

    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;

    public void loginButtonClick() {
        String username = usernameTextField.getText();
        String password = passwordField.getText();
        this.loggedInUser = userLogic.Login(username, password);

        if (this.loggedInUser.getTokenType() != null && loggedInUser.getTokenType().equalsIgnoreCase("bearer")) {
            try {
                new SwitchSceneUtils("fxml/main.fxml");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Incorrect credentials or unknown user");
        }
    }
}
