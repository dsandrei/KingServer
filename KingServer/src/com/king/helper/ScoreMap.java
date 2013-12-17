package com.king.helper;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * The score map
 * @author Andrei
 *
 */
public class ScoreMap extends CopyOnWriteArrayList<Score> {
	/** the serial versionUID */
	private static final long serialVersionUID = 1L;	
	
	/**
	 * Add function
	 * @param userId
	 * @param score
	 */
	public void addScore(Integer userId, Integer score) {	
		if (this.size() < 15 || this.get(0).getScore() < score) {
			Score currentScore = new Score(userId,score);
			if (this.contains(currentScore)) {
				this.remove(currentScore);
			}
			
			this.add(getIndexToAdd(currentScore), currentScore);
			
			if (this.size() > 15) {
				this.remove(0);
			}
			
		}
	}
	
	private int getIndexToAdd(Score currentScore) {
		
		int low = 0;
		int high = this.size() - 1;
		
		while (low <= high) {
	       int mid = (low + high) >>> 1;
	       int cmp = this.get(mid).getScore().compareTo(currentScore.getScore());
			   if (cmp < 0)
				   low = mid + 1;
			   else if (cmp > 0)
				   high = mid - 1;
			   else
				   return mid; // key found		         
		}
		return low;
	}
	
	/**
	 * Get high scores function
	 * @return
	 */
	public String getHighScores() {
		StringBuffer ret = new StringBuffer("");
		for (int i=this.size()-1;i >=0; i--) {
			
			ret.append(this.get(i).getUserId()).append("=").append(this.get(i).getScore()).append(",");						 
		}
		return ret.substring(0, ret.length() - 1);
	}
}