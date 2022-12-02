package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MyController implements Initializable {
	static boolean answer;
   @FXML
   private Button exitButton;
 
   @FXML
   private Label titleLabel;
 
   @FXML
   private Button managerButton;
   
   @FXML
   private Button providerButton;
   
   @FXML
   private Button operatorButton;
   
   @Override
   public void initialize(URL location, ResourceBundle resources) {

       // TODO (don't really need to do anything here).
     
   }
   
   public void exitTerminal() {
	   Stage stage = (Stage) exitButton.getScene().getWindow();
	    // do what you have to do
	    stage.close();
   }
   
   public void managerAccess() throws Exception {
	  // Switch to the manager's terminal
	   if(display(3))
	   {
		   Parent newParent = FXMLLoader.load(getClass().getResource("/application/managerAccess.fxml"));
		   
		   Scene newScene = new Scene(newParent);
		   
		   Stage window = (Stage) managerButton.getScene().getWindow();
		   window.setScene(newScene);
		   window.show();
	   }
   }
   
   public void operatorAccess() throws Exception {
	   // Switch to the operator's terminal
	   if(display(1)) 
	   {
		   Parent newParent = FXMLLoader.load(getClass().getResource("/application/operatorAccess.fxml"));
		   
		   Scene newScene = new Scene(newParent);
		   
		   Stage window = (Stage) managerButton.getScene().getWindow();
		   window.setScene(newScene);
		   window.show();
	   }
	   
   }
   
   public void providerAccess() throws Exception {
	   // Switch to the provider's terminal
	   if(display(4)) 
	   {
		   Parent newParent = FXMLLoader.load(getClass().getResource("/application/providerAccess.fxml"));
		   
		   Scene newScene = new Scene(newParent);
		   
		   Stage window = (Stage) managerButton.getScene().getWindow();
		   window.setScene(newScene);
		   window.show();
	   }
   }
   public static void errorDisplay(String message) 
   {
	   Alert errorAlert = new Alert(AlertType.ERROR);
	   errorAlert.setHeaderText("ERROR");
	   errorAlert.setContentText(message);
	   errorAlert.showAndWait();
   }
   
   // This function pops up an alert, taking a username and password input, then checking to see if they are valid
   // Very fancy way to verify someone 
   public static boolean display(int accountType) {
	   answer=false;
	   Stage window = new Stage();
       window.initModality(Modality.APPLICATION_MODAL);
       window.setTitle("Login Panel");
       window.setMinWidth(250);
       Label label1 = new Label();
       label1.setText("Enter ID:");
       Label label2 = new Label();
       label2.setText("Enter Password");

       //Create login buttons
       Button login = new Button("Login");
       
       TextField user = new TextField();
       user.setPromptText("ID");
       
       TextField pass = new TextField();
       pass.setPromptText("Password");
       
       // Listen for ENTER to be pressed to submit
       user.setOnKeyPressed(e ->{
    	   if(e.getCode()==KeyCode.ENTER) 
    	   {
    		   if(!(user.getText().isEmpty())&&!(pass.getText().isEmpty())) 
    		   {
        		   // Finally made a helper function for parsing
    			   if(isInteger(user.getText())) 
    			   {
    				   int id = Integer.parseInt(user.getText());
    				   // If the account is an "operator", do this
    				   if(accountType==1) 
    				   {
    					   OperatorAccount temp = new OperatorAccount(id,pass.getText());
    					   if(temp.verify()) 
        				   {
        					   answer=true;
        				   } else {errorDisplay("Incorrect Information/Authentication Failed");}
    				   }
    				   // If the account is a "manager", do this
    				   if(accountType==3) 
            		   {
        				   ManagerAccount temp = new ManagerAccount(id, pass.getText());
        				   if(temp.verify()) 
        				   {
        					   answer=true;
        				   } else {errorDisplay("Incorrect Information/Authentication Failed");}
            		   }
    				   if(accountType==4) 
    				   {
    					   ProviderAccount temp = new ProviderAccount(id,pass.getText());
    					   if(temp.verify()) 
        				   {
        					   answer=true;
        				   } else {errorDisplay("Incorrect Information/Authentication Failed");}
    				   }
            		   window.close();
    			   } else 
    			   {
    				   errorDisplay("ID must be a number!");
    			   }
    		   }
    	   }
    	   
       });
       
       pass.setOnKeyPressed(e ->{
    	   if(e.getCode()==KeyCode.ENTER) 
    	   {
    		   // Make sure they aren't empty
    		   if(!(user.getText().isEmpty())&&!(pass.getText().isEmpty())) 
    		   {
        		   // Finally made a helper function for parsing
    			   if(isInteger(user.getText())) 
    			   {
    				   int id = Integer.parseInt(user.getText());
    				   // If the account is an "operator", do this
    				   if(accountType==1) 
    				   {
    					   OperatorAccount temp = new OperatorAccount(id,pass.getText());
    					   if(temp.verify()) 
        				   {
        					   answer=true;
        				   } else {errorDisplay("Incorrect Information/Authentication Failed");}
    				   }
    				   // If the account is a "manager", do this
    				   if(accountType==3) 
            		   {
        				   ManagerAccount temp = new ManagerAccount(id, pass.getText());
        				   if(temp.verify()) 
        				   {
        					   answer=true;
        				   } else {errorDisplay("Incorrect Information/Authentication Failed");}
            		   }
    				   
    				   if(accountType==4) 
    				   {
    					   ProviderAccount temp = new ProviderAccount(id,pass.getText());
    					   if(temp.verify()) 
        				   {
        					   answer=true;
        				   } else {errorDisplay("Incorrect Information/Authentication Failed");}
    				   }
            		   window.close();
    			   } else 
    			   {
    				   errorDisplay("ID must be a number!");
    			   }
    		   }
    	   }
       });
       
       

       //
       login.setOnAction(e -> {
    	   // Make sure they aren't empty
    	   if(!(user.getText().isEmpty())&&!(pass.getText().isEmpty())) 
		   {
    		   // Finally made a helper function for parsing
			   if(isInteger(user.getText())) 
			   {
				   int id = Integer.parseInt(user.getText());
				   // If the account is an "operator", do this
				   if(accountType==1) 
				   {
					   OperatorAccount temp = new OperatorAccount(id,pass.getText());
					   if(temp.verify()) 
    				   {
    					   answer=true;
    				   } else {errorDisplay("Incorrect Information/Authentication Failed");}
				   }
				   // If the account is a "manager"
				   if(accountType==3) 
        		   {
    				   ManagerAccount temp = new ManagerAccount(id, pass.getText());
    				   if(temp.verify()) 
    				   {
    					   answer=true;
    				   } else {errorDisplay("Incorrect Information/Authentication Failed");}
        		   } 
				   if(accountType==4) 
				   {
					   ProviderAccount temp = new ProviderAccount(id,pass.getText());
					   if(temp.verify()) 
    				   {
    					   answer=true;
    				   } else {errorDisplay("Incorrect Information/Authentication Failed");}
				   }
        		   window.close();
			   }	 else 
			   {
				   errorDisplay("ID must be a number!");
			   }
		   }
       });
       
       VBox stackLabels = new VBox(10);
       stackLabels.getChildren().addAll(label1,label2);
       stackLabels.setAlignment(Pos.CENTER);
       
       VBox stackFields = new VBox(10);
       stackFields.getChildren().addAll(user,pass);
       stackFields.setAlignment(Pos.CENTER);
       
       HBox layout = new HBox(10);
       VBox realLayout = new VBox(10);
       
       //Add buttons
       layout.getChildren().addAll(stackLabels,stackFields);
       layout.setAlignment(Pos.CENTER);
       
       realLayout.getChildren().addAll(layout,login);
       realLayout.setAlignment(Pos.CENTER);
       
       Scene scene = new Scene(realLayout);
       window.setScene(scene);
       window.showAndWait();

       //Make sure to return answer
       return answer;
   }
   
   
   
   public static boolean isInteger(String str) {
	    try {
	        Integer.parseInt(str);
	        return true;
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	}
 
}