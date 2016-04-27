/******************************************************************************
 *  Compilation:  javac TextAnalysisApp.java
 *  Execution:    java TextAnalysisApp
 *  Dependencies: None
 *******************************************************************************/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 
 * The TextAnalysisApp program implements an application that finds and prints
 * each word with the number of occurrences from a given set of text files. It
 * also computes and prints the most repeated word and least repeated word for
 * each text file.
 * 
 * Assumptions: There are N text files which act as input & can be loaded as
 * required in the main method. There can be duplicate words in a text file,
 * therefore there can be multiple most repeated and least repeated words.
 *
 * @author Akshit Mahajan
 * @date March 06, 2016
 *
 */
public class TextAnalysisApp {

	private Map<String, Integer> wordMap; // stores all the words with occurrence count
									// from all the input files

	/**
	 * Parameterized constructor to call the private methods of this class which
	 * performs computations and prints the output.
	 * 
	 * @param pathList
	 */
	public TextAnalysisApp(Set<String> pathList) {
		wordMap = new HashMap<String, Integer>();
		Iterator<String> itr = pathList.iterator();

		while (itr.hasNext()) {
			readTextFile(itr.next().toString());
		}

		System.out
				.println("-----------------------------------------------------");
		printMap(wordMap);
		printMostAndLeastRepeatedWords(wordMap);
		System.out
				.println("-----------------------------------------------------");
	}

	/**
	 * This method performs I/O operations, reads the text files and adds each
	 * file data to the map
	 * 
	 * @param path
	 */
	private void readTextFile(String path) {
		String currentLine;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path));
			while ((currentLine = br.readLine()) != null) {
				String[] arrWords = currentLine.split(" ");
				for (int i = 0; i < arrWords.length; i++) {

					// check whether the word already exists or not
					String key = arrWords[i];
					if (wordMap.get(key) == null) {
						wordMap.put(key, 1);
					} else {
						int newCount = wordMap.get(key) + 1;
						wordMap.put(key, newCount);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Prints the map
	 * 
	 * @param map
	 */
	private void printMap(Map<String, Integer> map) {
		for (Entry<String, Integer> entry : map.entrySet()) {
			System.out.println("Word : " + entry.getKey() + " | Occurrence : "
					+ entry.getValue());
		}
	}

	/**
	 * This method computes & prints the Most Repeated words and the Least
	 * Repeated words from each text file.
	 * 
	 * @param map
	 *            with words and their occurrences
	 */
	private void printMostAndLeastRepeatedWords(Map<String, Integer> map) {

		int max = 0; // stores max count from the wordMap
		int min = 0; // stores the min count from the wordMap
		
		// list to hold most repeated words
		ArrayList<String> mostRepeatedWordsList = new ArrayList<String>(); 
		
		// list to hold least repeated words
		ArrayList<String> leastRepeatedWordsList = new ArrayList<String>(); 
		
		for (Entry<String, Integer> entry : map.entrySet()) {
			if (entry.getValue() > max) {
				max = entry.getValue();
			} else if (entry.getValue() < max) {
				if (min == 0) {
					min = entry.getValue();
				} else if (entry.getValue() < min) {
					min = entry.getValue();
				}
				else {}
			} else {}
		}

		for (Entry<String, Integer> entry : map.entrySet()) {
			if (entry.getValue() == max) {
				mostRepeatedWordsList.add(entry.getKey());
			}
			if (entry.getValue() == min) {
				leastRepeatedWordsList.add(entry.getKey());
			}
		}

		System.out.println("Most Repeated Words :");
		for (String mostRepeated : mostRepeatedWordsList) {
			System.out.println("| " + mostRepeated + " |");
		}

		System.out.println("Least Repeated Words :");
		for (String leastRepeated : leastRepeatedWordsList) {
			System.out.println("| " + leastRepeated + " |");
		}
	}

	/**
	 * This is the main method - execution point and behaves as test client
	 * Initializes a set of file paths
	 * 
	 * Assumption : For demonstration purpose, testing with 3 text files
	 * 
	 * @param args
	 *            unused
	 */
	public static void main(String[] args) {
		Set<String> paths = new HashSet<String>();
		paths.add("C:/Code/tmp/sample.txt");
		paths.add("C:/Code/tmp/sample1.txt");
		paths.add("C:/Code/tmp/chess.txt");
		new TextAnalysisApp(paths);
	}
}
