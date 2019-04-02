import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class GUI extends Application {

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


		btnSearch.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				// temp. outputs contexts of txt file
				String str = searchbar.getText();
				try {
					Searcher search = new Searcher(str);
					String[] aryLines = search.OpenFile();

					for (int i = 0; i < aryLines.length; i++) {
						//check if array[i] is null
						if (aryLines[i] == null) {
							System.out.println("");
						} else {
							System.out.println(aryLines[i]);

							//start sorting
							String unsorted = aryLines[i];
							Book toSort = new Book(unsorted);
							//print sorted
							for (int j = 0 ; j < toSort.sorter().size(); j++) {
								System.out.println(toSort.sorter().get(j));
							}
						}
					}
				} catch (IOException e1) {
					System.out.println(e1.getMessage());
				}

			}
		});

		// root pane
		final GridPane root = new GridPane();

		// background image
		Image img = new Image(new FileInputStream(
				"C:\\Users\\stottlern\\eclipse-workspace-spring-2019\\comp1050-Final\\images\\courtyard.jpg"));
		ImageView imgView = new ImageView(img);

		BorderPane background = new BorderPane();
		background.setCenter(imgView);

		// add any panes to root pane
		root.getChildren().addAll(background, searchButton);

		// make scene and add root pane
		Scene scene = new Scene(root, 975, 650);
		scene.getStylesheets().add("style.css");

		// primary stage
		primaryStage.setTitle("Hello World!");
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.show();

	}

}
