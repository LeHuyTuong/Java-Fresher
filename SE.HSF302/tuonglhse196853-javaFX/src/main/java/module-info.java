module sum25.se196853.tuonglhse196853javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.mysql.cj;

    // Spring Boot & Core
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.boot;
    requires spring.beans;
    requires spring.core;
    requires spring.aop;
    requires spring.web;

    // Spring Data JPA & Persistence
    requires spring.data.jpa;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires spring.data.commons;
    requires spring.tx;

    // JDBC Driver
    requires java.sql;
    requires jakarta.transaction;

    // Validation & Injection APIs
    requires jakarta.validation;
    requires jakarta.inject;
    requires jakarta.annotation;
    requires jakarta.cdi;


    opens sum25.se196853.tuonglhse196853javafx to javafx.graphics, javafx.fxml, spring.core, spring.beans, spring.context;
    opens sum25.se196853.tuonglhse196853javafx.controller to javafx.fxml, spring.core, spring.beans, spring.context, javafx.weaver.spring;
    opens sum25.se196853.tuonglhse196853javafx.entity to org.hibernate.orm.core, spring.core, javafx.base;
    opens sum25.se196853.tuonglhse196853javafx.service to spring.core, spring.beans, spring.context;
    opens sum25.se196853.tuonglhse196853javafx.service.impl to spring.core, spring.beans, spring.context;
    opens sum25.se196853.tuonglhse196853javafx.repository to spring.core, spring.beans, spring.context, spring.data.jpa;
    opens sum25.se196853.tuonglhse196853javafx.config to spring.core, spring.beans, spring.context;


    exports sum25.se196853.tuonglhse196853javafx;
    exports sum25.se196853.tuonglhse196853javafx.controller;
    exports sum25.se196853.tuonglhse196853javafx.entity;
    exports sum25.se196853.tuonglhse196853javafx.service;
    exports sum25.se196853.tuonglhse196853javafx.repository;
}
