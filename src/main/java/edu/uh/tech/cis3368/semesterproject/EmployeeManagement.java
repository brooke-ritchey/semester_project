package edu.uh.tech.cis3368.semesterproject;

import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class EmployeeManagement implements Initializable{
    @Autowired
    EmployeeRepository employeeRepository;
    @FXML
    Button btnMain;
    @FXML
    TextField updatefname;
    @FXML
    TextField updatelname;
    @FXML
    TextField updateemail;
    @FXML
    TextField updatephone;
    @FXML
    TableView<Employee> table;
    @FXML
    TableColumn<Employee, String> fname;
    @FXML
    TableColumn<Employee, String> lname;
    @FXML
    TableColumn<Employee, String> email;
    @FXML
    TableColumn<Employee, String> phone;
    @FXML
    Button btnNew;

    private Scene scene;
    ObservableList<Employee> emp;
    Employee selected;


    @Autowired
    ConfigurableApplicationContext applicationContext;

    public void setReturnScene(Scene scene){
        this.scene = scene;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        emp = FXCollections.observableArrayList();
        emp = getEmployees();
        selected = new Employee();
        fname.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstName"));
        lname.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));
        email.setCellValueFactory(new PropertyValueFactory<Employee, String>("email"));
        phone.setCellValueFactory(new PropertyValueFactory<Employee, String>("phone"));
        table.getColumns();
        table.setItems(emp);
        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Employee>() {
            @Override
            public void changed(ObservableValue<? extends Employee> observable, Employee oldemp, Employee newemp) {
                if (newemp != null) {
                    String selectemail = new String(newemp.getEmail());
                    selected = employeeRepository.findByEmail(selectemail);
                    updatefname.setText(newemp.getFirstName());
                    updatelname.setText(newemp.getLastName());
                    updateemail.setText(newemp.getEmail());
                    updatephone.setText(newemp.getPhone());
                }
            }
        });

    }

    private ObservableList<Employee> getEmployees(){
        ObservableList<Employee> employees = FXCollections.observableArrayList();
        employeeRepository.findAll().forEach(e -> employees.addAll(e));
        return employees;
    }


    @FXML
    private void mainMenu(ActionEvent actionEvent) throws IOException{
        Stage parent  = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
        fxmlLoader.setControllerFactory(applicationContext::getBean);
        Scene scene = new Scene(fxmlLoader.load());
        MainController mainController = fxmlLoader.getController();
        parent.setScene(scene);
    }

    @FXML
    private void updateEmployee(){
        selected.setPhone(updatephone.getText());
        selected.setFirstName(updatefname.getText());
        selected.setLastName(updatelname.getText());
        selected.setEmail(updateemail.getText());
        employeeRepository.save(selected);

        emp = getEmployees();
        table.setItems(emp);
        updateemail.clear();
        updatefname.clear();
        updatelname.clear();
        updatephone.clear();

    }

    @FXML
    private void deleteEmployee(){
        employeeRepository.delete(selected);
        emp = getEmployees();
        table.setItems(emp);
        updateemail.clear();
        updatefname.clear();
        updatelname.clear();
        updatephone.clear();
    }

    @FXML
    private void newEmployee(ActionEvent actionEvent) throws IOException{
        Stage parent  = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("newEmployee.fxml"));
        fxmlLoader.setControllerFactory(applicationContext::getBean);
        Scene scene = new Scene(fxmlLoader.load());
        NewEmployee newEmployee = fxmlLoader.getController();
        newEmployee.setReturnScene(btnNew.getScene());
        parent.setScene(scene);

    }
}
