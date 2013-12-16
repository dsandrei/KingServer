package com.king.helper;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * The session helper
 * @author Andrei
 *
 */
public class SessionHelper {
	/** the session key map */
	private static volatile ConcurrentHashMap<String, Integer> sessionKeyMap = new ConcurrentHashMap<String, Integer>(50000, 0.75f, 1000);
	/** the task timer */
	private static ScheduledThreadPoolExecutor removeTimer = new ScheduledThreadPoolExecutor(20);
	
	/**
	 * Gets a session key for the user id
	 * @param userId
	 * @return
	 */
	public static String getSessionKey(Integer userId) {				
		final String sessionKey = Long.toString(Math.abs(MersenneThreadLocalRandom.current().nextLong(Long.MAX_VALUE)), 36);
		
		sessionKeyMap.putIfAbsent(sessionKey, userId);
		
		removeTimer.schedule(new Runnable() {
			public void run() {
				sessionKeyMap.remove(sessionKey);
			}
		}, 600, TimeUnit.SECONDS);
		
		return sessionKey;
	}
	
	/**
	 * Returns a user id for the specified session key
	 * @param sessionKey
	 * @return
	 */
	public static Integer getUserId(String sessionKey) {
		return sessionKeyMap.get(sessionKey); 
	}
}
