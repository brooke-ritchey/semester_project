package edu.uh.tech.cis3368.semesterproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.print.Book;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Component
public class NewProduct implements Initializable{
    private Scene scene;
    ObservableList<edu.uh.tech.cis3368.semesterproject.Component> compnents;
    ObservableList<Job> jobs;
    ObservableList<Temp> temps;
    ObservableList<Temp> usedtemps;
    edu.uh.tech.cis3368.semesterproject.Component component;
    ProductComponent productComponent;
    Product product;
    Temp temp;

    @Autowired
    ComponentRepository componentRepository;
    @Autowired
    ProductComponentRepository productComponentRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    TempRepository tempRepository;
    @Autowired
    JobRepository jobRepository;
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
    ComboBox<Job> drop;
    @FXML
    Label cost;


    public void setReturnScene(Scene scene){
        this.scene = scene;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        jobs = FXCollections.observableArrayList();
        jobRepository.findAll().forEach(j -> jobs.add(j));
        drop.setItems(jobs);
        complist.setVisible(false);
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

        complist.setVisible(true);
        componentRepository.findAll().forEach(c -> {
            temp = new Temp();
            temp.setName(c.getName());
            temp.setDescription(c.getDescription());
            temp.setQuantity("0");
            temps.addAll(temp);
        });
        complist.setItems(temps);
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

    @FXML
    private void save(){
        Job job = drop.getSelectionModel().getSelectedItem();

        usedtemps = FXCollections.observableArrayList();
        usedtemps.addAll(temps.stream().filter(c -> c.getQuantity() != "0").collect(Collectors.toList()));
        Product product = new Product();
        product.setName(prodname.getText());
        productRepository.save(product);
        job.setProduct(product);
        jobRepository.save(job);
        product.setDescription(proddesc.getText());
        usedtemps.forEach(t -> {
            edu.uh.tech.cis3368.semesterproject.Component component = new edu.uh.tech.cis3368.semesterproject.Component();
            component = componentRepository.findByName(t.getName());
            productComponent = new ProductComponent(Integer.parseInt(t.getQuantity()), component, product);
            productComponentRepository.save(productComponent);
        });
    }
}
