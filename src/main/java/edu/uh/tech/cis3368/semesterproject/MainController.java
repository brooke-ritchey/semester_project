package edu.uh.tech.cis3368.semesterproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import java.io.IOException;


@Component
public class MainController{
    @FXML
    private Button employeeMng;
    @FXML
    private Button job;


    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @FXML
    public void empManage(ActionEvent actionEvent) throws IOException{
        Stage parent  = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("employeeManagement.fxml"));
        fxmlLoader.setControllerFactory(applicationContext::getBean);
        Scene scene = new Scene(fxmlLoader.load());
        EmployeeManagement empManagement = fxmlLoader.getController();
        empManagement.setReturnScene(employeeMng.getScene());
        parent.setScene(scene);
    }

    @FXML
    private void jobEntry(ActionEvent actionEvent) throws IOException{
        Stage parent  = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("jobEntry.fxml"));
        fxmlLoader.setControllerFactory(applicationContext::getBean);
        Scene scene = new Scene(fxmlLoader.load());
        JobEntry jobEntry = fxmlLoader.getController();
        jobEntry.setReturnScene(job.getScene());
        parent.setScene(scene);

    }

}
