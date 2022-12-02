package application;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class operatorController implements Initializable 
{

	 @FXML
	 private Button closeButton;
	 static boolean answer;
	 
	 @FXML
	 private Button editMembers;
	 
	 @FXML
	 private Button editProviders;
	 
	 @FXML
	 private Button editManagers;
	 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}
	
   public void exitTerminal() throws Exception {
	   // Lets try to switch scenes
	   Parent newParent = FXMLLoader.load(getClass().getResource("/application/MyScene.fxml"));
	   
	   Scene newScene = new Scene(newParent);
	   
	   Stage window = (Stage) closeButton.getScene().getWindow();
	   window.setScene(newScene);
	   window.show();
	   
   }
   
   public void members() throws Exception
   {
	// Lets try to switch scenes
	   Parent newParent = FXMLLoader.load(getClass().getResource("/application/members.fxml"));
	   
	   Scene newScene = new Scene(newParent);
	   
	   Stage window = (Stage) closeButton.getScene().getWindow();
	   window.setScene(newScene);
	   window.show();
   }
   
   public void providers() throws Exception
   {
	// Lets try to switch scenes
	   Parent newParent = FXMLLoader.load(getClass().getResource("/application/providers.fxml"));
	   
	   Scene newScene = new Scene(newParent);
	   
	   Stage window = (Stage) closeButton.getScene().getWindow();
	   window.setScene(newScene);
	   window.show();
   }
   
   public void managers() throws Exception
   {
	// Lets try to switch scenes
	   Parent newParent = FXMLLoader.load(getClass().getResource("/application/managers.fxml"));
	   
	   Scene newScene = new Scene(newParent);
	   
	   Stage window = (Stage) closeButton.getScene().getWindow();
	   window.setScene(newScene);
	   window.show();
   }
 
	
	
	
}