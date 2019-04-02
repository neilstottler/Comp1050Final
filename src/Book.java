import java.util.ArrayList;

public class Book {

	private String toSort;
	public Book(String unsorted) {
		System.out.println("book class in book constructure " + unsorted);
		toSort = unsorted;
	}
	
	public ArrayList sorter() {
		
		ArrayList<String> sorted = new ArrayList<>();
		String[] arraysort = toSort.split(",");
		
		for (int i = 0; i < arraysort.length; i++) {
			sorted.add(arraysort[i]); 
		}
		
		return sorted;
		
	}

}
