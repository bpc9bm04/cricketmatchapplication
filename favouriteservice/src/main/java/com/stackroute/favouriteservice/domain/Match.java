package com.stackroute.favouriteservice.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Altap
 *
 */
@Entity
@Table(name = "cricket_match")
public class Match {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "unique_id")
	private int unique_id;

	@Column(name = "team1")
	private String teamOne;

	@Column(name = "team2")
	private String teamTwo;

	@Column(name = "date")
	private String matchDate;

	@Column(name = "match_started", columnDefinition = "TINYINT(1)")
	private boolean matchStarted;
	
	@Column(name = "user_id")
	private String userId;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * @return the teamOne
	 */
	public String getTeamOne() {
		return teamOne;
	}

	/**
	 * @param teamOne
	 *            the teamOne to set
	 */
	public void setTeamOne(String teamOne) {
		this.teamOne = teamOne;
	}

	/**
	 * @return the teamTwo
	 */
	public String getTeamTwo() {
		return teamTwo;
	}

	/**
	 * @param teamTwo
	 *            the teamTwo to set
	 */
	public void setTeamTwo(String teamTwo) {
		this.teamTwo = teamTwo;
	}

	

	/**
	 * @return the unique_id
	 */
	public int getUnique_id() {
		return unique_id;
	}

	/**
	 * @param unique_id the unique_id to set
	 */
	public void setUnique_id(int unique_id) {
		this.unique_id = unique_id;
	}

	/**
	 * @return the matchDate
	 */
	public String getMatchDate() {
		return matchDate;
	}

	/**
	 * @param matchDate the matchDate to set
	 */
	public void setMatchDate(String matchDate) {
		this.matchDate = matchDate;
	}

	/**
	 * @return the matchStarted
	 */
	public boolean isMatchStarted() {
		return matchStarted;
	}

	/**
	 * @param matchStarted
	 *            the matchStarted to set
	 */
	public void setMatchStarted(boolean matchStarted) {
		this.matchStarted = matchStarted;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	
}
