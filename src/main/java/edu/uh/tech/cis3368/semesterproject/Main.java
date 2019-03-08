package edu.uh.tech.cis3368.semesterproject;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class Main extends Application {

    private Parent root;

    private ConfigurableApplicationContext springContext;

    @Autowired
    EmployeeRepository employeeRepository;

    public static void main(String[] args) {
        launch();
    }

   @Override
    public void init() throws Exception {
        springContext = SpringApplication.run(getClass());
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);
        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("employeeManagement.fxml"));
        fxmlLoader2.setControllerFactory(springContext::getBean);
        root = fxmlLoader.load();
        super.init();

       springContext.getAutowireCapableBeanFactory().autowireBean(this);

//        employeeRepository.hashCode();
//        Employee employee = new Employee();
//        employee.setLastName("Granger");
//        employeeRepository.save(employee);
    }



    @Override
    public void start(Stage primaryStage) throws Exception {



        Scene mainMenu = new Scene(root,300,300);
        primaryStage.setScene(mainMenu);
        primaryStage.show();

    }

}
