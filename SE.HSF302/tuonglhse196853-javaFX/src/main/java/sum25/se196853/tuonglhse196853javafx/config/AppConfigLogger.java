package sum25.se196853.tuonglhse196853javafx.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfigLogger {

    @Value("${spring.datasource.driver-classname}")
    private String driverClassName;

    @PostConstruct
    public void logConfig() {
        System.out.println("Configured JDBC Driver: " + driverClassName);
    }
}
