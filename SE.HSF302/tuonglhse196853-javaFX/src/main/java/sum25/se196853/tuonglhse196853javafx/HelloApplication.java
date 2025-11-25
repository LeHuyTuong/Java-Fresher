package sum25.se196853.tuonglhse196853javafx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.boot.WebApplicationType;
import java.net.URL;

public class HelloApplication extends Application {

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init() {

        applicationContext = new SpringApplicationBuilder(SpringBootApp.class)
                .web(WebApplicationType.NONE)
                .headless(false)
                .run(getParameters().getRaw().toArray(new String[0]));
    }
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/sum25/se196853/tuonglhse196853javafx/view/login-view.fxml"));
        fxmlLoader.setControllerFactory(applicationContext::getBean);

        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        URL cssUrl = HelloApplication.class.getResource("/sum25/se196853/tuonglhse196853javafx/css/login.css");
        if (cssUrl != null) {
            scene.getStylesheets().add(cssUrl.toExternalForm());
            System.out.println("Loaded CSS: " + cssUrl.toExternalForm());
        } else {
            System.err.println("WARN: Could not find login.css");
        }


        stage.setTitle("Laptop Shop - Login");
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> {
            stop();
        });
        stage.show();
    }

    @Override
    public void stop() {
        if (applicationContext != null) {
            applicationContext.close();
        }
        Platform.exit();
        System.exit(0);
    }
}
