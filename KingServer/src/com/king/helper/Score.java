package com.king.helper;

/**
 * Score 
 * @author Andrei
 *
 */
public class Score { // immutable class, can be freely shared
	/** the user id */
	private final Integer userId;
	/** the score */
	private final Integer score;
		
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
	 * @return the score
	 */
	public Integer getScore() {
		return score;
	}
	
	/* 
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Score)) {
			return false;
		}
		Score other = (Score)obj;
		
		return this.getUserId().equals(other.getUserId());
	}

	/* 
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return this.getUserId(); // overriding hashCode is mandatory when overriding equals 
	}
}
