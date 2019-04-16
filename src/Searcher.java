import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Searcher {

	/**
	 * Currently has path defined with search path
	 */
	private static String path;
	private String searchinput;

	public Searcher(String input) {
		path = "books.txt";
		searchinput = input;
	}



	/**
	 * reads txt file and returns all 
	 * contents of lines 
	 * to be sorted in book class
	 * @return String[] textdata pulled from text file
	 * @throws IOException
	 */
	public String[] OpenFile() throws IOException {


		int counter = 0;

		BufferedReader textReader = new BufferedReader(new FileReader(path));

		int numberOfLines = readLines();
		String[] textData = new String[numberOfLines];
		
		String line = "";

		//set textData array to have each line in text file
		for (int i = 0; i < numberOfLines; i++) {
			textData[i] = textReader.readLine();
			//System.out.println("converting text line to array " + textData[i]);
		}
		

		//System.out.println("numberOfLines " + numberOfLines);


		for (int i = 0; i < numberOfLines; i++) {

			//line = textReader.readLine();
			//textData[counter] = line;

			//System.out.println("lines " + line);
			//System.out.println("textData before test "  + textData[i]);


			//check if search is in this line
			boolean contains = textData[i].contains(searchinput);
			if (contains) {
				counter++;
				//System.out.println("counter " + counter);
				//System.out.println("textData RETURN " + textData[i]);
				
			} else if (counter == 0 && !contains) {
				
				System.out.println("FUCK");
				
				if (counter > 0) {
					return textData;
				} else {
					for (int j = 0; j < numberOfLines; j++) {
						textData[0] = "Unable to find search query";
						textData[j] = "";
						textReader.close();
					}
					
					System.out.println("we here 3");
					//textData[0] = "Unknown Error";

					textReader.close();
					return textData;
				}

			}
			
			
			
		}

		return textData;



	}
	
	/**
	 * gets amount of lines in txt file because the
	 * file could have a infinite number of lines 
	 * we need to count them
	 * @return
	 * @throws IOException
	 */
	static int readLines() throws IOException {
		FileReader file_to_read = new FileReader(path);
		BufferedReader bf = new BufferedReader(file_to_read);

		String aLine;
		int numberOfLines = 0;

		while ((aLine = bf.readLine()) != null) {
			numberOfLines++;
		}
		bf.close();
		return numberOfLines;
	}

	
	/**
	 * return number of lines to read in file
	 * @return number of lines in text file to read
	 * @throws IOException 
	 */
	public static int getNumberOfLines() throws IOException {
		int numberOfLines = readLines();
		return numberOfLines;
	}



}