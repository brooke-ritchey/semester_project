package edu.uh.tech.cis3368.semesterproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;

import javafx.scene.control.*;

import java.awt.event.ActionEvent;
import java.io.IOException;


@Component
public class MainController{
    @Autowired
    EmployeeRepository employeeRepository;
    @FXML
    AnchorPane root;

    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;

    @FXML
    private void empManage() throws IOException{
        AnchorPane fxmlLoader2 = FXMLLoader.load(getClass().getResource("employeeManagement.fxml"));
        root.getChildren().addAll(fxmlLoader2);
    }

    @FXML
    private void jobEntry() throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("jobEntry.fxml"));


    }

}
