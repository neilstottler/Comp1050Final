import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.util.Scanner;

public class Searcher {

	public void parseFile(String fileName,String searchStr) throws FileNotFoundException{
		Scanner scan = new Scanner(new File(fileName));
		while(scan.hasNext()){
			String line = scan.nextLine().toLowerCase().toString();
			if(line.contains(searchStr)){
				System.out.println(line);
			}
		}
	}


	//public static void main(String[] args) throws FileNotFoundException{
	//    fileSearch fileSearch = new Files();
	//    fileSearch.parseFile("src/test.txt", "am");
	//}
	public static void main(String[] args) throws IOException {
		
		//write to file
//		Writer writer = null;

//		try {
//			writer = new BufferedWriter(new OutputStreamWriter(
//					new FileOutputStream("filename.txt"), "utf-8"));
//			writer.write("Something");
//		} catch (IOException ex) {
			// Report
//		} finally {
//			try {writer.close();} catch (Exception ex) {/*ignore*/}
//		}


		//file reader
		FileReader fr = 
				new FileReader("filename.txt"); 

		int i; 
		while ((i=fr.read()) != -1) 
			System.out.print((char) i); 

	}
}

