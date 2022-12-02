package jt.chocolic;

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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class providerController implements Initializable 
{

	 @FXML
	 private Button closeButton;
	 
	 @FXML
	 private Button directory;
	 
	 @FXML
	 private Button billButton;
	 
	 
	 
	 static boolean answer;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}
	
   public void exitTerminal() throws Exception {
	   // Lets try to switch scenes
	   Parent newParent = FXMLLoader.load(getClass().getResource("MyScene.fxml"));
	   
	   Scene newScene = new Scene(newParent);
	   
	   Stage window = (Stage) closeButton.getScene().getWindow();
	   window.setScene(newScene);
	   window.show();
	   
   }
   
   public void startReporting() throws Exception 
   {
	// Lets try to switch scenes
	   Parent newParent = FXMLLoader.load(getClass().getResource("directoryView.fxml"));
	   
	   Scene newScene = new Scene(newParent);
	   
	   Stage window = (Stage) closeButton.getScene().getWindow();
	   window.setScene(newScene);
	   window.show();
   }
   
   
   public void beginBilling() throws Exception 
   {
	   Stage window = new Stage();
	   window.initModality(Modality.APPLICATION_MODAL);
	   window.setTitle("Edit Provider");
	   window.setMinWidth(250);
	   window.setMinHeight(400);

	   Label label1 = new Label();
	   label1.setText("Enter ID to Bill:");
	   Label label2 = new Label();
	   label2.setText("Enter Service Code:");

	   TextField idField = new TextField();
	   idField.setPromptText("ID");
	   TextField nameField = new TextField();
	   nameField.setPromptText("ServiceCode");

	   VBox stackLabels = new VBox(15);
	   stackLabels.getChildren().addAll(label1,label2);
	   stackLabels.setAlignment(Pos.CENTER);

	   VBox stackFields = new VBox(10);
	   stackFields.getChildren().addAll(idField,nameField);
	   stackFields.setAlignment(Pos.CENTER);

	   HBox layout = new HBox(10);
	   VBox realLayout = new VBox(10);
		Button update = new Button("Bill Member");
		update.setOnAction(e->window.close());
	   //Add buttons
	   layout.getChildren().addAll(stackLabels,stackFields);
	   layout.setAlignment(Pos.CENTER);

	   realLayout.getChildren().addAll(layout,update);
	   realLayout.setAlignment(Pos.CENTER);

	   Scene scene = new Scene(realLayout);
	   window.setScene(scene);
	   window.showAndWait();
   }
 
	
	
	
}