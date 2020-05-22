import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


/**
 * This program reads an input file, parses it into word tokens, count the
 * number of occurrence of each word and print them in descending order.
 */
public class WordFrequency {

	// Input file name
	private static final String FILE_NAME = "poem.txt";

	// Map to store the word and count in key,value pairs
	private Map<String, Integer> wordMap;

	// Default constructor
	public WordFrequency() {
		// initializing map
		wordMap = new HashMap<>();
	}

	/**
	 * This function reads file line by line, parse each line to get word tokens and
	 * put token into map.
	 * 
	 * @param fileName
	 */
	public void readFile(String fileName) {
		try {

			// Using buffered reader to read the file.
			BufferedReader reader = new BufferedReader(new FileReader(fileName));

			String line;

			// Looping through line by line till file has a line in it.
			while ((line = reader.readLine()) != null) {
				// Converting each line into lower case letters
				line = line.toLowerCase();
				// Removing all characters other than space, a-z or A-Z
				line = line.replaceAll("[^\\sa-zA-Z]", "");
				// Replacing all whitespaces occurring more than once into single space,
				// then trimming whitespaces from left, right side of the line
				line = line.replaceAll("\\s+", " ").trim();

				// If line is not empty
				if (!line.isEmpty()) {
					// splitting line into words considering space as the delimiter
					String[] s = line.split(" ");
					// For each word in array s, putting it into map
					for (String token : s) {
						// If word is already present, incrementing its count
						if (wordMap.containsKey(token)) {
							Integer count = wordMap.get(token);
							wordMap.put(token, count + 1);
						} else {
							// Otherwise, creating an entry in the map.
							wordMap.put(token, 1);
						}
					}
				}
			}

		} catch (FileNotFoundException ex) {

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void printFrequency() {

		// Creating an linked list to store all entry set
		List<Map.Entry<String, Integer>> linkedMap = new LinkedList<>(wordMap.entrySet());

		// Then linked list is sorted on the basis of value of each entry set, using
		// comparator
		// interface.
		Collections.sort(linkedMap, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return -1 * o1.getValue().compareTo(o2.getValue());
			}
		});

		int num = 1;
		// Printing each entry set present in the linked list.
		System.out.printf("%-5s %-10s %-10s\n", "No.", "Word", "Frequency");
		System.out.printf("%-30s\n", "----------------------------");
		for (Map.Entry<String, Integer> wordFreq : linkedMap) {
			System.out.printf("%-5d %-15s %-5d\n", num, wordFreq.getKey(), wordFreq.getValue());
			num++;
		}
	}

	/**
	 * Main method to test the WordFrequency class.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		WordFrequency wordFrequency = new WordFrequency();
		wordFrequency.readFile(FILE_NAME);
		wordFrequency.printFrequency();

	}

}
