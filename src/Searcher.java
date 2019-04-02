import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Searcher {

	/**
	 * Currently has path defined with search path
	 */
	private String path;
	private String searchinput;

	public Searcher(String input) {
		path = "C:/Users/stottlern/eclipse-workspace-spring-2019/comp1050-Final/filename.txt";
		searchinput = input;
	}

	/**
	 * reads txt file and returns all contents of lines. to be sorted in book class
	 * @return
	 * @throws IOException
	 */
	public String[] OpenFile() throws IOException {

		FileReader fr = new FileReader(path);
		BufferedReader textReader = new BufferedReader(fr);

		int numberOfLines = readLines();
		String[] textData = new String[numberOfLines];

		int i;

		for (i = 0; i < numberOfLines; i++) {
			textData[i] = textReader.readLine();
			

			//check if string is in line searching
			boolean contains = textData[i].contains(searchinput);
			if (contains) {
				textReader.close();
				return textData;
			} else {
				for (i = 0; i < numberOfLines; i++) {
					textData[i] = "";
				}
				textData[0] = "Unable to find search query";
				textReader.close();
				return textData;
			}
			
		}

		textReader.close();
		
		//debugging
		for (i = 0; i < numberOfLines; i++) {
			textData[i] = "";
		}
		textData[0] = "debugging";
		return textData;
		
	}

	/**
	 * gets amount of lines in txt file because the file could have a infinite number of lines we need to count them
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