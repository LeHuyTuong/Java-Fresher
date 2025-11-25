package sum25.se196853.demojavafxs3.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class UserController {

    private  String userName;
    @FXML
    private Label txtRole;

    @FXML
    public void onHelloButtonClick(ActionEvent event) {
        txtRole.setText("Hello World" + userName);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @FXML
    public void onByeByeClick(ActionEvent event) {
        Platform.exit();
    }
}

