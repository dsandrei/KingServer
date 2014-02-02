package com.king.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The score map
 * @author Andrei
 *
 */
public class ScoreMap {
	/** the array list */
	private static List<Score> scoreList; // composition is more flexible then inheritance
										  // prefer type specifiers to direct implementations
										  // the add operation has to be manually synchronized, there is no point in using a concurrent structure 
	/** The lock protecting all mutators */
    private transient final ReentrantLock lock = new ReentrantLock(); // private lock object
		
	/**
	 * Class constructor
	 */
	public ScoreMap() {
		scoreList = new ArrayList<Score>();
	}
	
	/**
	 * Add function
	 * @param userId
	 * @param score
	 */
	public void addScore(Integer userId, Integer score) {	
		final ReentrantLock lock = this.lock;
        lock.lock();
        try {
			if (scoreList.size() < 15 || scoreList.get(0).getScore() < score) {
				Score currentScore = new Score(userId,score);
				if (scoreList.contains(currentScore)) {
					scoreList.remove(currentScore);
				}
				
				scoreList.add(getIndexToAdd(currentScore), currentScore);
				
				if (scoreList.size() > 15) {
					scoreList.remove(0);
				}			
			}
        } finally {
            lock.unlock();
        }
	}
	
	private int getIndexToAdd(Score currentScore) {	
		int low = 0;
		int high = scoreList.size() - 1;
		
		while (low <= high) {
	       int mid = (low + high) >>> 1;
	       int cmp = scoreList.get(mid).getScore().compareTo(currentScore.getScore());
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
		StringBuilder ret = new StringBuilder(""); // StringBuffer is synchronized, is considered deprecated
		Score[] scoreSnapShot = new Score[0];
		
		scoreSnapShot = scoreList.toArray(scoreSnapShot);
		
		for (int i=scoreSnapShot.length-1;i >=0; i--) {			
			ret.append(scoreSnapShot[i].getUserId()).append("=").append(scoreSnapShot[i].getScore()).append(",");						 
		}
		return ret.substring(0, ret.length() - 1);
	}
}