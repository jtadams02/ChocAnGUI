package application;

import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

public class Controller implements Initializable {
	
    // This runs code behind the scenes, ie could connect to a db before view loads! pretty swag huh
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("View is now loaded!");
        
    }
    
    
    public void loginButtonClicked() {
    	System.out.println("SWAG IN OHIO");
    	
    }
    

    

    
    

}