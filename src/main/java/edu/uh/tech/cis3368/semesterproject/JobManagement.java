package edu.uh.tech.cis3368.semesterproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

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
    ObservableList<Job> prodjobs;
    ObservableList<Job> closejobs;
    @FXML
    TableColumn preName;
    @FXML
    TableColumn preDesc;
    @FXML
    TableColumn prodName;
    @FXML
    TableColumn prodDesc;
    @FXML
    TableColumn closeName;
    @FXML
    TableColumn closeDesc;
    @FXML
    TableView<Job> preprod;
    @FXML
    TableView<Job> prod;
    @FXML
    TableView<Job> close;
    @Autowired
    ProductRepository productRepository;


    ObservableList<Temp> temps;
    ObservableList<Temp> usedtemps;
    ProductComponent productComponent;
    Temp temp;

    @Autowired
    ComponentRepository componentRepository;
    @Autowired
    ProductComponentRepository productComponentRepository;
    @Autowired
    TempRepository tempRepository;
    @FXML
    TableView complist;
    @FXML
    TableColumn compname;
    @FXML
    TableColumn compdesc;
    @FXML
    TableColumn compq;
    @FXML
    TextField prodname;
    @FXML
    TextArea proddesc;
    @FXML
    Label drop;
    @FXML
    Label cost;
    Job selectedjob;
    Job dragjob;


    public void setReturnScene(Scene scene){
        this.scene = scene;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        jobs = FXCollections.observableArrayList();
        prejobs = FXCollections.observableArrayList();
        prodjobs = FXCollections.observableArrayList();
        closejobs = FXCollections.observableArrayList();
        jobRepository.findAll().forEach(j -> jobs.add(j));
        jobs.stream().filter(j -> j.getStage() == 1).forEach(j -> prejobs.add(j));
        preName.setCellValueFactory(new PropertyValueFactory<Job, String>("name"));
        preDesc.setCellValueFactory(new PropertyValueFactory<Job, String>("description"));
        preprod.setItems(prejobs);

        jobs.stream().filter(j -> j.getStage() == 2).forEach(j -> prodjobs.add(j));
        prodName.setCellValueFactory(new PropertyValueFactory<Job, String>("name"));
        prodDesc.setCellValueFactory(new PropertyValueFactory<Job, String>("description"));
        prod.setItems(prodjobs);

        jobs.stream().filter(j -> j.getStage() == 3).forEach(j -> closejobs.add(j));
        closeName.setCellValueFactory(new PropertyValueFactory<Job, String>("name"));
        closeDesc.setCellValueFactory(new PropertyValueFactory<Job, String>("description"));
        close.setItems(closejobs);
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
            job.setStage(Integer.parseInt(stage.getText()));
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
        JobManagement jobEntry = fxmlLoader.getController();
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
        if(componentRepository.count() == 0){
            edu.uh.tech.cis3368.semesterproject.Component component = new edu.uh.tech.cis3368.semesterproject.Component();
            component.setName("comp1");
            component.setDescription("component 1");
            component.setWholesalePrice(new BigDecimal("10.25"));
            componentRepository.save(component);
            edu.uh.tech.cis3368.semesterproject.Component component2 = new edu.uh.tech.cis3368.semesterproject.Component();
            component2.setName("comp2");
            component2.setDescription("component 2");
            component2.setWholesalePrice(new BigDecimal("1.98"));
            componentRepository.save(component2);
        }

        selectedjob = preprod.getSelectionModel().getSelectedItem();
        if (selectedjob != null) {
            if(selectedjob.getProduct().getName() == "name") {
                Stage parent = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("newProduct.fxml"));
                fxmlLoader.setControllerFactory(applicationContext::getBean);
                Scene scene = new Scene(fxmlLoader.load());
                JobManagement newEmployee = fxmlLoader.getController();
                newEmployee.setReturnScene(btnNewProd.getScene());
                parent.setScene(scene);


                drop.setText(selectedjob.getName());

                complist.setEditable(true);
                compname.setCellValueFactory(new PropertyValueFactory<Temp, String>("name"));
                compdesc.setCellValueFactory(new PropertyValueFactory<Temp, String>("description"));
                compq.setCellValueFactory(new PropertyValueFactory<Temp, String>("quantity"));
                compq.setCellFactory(TextFieldTableCell.forTableColumn());
                compq.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Temp, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Temp, String> cellEditEvent) {
                        ((Temp) cellEditEvent.getTableView().getItems().get(cellEditEvent.getTablePosition().getRow()))
                                .setQuantity(cellEditEvent.getNewValue());
                        cost.setText(calcCost().toString());
                    }
                });

                temps = FXCollections.observableArrayList();

                componentRepository.findAll().forEach(c -> {
                    temp = new Temp();
                    temp.setName(c.getName());
                    temp.setDescription(c.getDescription());
                    temp.setQuantity("0");
                    temps.addAll(temp);
                });
                complist.setItems(temps);
            }
        }
    }
    @FXML
    private void saveProduct(){
        usedtemps = FXCollections.observableArrayList();
        usedtemps.addAll(temps.stream().filter(c -> c.getQuantity() != "0").collect(Collectors.toList()));
        Product product = selectedjob.getProduct();
        product.setName(prodname.getText());
        productRepository.save(product);
        selectedjob.setProduct(product);
        jobRepository.save(selectedjob);
        product.setDescription(proddesc.getText());
        usedtemps.forEach(t -> {
            edu.uh.tech.cis3368.semesterproject.Component component = new edu.uh.tech.cis3368.semesterproject.Component();
            component = componentRepository.findByName(t.getName());
            productComponent = new ProductComponent(Integer.parseInt(t.getQuantity()), component, product);
            productComponentRepository.save(productComponent);
        });
    }
    private BigDecimal calcCost(){
        BigDecimal whole = new BigDecimal("0");
        usedtemps = FXCollections.observableArrayList();
        usedtemps.addAll(temps.stream().filter(c -> c.getQuantity() != "0").collect(Collectors.toList()));
        for (Temp t : usedtemps) {

            edu.uh.tech.cis3368.semesterproject.Component component = new edu.uh.tech.cis3368.semesterproject.Component();
            component = componentRepository.findByName(t.getName());
            whole = whole.add(new BigDecimal(component.getWholesalePrice().toString()).multiply(new BigDecimal(t.getQuantity())));

        }
        BigDecimal markup = whole.multiply(new BigDecimal(".10"));
        BigDecimal total = whole.add(markup);
        BigDecimal tax = total.multiply(new BigDecimal(".034"));
        BigDecimal totalwtax = total.add(tax);
        return totalwtax;
    }

    public void dragDetected(MouseEvent mouseEvent) {
        dragjob = preprod.getSelectionModel().getSelectedItem();
        if(dragjob.getProduct().getName()!="name") {
            Dragboard dragboard = preprod.startDragAndDrop(TransferMode.COPY_OR_MOVE);
            ClipboardContent clipboardContent = new ClipboardContent();
            clipboardContent.putString(dragjob.getName());
            dragboard.setContent(clipboardContent);
            mouseEvent.consume();
        }
    }
    public void dragOver(DragEvent dragEvent) {
        Dragboard dragboard = dragEvent.getDragboard();
        if(dragboard.hasContent(DataFormat.PLAIN_TEXT)){
            dragEvent.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }
        dragEvent.consume();
    }
    public void dragDropped(DragEvent dragEvent) {
        Dragboard dragboard = dragEvent.getDragboard();
        var dragCompleted = false;
        if(dragboard.hasContent(DataFormat.PLAIN_TEXT)){
            dragjob.setStage(2);
            jobRepository.save(dragjob);
            prejobs.clear();
            jobs.stream().filter(j -> j.getStage() == 1).forEach(j -> prejobs.add(j));
            preprod.setItems(prejobs);
            prodjobs.clear();
            jobs.stream().filter(j -> j.getStage() == 2).forEach(j -> prodjobs.add(j));
            prod.setItems(prodjobs);
            prod.refresh();
            dragCompleted = true;
        }

        dragEvent.setDropCompleted(dragCompleted);
        dragEvent.consume();
    }
    public void dragDetectedProd(MouseEvent mouseEvent) {
        dragjob = prod.getSelectionModel().getSelectedItem();

            Dragboard dragboard = prod.startDragAndDrop(TransferMode.COPY_OR_MOVE);
            ClipboardContent clipboardContent = new ClipboardContent();
            clipboardContent.putString(dragjob.getName());
            dragboard.setContent(clipboardContent);
            mouseEvent.consume();
    }
    public void dragOverClose(DragEvent dragEvent) {
        if(dragjob.getStage() == 2) {
            Dragboard dragboard = dragEvent.getDragboard();
            if (dragboard.hasContent(DataFormat.PLAIN_TEXT)) {
                dragEvent.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            dragEvent.consume();
        }
    }
    public void dragDroppedClose(DragEvent dragEvent) {
        Dragboard dragboard = dragEvent.getDragboard();
        var dragCompleted = false;
        if(dragboard.hasContent(DataFormat.PLAIN_TEXT)){
            dragjob.setStage(3);
            jobRepository.save(dragjob);
            prodjobs.clear();
            jobs.stream().filter(j -> j.getStage() == 2).forEach(j -> prodjobs.add(j));
            prod.setItems(prodjobs);
            closejobs.clear();
            jobs.stream().filter(j -> j.getStage() == 3).forEach(j -> closejobs.add(j));
            close.setItems(closejobs);
            close.refresh();
            dragCompleted = true;
        }

        dragEvent.setDropCompleted(dragCompleted);
        dragEvent.consume();

    }
}
