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
	private String path;
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


		BufferedReader textReader = new BufferedReader(new FileReader(path));

		int numberOfLines = readLines();
		String[] textData = new String[numberOfLines];

		
		String line;
		for (int i = 0; i < numberOfLines; i++) {
			line = textReader.readLine();
			textData[i] = line;

			//check if search is in this line
			boolean contains = textData[i].contains(searchinput);
			if (contains) {
				textReader.close();
				return textData;
			}
		}

		//if not in give error
		for (int j = 0; j < textData.length; j++) {
			textData[0] = "Unable to find search query";
			textData[j] = "";
			textReader.close();
			return textData;
		}
		
		System.out.println("we here 3");
		textData[0] = "Unknown Error";
		textReader.close();
		return textData;


	}

	/**
	 * gets amount of lines in txt file because the
	 * file could have a infinite number of lines 
	 * we need to count them
	 * @return
	 * @throws IOException
	 */
	int readLines() throws IOException {
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

}