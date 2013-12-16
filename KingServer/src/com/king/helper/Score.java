package com.king.helper;

public class Score {
	/** the user id */
	private Integer userId;
	/** the score */
	private Integer score;
	
	/**
	 * No-argument constructor
	 */
	public Score() {}
	
	/**
	 * Full-argument constructor
	 * @param userId
	 * @param score
	 */
	public Score(Integer userId, Integer score) {
		this.userId = userId;
		this.score = score;
	}
	
	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * @return the score
	 */
	public Integer getScore() {
		return score;
	}
	/**
	 * @param score the score to set
	 */
	public void setScore(Integer score) {
		this.score = score;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Score)) {
			return false;
		}
		Score other = (Score)obj;
		
		return this.getUserId() == other.userId;
	}
}
