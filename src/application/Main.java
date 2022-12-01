package application;
	
import javafx.application.Application;
import javafx.geometry.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.scene.control.*;

public class Main extends Application {
	
	Stage window;
	Scene scene;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		window = primaryStage;
		window.setTitle("ChocAn Terminal - GUI");
		
		BorderPane border = new BorderPane();
		
		GridPane grid = new GridPane();
		
		grid.setPadding(new Insets(10,10,10,10));
		grid.setVgap(8);
		grid.setHgap(8);
		
		Label mainLabel = new Label("Welcome to the ChocAn User Interface");
		grid.getChildren().add(mainLabel);
		border.setCenter(grid);
		
		
		
		
		// Form 
		TextField nameInput = new TextField();
		
		 Button button = new Button("Click Me");
		 button.setOnAction(e -> isInt(nameInput,nameInput.getText()) );
		 
		 VBox layout = new VBox(10);
		 layout.setPadding(new Insets(20,20,20,20));
		 layout.getChildren().addAll(nameInput,button);
		 
		 scene = new Scene(border, 300,250);
		 window.setScene(scene);
		
		window.show();
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

}




/*
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
		
		
 */