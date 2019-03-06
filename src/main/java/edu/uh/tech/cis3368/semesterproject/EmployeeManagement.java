package edu.uh.tech.cis3368.semesterproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Component
public class EmployeeManagement {
    @Autowired
    EmployeeRepository employeeRepository;

    @FXML
    AnchorPane root;

    @FXML
     TextField firstname;
    @FXML
     TextField lastname;
    @FXML
     TextField email;
    @FXML
     TextField phone;
    @FXML
     TextField trial;



    @FXML
    private void saveEmp() throws IOException{

        Employee employee = new Employee();

        employee.setFirstName(firstname.getText().toString());
        employee.setLastName(lastname.getText().toString());
        employee.setEmail(email.getText().toString());
        employee.setPhone(phone.getText().toString());
        trial.setText(employee.getEmail());


    }
    @FXML
    private void mainMenu() throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("main.fxml"));
        root.getChildren().setAll(pane);}
}
