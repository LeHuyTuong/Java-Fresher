module sum25.se196853.demojavafxs3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.boot;

    requires spring.beans;
    requires spring.core;
    requires spring.data.jpa;
    requires jakarta.persistence;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires com.microsoft.sqlserver.jdbc;
    requires java.desktop;


    opens sum25.se196853.demojavafxs3 to javafx.fxml, spring.core, spring.beans, spring.context;
    opens sum25.se196853.demojavafxs3.controller to javafx.fxml, spring.core, spring.beans, spring.context;
    opens sum25.se196853.demojavafxs3.entity to org.hibernate.orm.core, spring.core, javafx.base;
    opens sum25.se196853.demojavafxs3.service to spring.core, spring.beans, spring.context;
    opens sum25.se196853.demojavafxs3.config to spring.core, spring.beans, spring.context;

    exports sum25.se196853.demojavafxs3;
    exports sum25.se196853.demojavafxs3.controller;
}
