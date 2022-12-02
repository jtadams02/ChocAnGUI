	package application;
	
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.geometry.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.scene.control.*;

public class Main extends Application {
	
	Stage window;
	Scene scene;
	TreeView<String> treeView;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		try {
            // Read file fxml and draw interface.
            Parent root = FXMLLoader.load(getClass()
                    .getResource("/application/MyScene.fxml"));

            primaryStage.setTitle("ChocAn GUI");
            primaryStage.setScene(new Scene(root,800,500));
            primaryStage.show();
        
        } catch(Exception e) {
            e.printStackTrace();
        }
	}
}




/*
 *  
 *  DISPLAYING INFO IN SUMMARY REPORT??
 *  USE A  TREE VIEW BABY WOOO
 *  	
 *  
 *  // Create Branches
	public TreeItem<String> makeBranch(String name, TreeItem<String> parent) 
	{
		TreeItem<String> item = new TreeItem<String>(name);
		item.setExpanded(true);
		parent.getChildren().add(item);
		return item;
	}
 *  
 *  ListView Stuff
 *  ListView<String> listView;
 *  //ListView
 *  
		 listView = new ListView<>();
		 listView.getItems().addAll("Iron Man","Titanic","Avengers","Contact","Sixth Sense");
		 listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
 *  
 *  	// Print out List View Method
	private void buttonClick() {
		String messageString = "";
		ObservableList<String> movieStrings;
		movieStrings = listView.getSelectionModel().getSelectedItems();
		
		for(String m:movieStrings) {
			messageString+=m+"\n";
		}
		System.out.println(messageString);
		
	}
 *  
 *  
 *  // Combo Box Baby
		 comboBox = new ComboBox<String>();
		 comboBox.getItems().addAll("Dead Poets Society","Good Will Hunting","Good Morning Vietnam");
		 
		 comboBox.setEditable(true);
		 
		 comboBox.setOnAction(e -> System.out.println("User selected: "+comboBox.getValue() ));
		  
 *  
 *  
 *  
 *  
 *  
 *  PASSWORD THING
 * window = primaryStage;
		window.setTitle("ChocAn Terminal - GUI");
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10,10,10,10));
		
		grid.setVgap(8);
		grid.setHgap(8);
		
		// Name label
		Label nameLabel = new Label("Username:");
		GridPane.setConstraints(nameLabel, 0, 0);
		
		// Name input
		TextField nameInput = new TextField("Default Text");
		GridPane.setConstraints(nameInput,1,0);
		
		//Password Label
		Label passwordLabel = new Label("Password: ");
		GridPane.setConstraints(passwordLabel, 0, 1);
		
		// Password Input 
		TextField passInput = new TextField();
		passInput.setPromptText("password");
		
		GridPane.setConstraints(passInput, 1, 1);
		
		Button loginButton = new Button("Login");
		GridPane.setConstraints(loginButton, 1, 2);
		
		grid.getChildren().addAll(nameLabel,nameInput,passwordLabel,passInput,loginButton);
		
		Scene scene = new Scene(grid,300,200);
		window.setScene(scene);
		
		
		
		window = primaryStage;
		window.setTitle("ChocAn Terminal - GUI");
		
		 Button button = new Button("Click Me");
		 button.setOnAction(e->setUserAgentStylesheet(STYLESHEET_CASPIAN));
		 Label label = new Label("Welcome to the ChocAn GUI Terminal!");
		 
		 // TreeView Time Baby
		 TreeItem<String>root,jt,tatum;
		 
		 // Root
		 root = new TreeItem<>();
		 root.setExpanded(true);
		 
		 // JT Branch
		 jt = makeBranch("JT",root);
		 makeBranch("Snapchat",jt);
		 makeBranch("Instagram",jt);
		 makeBranch("Steam",jt);
		 
		 // Tat Branch
		 tatum = makeBranch("Tatum",root);
		 makeBranch("Snapchat",tatum);
		 makeBranch("Instagram",tatum);
		 makeBranch("Steam",tatum);
		 
		 // Create tree
		 treeView = new TreeView<>(root);
		 treeView.setShowRoot(false);
		 treeView.getSelectionModel().selectedItemProperty().addListener((v,oldv,newv)->{
			 if(newv!=null) {System.out.println(newv.getValue());}
		 });
		 
		 VBox layout = new VBox(10);
		 layout.setPadding(new Insets(20,20,20,20));
		 layout.getChildren().addAll(button,label);
		 layout.setAlignment(Pos.CENTER);
		 
		 scene = new Scene(layout, 500,300);
		 
		 window.setScene(scene);
		
		window.show();
		
		
		
		
		
		
		// Create Branches
	public TreeItem<String> makeBranch(String name, TreeItem<String> parent) 
	{
		TreeItem<String> item = new TreeItem<String>(name);
		item.setExpanded(true);
		parent.getChildren().add(item);
		return item;
	}
	
	private boolean isInt(TextField input, String message) 
	{
		try {
			int age = Integer.parseInt(input.getText());	
			System.out.println("User is: "+age);
			return true;
		} 
		catch (NumberFormatException e)
		{
			System.out.println("Error: "+message+" is not a valid number.");
			return false;
		}
	}
		
 */