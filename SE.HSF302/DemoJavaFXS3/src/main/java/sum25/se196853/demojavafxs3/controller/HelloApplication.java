package sum25.se196853.demojavafxs3.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import sum25.se196853.demojavafxs3.SpringBootApp;

import java.io.IOException;

public class HelloApplication extends Application {

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init() {
        applicationContext = new SpringApplicationBuilder(SpringBootApp.class).run();
    }

    @Override
    public void start(Stage stage) throws IOException {
        // *** Sửa đường dẫn FXML: Thêm dấu "/" ở đầu để tìm từ root của classpath ***
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/sum25/se196853/demojavafxs3/hello-view.fxml"));

        // *** Rất quan trọng: Để Spring tiêm (inject) dependencies vào Controller ***
        fxmlLoader.setControllerFactory(applicationContext::getBean);

        Parent root = fxmlLoader.load();
        // Sử dụng file FXML đã được style (hello-view.fxml)
        Scene scene = new Scene(root);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        // Đóng Spring context khi ứng dụng tắt
        applicationContext.close();
    }
}
