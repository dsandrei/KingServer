package com.king.test;

import junit.framework.TestCase;

import org.junit.Test;

import com.king.helper.ScoreHelper;
import com.king.helper.SessionHelper;

/**
 * The helper test
 * @author Andrei
 *
 */
public class HelperTest extends TestCase {
	
	@Test
	public void testSessionKeyGeneration() {
		assertNotNull(SessionHelper.getSessionKey(1));
	}
	
	@Test
	public void testGetUserId() {
		String sessionKey = SessionHelper.getSessionKey(1);
		assertEquals(new Integer(1), SessionHelper.getUserId(sessionKey));
	}
	
	@Test
	public void testGetHighScoreList() {
		String sessionKey = SessionHelper.getSessionKey(1);
		ScoreHelper.insertScore(SessionHelper.getUserId(sessionKey), 1, 100);
		assertEquals("1=100", (ScoreHelper.getHighestScores(1)));
	}
}
