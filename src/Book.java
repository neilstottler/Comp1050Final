import java.util.ArrayList;

/**
 * This class is the sorting method
 * for the information grabbed
 * by the Searcher class
 * @author stottlern
 *
 */
public class Book {

	private String toSort;
	public Book(String unsorted) {
		toSort = unsorted;
	}
	
	/**
	 * sorts info from string[] to ArrayList
	 * @return book information sorted
	 */
	public ArrayList<String> sorter() {
		
		ArrayList<String> sorted = new ArrayList<>();
		String[] arraysort = toSort.split(",");
		
		for (int i = 0; i < arraysort.length; i++) {
			sorted.add(arraysort[i]); 
		}
		
		return sorted;
		
	}

}
