package com.programming.problems;
/******************************************************************************
 *  Compilation:  javac Player.java
 *  Execution:    None
 *  Dependencies: None
 *******************************************************************************/


import java.util.Date;

/**
 * POJO class containing Player Data
 * 
 * @author Akshit Mahajan
 * @date March 06, 2016
 */
public class Player {
	private int playerId;		// player's unique id
	private double playerScore;		// player's test score
	private Date playDate;		// date on which score was recorded
	
	/**
	 * Initializes instance variables
	 * 
	 * @param date
	 * @param playerId
	 * @param playerScore
	 */
	public Player(Date date, int playerId, double playerScore) {
		this.playerId = playerId;
		this.playerScore = playerScore;
		this.playDate = date;
	}
	
	/**
	 * Public method to get player id
	 * 
	 * @return int playerId
	 */
	public int getPlayerId() {
		return playerId;
	}
	
	/**
	 * Public method to set player id
	 */
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	
	/**
	 * Public method to get player's score
	 * 
	 * @return double playScore
	 */
	public double getPlayerScore() {
		return playerScore;
	}
	
	/**
	 * Public method to set player's score
	 */
	public void setPlayerScore(double playerScore) {
		this.playerScore = playerScore;
	}
	
	/**
	 * Public method to get Play Date
	 * 
	 * @return Date playDate
	 */
	public Date getDate() {
		return playDate;
	}
	
	/**
	 * Public method to set play date
	 */
	public void setDate(Date date) {
		this.playDate = date;
	}
}
