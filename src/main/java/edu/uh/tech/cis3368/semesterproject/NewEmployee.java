package edu.uh.tech.cis3368.semesterproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class NewEmployee implements Initializable {
    @Autowired
    EmployeeRepository employeeRepository;
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
    Button btnMain;

    private Scene scene;

    @Autowired
    ConfigurableApplicationContext applicationContext;

    public void setReturnScene(Scene scene){
        this.scene = scene;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    private void saveEmp() throws IOException {

        Employee employee = new Employee();

        employee.setFirstName(firstname.getText().toString());
        employee.setLastName(lastname.getText().toString());
        employee.setEmail(email.getText().toString());
        employee.setPhone(phone.getText().toString());
        employeeRepository.save(employee);
        firstname.clear();
        lastname.clear();
        email.clear();
        phone.clear();


    }

    @FXML
    private void mainMenu(ActionEvent actionEvent) throws IOException{
        Stage parent  = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("employeeManagement.fxml"));
        fxmlLoader.setControllerFactory(applicationContext::getBean);
        Scene scene = new Scene(fxmlLoader.load());
        EmployeeManagement employeeManagement = fxmlLoader.getController();
        employeeManagement.setReturnScene(btnMain.getScene());
        parent.setScene(scene);
    }
}
