/******************************************************************************
 *  Compilation:  javac PlayerScoreApp.java
 *  Execution:    java PlayerScoreApp
 *  Dependencies: Player.java
 *******************************************************************************/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * The PlayerScoreApp program implements an application that finds and prints a
 * Player’s Final Score from a given list of Player scores.
 * 
 * A Player’s Final Score is calculated as the average of his N highest test
 * scores.
 * 
 * Assumptions: Each player has at least N = 5 test scores. Input is read from a
 * text file which contains player's data (Play date, Player ID, Player's
 * Score). Player data format : PlayDate,PlayerId,PlayerScore Date format :
 * dd-MMM-yyyy
 *
 * @author Akshit Mahajan
 * @date March 06, 2016
 *
 */
public class PlayerScoreApp {

	private Map<Integer, ArrayList<Double>> scoreReport; // saves the score
															// report of all the
															// players
	private int maxScoreCount; // no. of highest scores to be considered
	private String filePath; // path of input file location

	/**
	 * Initializes the instance variables
	 * 
	 * @param maxScoreCount
	 *            number of highest scores to be considered
	 * @param filePath
	 *            - path where input file exists
	 */
	public PlayerScoreApp(int maxScoreCount, String filePath) {
		this.filePath = filePath;
		this.maxScoreCount = maxScoreCount;
		scoreReport = new HashMap<Integer, ArrayList<Double>>();
	}

	/**
	 * Prints the Final Score of each player. Final Score is calculated as the
	 * average of his N highest test scores.
	 * 
	 * @param playerEntries
	 */
	public void printFinalScores(ArrayList<Player> playerEntries) {
		for (Player player : playerEntries) {
			ArrayList<Double> scores = new ArrayList<Double>();
			Integer playerId = player.getPlayerId();

			if (!scoreReport.containsKey(playerId)) {
				scores.add(player.getPlayerScore());
				scoreReport.put(playerId, scores);
			} else {
				ArrayList<Double> scoreList = scoreReport.get(playerId);
				scoreList.add(player.getPlayerScore());
			}
		}

		for (Entry<Integer, ArrayList<Double>> entry : scoreReport.entrySet()) {
			int playerId = entry.getKey();
			ArrayList<Double> scoreList = entry.getValue();
			Double[] scoreArray = new Double[scoreList.size()];

			scoreArray = scoreList.toArray(scoreArray);
			Double finalScore = getSumOfNMaxScores(scoreArray) / maxScoreCount;

			System.out.println("Final Score of Player " + playerId + " is "
					+ finalScore);
		}
	}

	/**
	 * Reads the player entries from the file using I/O operation and adds
	 * Player object in ArrayList
	 * 
	 * @return list of players
	 */
	private ArrayList<Player> readEntriesFromFile() {

		ArrayList<Player> playerList = new ArrayList<Player>();
		BufferedReader br = null;

		try {
			String currentLine;

			br = new BufferedReader(new FileReader(filePath));

			while ((currentLine = br.readLine()) != null) {
				String[] entry = currentLine.split(",");
				Player player_entry = null;
				for (Integer i = 0; i < entry.length; i++) {
					player_entry = new Player(new SimpleDateFormat(
							"dd-MMM-yyyy").parse(entry[0]),
							Integer.parseInt(entry[1]),
							Double.parseDouble(entry[2]));
				}
				playerList.add(player_entry);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return playerList;
	}

	/**
	 * Compute sum of N max scores
	 * 
	 * @param array
	 *            of scores
	 * @return the sum of N max numbers
	 */
	private double getSumOfNMaxScores(Double[] scoreArray) {

		Double max = 0.0;
		Double maxSum = 0.0;
		int index = 0;

		for (int i = 0; i < maxScoreCount; i++) {
			max = scoreArray[0];
			for (int j = 1; j < scoreArray.length; j++) {
				if (max < scoreArray[j]) {
					max = scoreArray[j];
					index = j;
				}
			}
			maxSum += max;
			scoreArray[index] = 0.0;
		}
		return maxSum;
	}

	/**
	 * This is the main method - execution point and behaves as test client
	 * Reads entries from file and save it in arrayList
	 * 
	 * Assumption : For demonstration purpose, testing with 1 input file
	 * 
	 * @param args
	 *            Unused.
	 */
	public static void main(String[] args) {
		int N = 5;
		String path = "C:/Code/tmp/player_scores.txt";
		PlayerScoreApp client = new PlayerScoreApp(N, path);
		try {
			ArrayList<Player> playerEntries = client.readEntriesFromFile();
			client.printFinalScores(playerEntries);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
}
