package edu.uh.tech.cis3368.semesterproject;

import javafx.fxml.Initializable;
import javafx.scene.Scene;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class JobManagement implements Initializable {
    private Scene scene;




    public void setReturnScene(Scene scene){
        this.scene = scene;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
