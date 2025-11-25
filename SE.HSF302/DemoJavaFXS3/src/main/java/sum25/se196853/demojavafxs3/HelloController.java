package sum25.se196853.demojavafxs3;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import sum25.se196853.demojavafxs3.controller.HelloApplication; // Dùng để lấy resource
import sum25.se196853.demojavafxs3.controller.UserController;
import sum25.se196853.demojavafxs3.entity.User;
import sum25.se196853.demojavafxs3.service.UserService;

import java.io.IOException;

@Component // *** Đánh dấu là Spring Component ***
public class HelloController {

    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;
    @FXML private Label lblStatus;
    @FXML private TextField welcomeText;

    // *** Spring sẽ tự động tiêm (inject) UserService vào đây ***
    @Autowired
    private UserService userService;

    // *** Cần Spring Context để load FXML tiếp theo ***
    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @FXML
    private void onHelloButtonClick() throws IOException {
        if(txtUsername.getText().equals("admin") && txtPassword.getText().equals("admin")) {
            Stage currentStage = (Stage) welcomeText.getScene().getWindow();
            currentStage.close();

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/hello-view.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 600,800);

            UserController controller = fxmlLoader.getController();
            controller.setUserName(txtUsername.getText());

            stage.setTitle("Hello World");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();
        }
        else{
            welcomeText.setText("Check your username and password");
        }
    }

    private void loadUserListScreen() {
        try {
            // Lấy Stage (cửa sổ) hiện tại
            Stage stage = (Stage) txtUsername.getScene().getWindow();

            // Tạo FXML Loader cho màn hình mới (sửa đường dẫn có dấu "/")
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/sum25/se196853/demojavafxs3/user-list-view.fxml"));

            // *** Rất quan trọng: Dùng lại Spring context factory ***
            fxmlLoader.setControllerFactory(applicationContext::getBean);

            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 600, 400);
            stage.setScene(scene);
            stage.setTitle("User List");

        } catch (IOException e) {
            e.printStackTrace();
            lblStatus.setText("Error loading user list.");
        }
    }

    @FXML
    public void onByeByeClick(ActionEvent actionEvent) {
        Platform.exit(); // graceful shutdown
    }

    @FXML
    private void initialize() {
        if (lblStatus != null) lblStatus.setText("Please enter your credentials.");
    }
}
