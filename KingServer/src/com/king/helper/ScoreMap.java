package com.king.helper;

import java.util.Comparator;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * The score map
 * @author Andrei
 *
 */
public class ScoreMap {
	/** the scores map */
	private ConcurrentSkipListSet<Score> scoresSet;
	
	public ScoreMap() {
		scoresSet = new ConcurrentSkipListSet<Score>(new Comparator<Score>() {

			/* (non-Javadoc)
			 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
			 */
			@Override
			public int compare(Score o1, Score o2) {
				return Integer.compare(o1.getScore(), o2.getScore());
			}
			
		});
	}
	
	/**
	 * Add function
	 * @param userId
	 * @param score
	 */
	public void add(Integer userId, Integer score) {				
		if (scoresSet.size() < 15 || scoresSet.first().getScore() < score) {
			Score currentScore = new Score(userId,score);
			if (scoresSet.contains(currentScore)) {
				scoresSet.remove(currentScore);
			}
			scoresSet.add(currentScore);
						
			if (scoresSet.size() > 15) {
				scoresSet.pollFirst();
			}
			
		}
	}
	
	/**
	 * Get high scores function
	 * @return
	 */
	public String getHighScores() {
		StringBuffer ret = new StringBuffer("");
		int counter = 0;
		for (Score currentScore: scoresSet.descendingSet()) {
			if (counter++ > 15) 
				break;
			
			ret.append(currentScore.getUserId()).append("=").append(currentScore.getScore()).append(",");						 
		}
		return ret.substring(0, ret.length() - 1);
	}
}