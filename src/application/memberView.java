package application;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class memberView implements Initializable{

	 // On push, executes exitTerminal()
	 @FXML
	 private Button closeButton;
	 
	// On push, executes goBack()
	 @FXML
	 private Button backButton;
	 
	// On push, executes CreateTree()
	 @FXML
	 private Button view;
	 
	// On push, executes editManager()
	 @FXML
	 private Button edit;
	 
	 // On push, executes addManager()
	 @FXML
	 private Button add;
	 
	// On push, executes deleteManager()
	 @FXML
	 private Button delete;
	 
	 @FXML
	 private Button suspend;
	 
	 @FXML 
	 private Button reinstate;
	 
	 @FXML
	 private VBox treeBox;
	 
	 // Our tree view
	 @FXML
	 TreeView selectionTreeView;
	 @FXML
	 private void handleButtonAction(ActionEvent event) {
	     createTree();
	 }
	 
	 public void createTree(String... rootItems) {
		    //create root
		 	List<MemberAccount> list = MemberAccount.getMemberAccounts();
		    TreeItem<String> root = new TreeItem<>("Root");
		    root.setExpanded(true);
		    //root.setExpanded(true);
		    //create child
			for(int i=0;i<list.size();i++) 
			{
				MemberAccount dis = list.get(i);
				String id = Integer.toString(dis.idNumber);
				String idd = "ID: "+ id;
				TreeItem<String> item = new TreeItem<String>(idd);
				item.getChildren().add(new TreeItem<String>("Name: "+dis.name));
				item.getChildren().add(new TreeItem<String>("Address: "+ dis.address));
				item.getChildren().add(new TreeItem<String>("City: "+dis.city));
				item.getChildren().add(new TreeItem<String>("State: " +dis.state));
				item.getChildren().add(new TreeItem<String>("Zipcode: "+ Integer.toString(dis.zip)));
				item.getChildren().add(new TreeItem<String>("Fees: "+Float.toString(dis.feesOwed)));
				item.getChildren().add(new TreeItem<String>("Suspended: "+String.valueOf(dis.isSuspended)));
				
				item.setExpanded(true);
				
				root.getChildren().add(item);
			
			}
		    //root is the parent of itemChild
		    selectionTreeView.setRoot(root);
		    selectionTreeView.setShowRoot(false);
		}
	 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{	
		
	}
	
	 public static void errorDisplay(String message) 
	   {
		   Alert errorAlert = new Alert(AlertType.ERROR);
		   errorAlert.setHeaderText("ERROR");
		   errorAlert.setContentText(message);
		   errorAlert.showAndWait();
	   }
	 public static void sucessDisplay(String message) 
	   {
		   Alert errorAlert = new Alert(AlertType.NONE);
		   errorAlert.setHeaderText("SUCESS");
		   errorAlert.setContentText(message);
		   errorAlert.showAndWait();
	   }
	
	public void exitTerminal() throws Exception {
		   // Lets try to switch scenes
		   Parent newParent = FXMLLoader.load(getClass().getResource("/application/MyScene.fxml"));
		   
		   Scene newScene = new Scene(newParent);
		   
		   Stage window = (Stage) closeButton.getScene().getWindow();
		   window.setScene(newScene);
		   window.show();
		   
	   }
	
	public void goBack() throws Exception 
	   {
		   Parent newParent = FXMLLoader.load(getClass().getResource("/application/operatorAccess.fxml"));
		   
		   Scene newScene = new Scene(newParent);
		   
		   Stage window = (Stage) closeButton.getScene().getWindow();
		   window.setScene(newScene);
		   window.show();
	   }
	
	public void edit() 
	{
		   Stage window = new Stage();
	       window.initModality(Modality.APPLICATION_MODAL);
	       window.setTitle("Edit Member");
	       window.setMinWidth(250);
	       window.setMinHeight(400);
	       
	       Label label1 = new Label();
	       label1.setText("Enter ID to update:");
	       Label label2 = new Label();
	       label2.setText("Update Name:");
	       Label label22 = new Label("Update Password");
	       Label label3 = new Label();
	       label3.setText("Update Address:");
	       Label label4 = new Label();
	       label4.setText("Update City:");
	       Label label5 = new Label();
	       label5.setText("Update State:");
	       Label label6 = new Label();
	       label6.setText("Update Zip:");
	       Label label7 = new Label("Enter fee's (0 by Default)");
	       
	       TextField idField = new TextField();
	       idField.setPromptText("ID");
	       TextField nameField = new TextField();
	       nameField.setPromptText("Name");
	       TextField passwordField = new TextField();
	       passwordField.setPromptText("Password");
	       TextField addressField = new TextField();
	       addressField.setPromptText("Address");
	       TextField cityField = new TextField();
	       cityField.setPromptText("City");
	       TextField stateField = new TextField();
	       stateField.setPromptText("State");
	       TextField zipField = new TextField();
	       zipField.setPromptText("Zipcode");
	       TextField floatField = new TextField();
	       floatField.setText("0");
	       
	       Button update = new Button("Update Member");
	       update.setOnAction(e->{
	    	   int id = 0;
	    	   int zip = 0;
	    	   float floaty = 0;
	    	   
		    	try {
		    		id = Integer.parseInt(idField.getText());
				} catch (NumberFormatException nfe) {
					errorDisplay("ID must be a number!");
					return;
				}
		    	
		    	try {
		    		zip = Integer.parseInt(zipField.getText());
				} catch (NumberFormatException nfe) {
					errorDisplay("Zipcode must be a number!");
					return;
				}
		    	try {
		    		floaty = Float.parseFloat(floatField.getText());
		    	} catch (NumberFormatException nfe) {
		    		errorDisplay("The fee must be a number!");
					return;
		    	}
		    	
	    	   MemberAccount updater = new MemberAccount(id);
	    	   updater.updateMember(nameField.getText(),passwordField.getText(), addressField.getText(), cityField.getText(), stateField.getText(),zip,floaty);
	    	   // sucessDisplay("Manager has been updated in Database");
	    	   window.close();
	       });  
	       
	       VBox stackLabels = new VBox(15);
	       stackLabels.getChildren().addAll(label1,label2,label22,label3,label4,label5,label6,label7);
	       stackLabels.setAlignment(Pos.CENTER);
	       
	       VBox stackFields = new VBox(10);
	       stackFields.getChildren().addAll(idField,nameField,passwordField,addressField,cityField,stateField,zipField,floatField);
	       stackFields.setAlignment(Pos.CENTER);
	       
	       HBox layout = new HBox(10);
	       VBox realLayout = new VBox(10);
	       
	       //Add buttons
	       layout.getChildren().addAll(stackLabels,stackFields);
	       layout.setAlignment(Pos.CENTER);
	       
	       realLayout.getChildren().addAll(layout,update);
	       realLayout.setAlignment(Pos.CENTER);
	       
	       Scene scene = new Scene(realLayout);
	       window.setScene(scene);
	       window.showAndWait();		
	}
	
	public void add() 
	{
		Stage window = new Stage();
	       window.initModality(Modality.APPLICATION_MODAL);
	       window.setTitle("Add Member");
	       window.setMinWidth(250);
	       window.setMinHeight(400);
	       
	       Label title = new Label("Add a new Member!");
	       Label label1 = new Label();
	       label1.setText("Enter a name:");
	       Label label11 = new Label("Enter a password:");
	       Label label2 = new Label();
	       label2.setText("Enter an address:");
	       Label label3 = new Label();
	       label3.setText("Enter a city:");
	       Label label4 = new Label();
	       label4.setText("Enter a state:");
	       Label label5 = new Label();
	       label5.setText("Enter a zip:");
	       Label label6 = new Label("Enter fee's (0 by Default)");
	      
	       
	       TextField nameField = new TextField();
	       nameField.setPromptText("Name");
	       TextField passwordField = new TextField();
	       passwordField.setPromptText("Password");
	       TextField addressField = new TextField();
	       addressField.setPromptText("Address");
	       TextField cityField = new TextField();
	       cityField.setPromptText("City");
	       TextField stateField = new TextField();
	       stateField.setPromptText("State");
	       TextField zipField = new TextField();
	       zipField.setPromptText("Zipcode");
	       TextField floatField = new TextField();
	       floatField.setText("0");
	       
	       HBox buttonBox = new HBox(5);
	       
	       Button update = new Button("Add Member");
	       update.setOnAction(e->{
	    	   int zip = 0;
		    	try {
		    		zip = Integer.parseInt(zipField.getText());
				} catch (NumberFormatException nfe) {
					errorDisplay("Zipcode must be a number!");
					return;
				}
		    	float floaty = 0;
		    	try {
		    		floaty = Float.parseFloat(floatField.getText());
		    	} catch (NumberFormatException nfe) {
		    		errorDisplay("The fee must be a number!");
					return;
		    	}
		    	
	    	   MemberAccount updater = new MemberAccount();
	    	   updater.addMemberAccount(nameField.getText(), passwordField.getText(),addressField.getText(), cityField.getText(), stateField.getText(),zip,floaty);
	    	   window.close();
	       });  
	       
	       VBox stackLabels = new VBox(15);
	       stackLabels.getChildren().addAll(label1,label2,label3,label4,label5,label6);
	       stackLabels.setAlignment(Pos.CENTER);
	       
	       VBox stackFields = new VBox(10);
	       stackFields.getChildren().addAll(nameField,addressField,cityField,stateField,zipField,floatField,buttonBox);
	       stackFields.setAlignment(Pos.CENTER);
	       
	       HBox layout = new HBox(10);
	       VBox realLayout = new VBox(10);
	       
	       //Add buttons
	       layout.getChildren().addAll(stackLabels,stackFields);
	       layout.setAlignment(Pos.CENTER);
	       
	       realLayout.getChildren().addAll(title,layout,update);
	       realLayout.setAlignment(Pos.CENTER);
	       
	       Scene scene = new Scene(realLayout);
	       window.setScene(scene);
	       window.showAndWait();	

		
	}
	
	public void delete()
	{
			Stage window = new Stage();
		   window.initModality(Modality.APPLICATION_MODAL);
	       window.setTitle("Delete Member");
	       window.setMinWidth(250);
	       window.setMinHeight(400);
	       
	       Label idLabel = new Label("Enter ID to be Deleted:");
	       TextField idField = new TextField();
	       idField.setPromptText("ID");
	       
	       Button delete = new Button("Delete Member");
	       
	       delete.setOnAction(e->{
	    	   int id = 0;
	    	   try {
		    		id = Integer.parseInt(idField.getText());
				} catch (NumberFormatException nfe) {
					errorDisplay("ID must be a number!");
					return;
				}
	    	   MemberAccount update = new MemberAccount(id);
	    	   update.deleteMember(id);
	    	   window.close();
	       });
	       
	       HBox stuff = new HBox(10);
	       stuff.getChildren().addAll(idLabel,idField);
	       stuff.setAlignment(Pos.CENTER);
	       
	       VBox layout = new VBox(15);
	       layout.getChildren().addAll(stuff,delete);
	       layout.setAlignment(Pos.CENTER);
	       
	       Scene scene = new Scene(layout);
	       window.setScene(scene);
	       window.showAndWait();
	       
	       
		
	}
	
	public void suspend() 
	{
		Stage window = new Stage();
		   window.initModality(Modality.APPLICATION_MODAL);
	       window.setTitle("Suspend Member");
	       window.setMinWidth(250);
	       window.setMinHeight(400);
	       
	       Label idLabel = new Label("Enter ID to be Suspended:");
	       TextField idField = new TextField();
	       idField.setPromptText("ID");
	       
	       Button suspend = new Button("Suspend Member");
	       
	       suspend.setOnAction(e->{
	    	   int id = 0;
	    	   try {
		    		id = Integer.parseInt(idField.getText());
				} catch (NumberFormatException nfe) {
					errorDisplay("ID must be a number!");
					return;
				}
	    	   MemberAccount update = new MemberAccount(id);
	    	   update.suspendAccount(update.idNumber);
	    	   window.close();
	       });
	       
	       HBox stuff = new HBox(10);
	       stuff.getChildren().addAll(idLabel,idField);
	       stuff.setAlignment(Pos.CENTER);
	       
	       VBox layout = new VBox(15);
	       layout.getChildren().addAll(stuff,suspend);
	       layout.setAlignment(Pos.CENTER);
	       
	       Scene scene = new Scene(layout);
	       window.setScene(scene);
	       window.showAndWait();
	}
	
	public void reinstate() 
	{
		Stage window = new Stage();
		   window.initModality(Modality.APPLICATION_MODAL);
	       window.setTitle("Reinstate Member");
	       window.setMinWidth(250);
	       window.setMinHeight(400);
	       
	       Label idLabel = new Label("Enter ID to be Reinstated:");
	       TextField idField = new TextField();
	       idField.setPromptText("ID");
	       
	       Button reinstate = new Button("Reinstate Member");
	       
	       reinstate.setOnAction(e->{
	    	   int id = 0;
	    	   try {
		    		id = Integer.parseInt(idField.getText());
				} catch (NumberFormatException nfe) {
					errorDisplay("ID must be a number!");
					return;
				}
	    	   MemberAccount update = new MemberAccount(id);
	    	   update.reinstateAccount(id);
	    	   window.close();
	       });
	       
	       HBox stuff = new HBox(10);
	       stuff.getChildren().addAll(idLabel,idField);
	       stuff.setAlignment(Pos.CENTER);
	       
	       VBox layout = new VBox(15);
	       layout.getChildren().addAll(stuff,reinstate);
	       layout.setAlignment(Pos.CENTER);
	       
	       Scene scene = new Scene(layout);
	       window.setScene(scene);
	       window.showAndWait();
	}
	
}