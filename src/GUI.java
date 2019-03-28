import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

public class GUI extends Application{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws FileNotFoundException {
		
		
		final Button btnSearch = new Button();
		btnSearch.setText("Search");
		
		TextField searchbar = new TextField();
		searchbar.setPrefWidth(500);
		
		HBox searchButton = new HBox(searchbar, btnSearch);
		searchButton.setAlignment(Pos.CENTER);
		searchButton.setSpacing(50);
		
		
		
		//root pane
		final GridPane root = new GridPane();
		
		//background image
		Image img = new Image(new FileInputStream("C:\\Users\\stottlern\\eclipse-workspace-spring-2019\\comp1050-Final\\images\\courtyard.jpg"));
		ImageView imgView = new ImageView(img);
		
		BorderPane background = new BorderPane();
		background.setCenter(imgView);

		//add any panes to root pane
		root.getChildren().addAll(background, searchButton);
		
		//make scene and add root pane
		Scene scene = new Scene(root, 975,650);
		scene.getStylesheets().add("style.css");
		
		//primary stage
		primaryStage.setTitle("Hello World!");
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.show();
	}

}
