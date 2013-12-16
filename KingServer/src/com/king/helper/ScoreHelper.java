package com.king.helper;

import java.util.concurrent.ConcurrentHashMap;

/**
 * The score helper
 * @author Andrei
 *
 */
public class ScoreHelper {
	/** the level key map */
	private static volatile ConcurrentHashMap<Integer, ScoreMap> levelKeyMap = new ConcurrentHashMap<Integer, ScoreMap>(50000, 0.75f, 1000);
	
	/**
	 * Insert a score
	 * @param userId
	 * @param levelId
	 * @param score
	 */
	public static void insertScore(Integer userId, Integer levelId, Integer score) {
		ScoreMap currentLevelSet = levelKeyMap.get(levelId);
		if (currentLevelSet == null) {
			levelKeyMap.putIfAbsent(levelId, new ScoreMap());	
			currentLevelSet = levelKeyMap.get(levelId);
		}
		
		currentLevelSet.add(userId, score);
	}
	
	/**
	 * Get highest scores
	 * @param levelId
	 * @return
	 */
	public static String getHighestScores(Integer levelId) {
		ScoreMap currentLevelSet = levelKeyMap.get(levelId);
		if (currentLevelSet == null) {
			return "";
		}
		
		return currentLevelSet.getHighScores();
	}
}
