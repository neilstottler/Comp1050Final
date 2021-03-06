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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class GUI extends Application {

	Scene homeScreen, scene2;

	public static void main(String[] args) {
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
		searchButton.setSpacing(30);

		// background image
		Image img = new Image(new FileInputStream(
				"images/courtyard.jpg"));
		ImageView imgView = new ImageView(img);
		BorderPane background = new BorderPane();
		background.setCenter(imgView);

		// add any panes to root pane
		final GridPane root = new GridPane();
		root.getChildren().addAll(background, searchButton);


		// make scene and add root pane
		Scene homeScreen = new Scene(root, 975, 650);
		primaryStage.setScene(homeScreen);

		// primary stage

		//scene2
		//  search button 2
		final Button btnSearch2 = new Button();
		btnSearch2.setText("Search");

		
		TextField searchbar2 = new TextField();
		searchbar2.setPrefWidth(500);


		HBox searchButton2 = new HBox(searchbar2, btnSearch2);
		searchButton2.setAlignment(Pos.BOTTOM_CENTER);
		searchButton2.setSpacing(50);

		// new gridpane for scene 2
		final GridPane root2 = new GridPane();
		root2.setHgap(10);
		root2.setVgap(10);


		// background
		Image img2 = new Image(new FileInputStream(
				"images/courtyard.jpg"));
		ImageView imgView2 = new ImageView(img2);
		BorderPane background2 = new BorderPane();
		background2.setCenter(imgView2);




		// ScrollPane
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(root2);

		// Pannable.
		scrollPane.setPannable(true);


		// Labels for the information of a book
		Label resultsSign = new Label("");

		Label extraspacing = new Label("                                                                ");
		Label author = new Label ("");
		Label book = new Label ("");
		Label serialNumber = new Label ("");
		Label abStract = new Label ("");
        Label mostrel = new Label ("          The Most Relevant Result");
        Label other = new Label("                     Other results");
        


		// setting fonts
		author.setFont(Font.font("Verdana", 20));
		book.setFont(Font.font("Verdana", 20));
		serialNumber.setFont(Font.font("Verdana", 20));
		abStract.setFont(Font.font("Verdana", 20));
		resultsSign.setFont(Font.font("Verdana",FontWeight.BOLD, 25));
		mostrel.setFont(Font.font("Verdana",FontWeight.BOLD, 25));
		other.setFont(Font.font("Verdana",FontWeight.BOLD, 25));

		TextArea allResults = new TextArea();
		allResults.setPrefWidth(500);
		allResults.setPrefHeight(400);
		
		
		// adding elements to GridPane
		root2.add(searchButton2, 2, 4);
		root2.add(mostrel,2, 10);
		root2.add(resultsSign, 2, 9);
		root2.add(author, 2, 16);
		root2.add(book, 2, 15);
		root2.add(serialNumber, 2, 14);
		root2.add(abStract, 2, 17);
		root2.add(extraspacing, 1, 15);
		root2.add(allResults, 2, 25);
		root2.add(other, 2, 22);


		root2.setStyle("-fx-background-image: url('" + background2 + "')");


		Scene scene2 = new Scene(scrollPane,975,650);


		/*
		 * 
		 * Searching Home Screen
		 * 
		 */
		btnSearch.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				//set displayed text to void in case of error/changes to book info
				author.setText("");
				book.setText("");
				serialNumber.setText("");
				abStract.setText("");

				int counter = 0;

				//check if search bar is empty
				if (searchbar.getText().equals("")) {
					primaryStage.setScene(homeScreen);

				} else {

					primaryStage.setScene(scene2);
					// temp. outputs contexts of txt file
					String str = searchbar.getText();
					try {
						Searcher search = new Searcher(str);
						String[] aryLines = search.OpenFile();

						
						allResults.clear();
						
						for (int i = 0; i < Searcher.getNumberOfLines(); i++) {

							//check if array[i] is null							
							if (aryLines[i] == null) {
								System.out.println("");
							} else {

								//start sorting
								String unsorted = aryLines[i];
								Book toSort = new Book(unsorted);

								
								if (aryLines[i].contains(str)) {
									counter++;
									//print sorted
									for (int j = 0; j < Searcher.getNumberOfLines(); j++) {						
										
										allResults.setText(allResults.getText() + "\n" + "ISBN: " +toSort.sorter().get(0));
										allResults.setText(allResults.getText() + "\n" + "Author: " + toSort.sorter().get(1));
										allResults.setText(allResults.getText() + "\n" + "Title: " +toSort.sorter().get(2));
										allResults.setText(allResults.getText() + "\n" +"Abstract: " + toSort.sorter().get(3) + "\n");
										
										if (!(toSort.sorter().size() < 4)) {

											//set text of lables to match search
											serialNumber.setText("ISBN: " + toSort.sorter().get(0));
											author.setText("Author: " + toSort.sorter().get(1));
											book.setText("Title: " + toSort.sorter().get(2));
											abStract.setText("Abstract: " + toSort.sorter().get(3));

											
											break;

										} else {
										
											serialNumber.setText("Error: " + aryLines[0]);
										}

									}
								}
							}
						}

						if (counter == 0) {
							//System.out.println("fucking");
							serialNumber.setText("Error: Did not find anything related to: ");
							allResults.setText("No Results");
							book.setText(str);
						}


					} catch (IOException e1) {
						System.out.println(e1.getMessage());
					}
				}
			}
		});
		/*
		 * 
		 * End Search button on Home Screen
		 * 
		 */

		/*
		 * 
		 * Searching result
		 * 
		 */
		btnSearch2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				allResults.clear();
				
				//set displayed text to void in case of error/changes to book info
				author.setText("");
				book.setText("");
				serialNumber.setText("");
				abStract.setText("");

				int counter = 0;

				//check if search bar is empty
				if (searchbar2.getText().equals("")) {
					primaryStage.setScene(scene2);
					serialNumber.setText("Please enter something into the search bar.");

				} else {

					primaryStage.setScene(scene2);
					// temp. outputs contexts of txt file
					String str = searchbar2.getText();
					try {
						Searcher search = new Searcher(str);
						String[] aryLines = search.OpenFile();

						for (int i = 0; i < Searcher.getNumberOfLines(); i++) {
							
							//check if array[i] is null							
							if (aryLines[i] == null) {
								System.out.println("");
							} else {

								//start sorting
								String unsorted = aryLines[i];
								Book toSort = new Book(unsorted);


								if (aryLines[i].contains(str)) {
									counter++;
									//print sorted
									for (int j = 0; j < Searcher.getNumberOfLines(); j++) {						

										if (!(toSort.sorter().size() < 4)) {

											//set text of lables to match search
											serialNumber.setText("ISBN: " + toSort.sorter().get(0));
											author.setText("Author: " + toSort.sorter().get(1));
											book.setText("Title: " + toSort.sorter().get(2));
											abStract.setText("Abstract: " + toSort.sorter().get(3));
											
											allResults.setText(allResults.getText() + "\n" + "ISBN: " +toSort.sorter().get(0));
											allResults.setText(allResults.getText() + "\n" + "Author: " + toSort.sorter().get(1));
											allResults.setText(allResults.getText() + "\n" + "Title: " +toSort.sorter().get(2));
											allResults.setText(allResults.getText() + "\n" +"Abstract: " + toSort.sorter().get(3) + "\n");

											break;

										} else {
											serialNumber.setText("Error: " + aryLines[0]);
										}

									}
								}
							}
						}

						if (counter == 0) {
							//System.out.println("fucking");
							serialNumber.setText("Error: Did not find anything related to: ");
							allResults.setText("No Results");
							book.setText(str);
						}


					} catch (IOException e1) {
						System.out.println(e1.getMessage());
					}
				}
			}
		});
		/*
		 * 
		 * End Search button on result
		 * 
		 */

		// make scene and add root pane
		primaryStage.setTitle("Book Search");
		primaryStage.setScene(homeScreen);
		primaryStage.sizeToScene();
		primaryStage.show();
	}
}