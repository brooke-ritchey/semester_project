package edu.uh.tech.cis3368.semesterproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

@Component
public class JobManagement implements Initializable {
    private Scene scene;
    @Autowired
    ConfigurableApplicationContext applicationContext;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    JobRepository jobRepository;

    @FXML
    Button btnMain;
    @FXML
    Button btnNew;
    @FXML
    Button btnNewProd;
    @FXML
    TextField first;
    @FXML
    TextField last;
    @FXML
    TextField address;
    @FXML
    TextField phone;
    @FXML
    TextField name;
    @FXML
    TextArea desc;
    @FXML
    TextField stage;
    ObservableList<Job> jobs;
    ObservableList<Job> prejobs;
    @FXML
    TableColumn preName;
    @FXML
    TableColumn preDesc;
    @FXML
    TableView preprod;
    @Autowired
    ProductRepository productRepository;




    public void setReturnScene(Scene scene){
        this.scene = scene;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        jobs = FXCollections.observableArrayList();
        prejobs = FXCollections.observableArrayList();
        jobRepository.findAll().forEach(j -> jobs.add(j));
        jobs.stream().filter(j -> j.getStage() != "Pre-Production").forEach(j -> prejobs.add(j));
        preName.setCellValueFactory(new PropertyValueFactory<Job, String>("name"));
        preDesc.setCellValueFactory(new PropertyValueFactory<Job, String>("description"));
        preprod.getColumns();
        preprod.setItems(prejobs);
    }

    @FXML
    private void newJob(ActionEvent actionEvent) throws IOException {
        Stage parent  = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("newJob.fxml"));
        fxmlLoader.setControllerFactory(applicationContext::getBean);
        Scene scene = new Scene(fxmlLoader.load());
        JobManagement newEmployee = fxmlLoader.getController();
        newEmployee.setReturnScene(btnNew.getScene());
        parent.setScene(scene);
    }

    @FXML
    private void save(){
        Customer customer = new Customer();
        Job job = new Job();
        Product product = new Product();
        product.setName("name");
        product.setDescription("Desc");
            customer.setFirstName(first.getText());
            customer.setLastName(last.getText());
            customer.setAddress(address.getText());
            customer.setPhoneNumber(phone.getText());
            customerRepository.save(customer);

            job.setDescription(desc.getText());
            job.setName(name.getText());
            job.setStage(stage.getText());
            job.setProduct(product);
            job.setCustomer(customer);
            jobRepository.save(job);

        name.clear();
        desc.clear();
    }

    @FXML
    private void back(ActionEvent actionEvent) throws IOException {
        Stage parent  = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("jobManagement.fxml"));
        fxmlLoader.setControllerFactory(applicationContext::getBean);
        Scene scene = new Scene(fxmlLoader.load());
        JobManagement newEmployee = fxmlLoader.getController();
        newEmployee.setReturnScene(btnNew.getScene());
        parent.setScene(scene);
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
    private void newProduct(ActionEvent actionEvent) throws IOException{
        Stage parent  = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("newProduct.fxml"));
        fxmlLoader.setControllerFactory(applicationContext::getBean);
        Scene scene = new Scene(fxmlLoader.load());
        NewProduct newEmployee = fxmlLoader.getController();
        newEmployee.setReturnScene(btnNewProd.getScene());
        parent.setScene(scene);
    }
}
