package com.king.test;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.king.helper.ScoreHelper;
import com.king.helper.SessionHelper;

import junit.framework.TestCase;

/**
 * The main test class
 * @author Andrei
 *
 */
@RunWith(MultiThreadedRunner.class)
public class AppTest extends TestCase {
	
	@Test
	public void testApp() {
		String firstSessionKey = SessionHelper.getSessionKey(1234);
		String secondSessionKey = SessionHelper.getSessionKey(5678);
		
		ScoreHelper.insertScore(SessionHelper.getUserId(firstSessionKey), 1, 10);
		ScoreHelper.insertScore(SessionHelper.getUserId(secondSessionKey), 1, 20);
		
		System.out.println("1:"+ScoreHelper.getHighestScores(1));
	}
	
	@Test
	public void testAppSecondThread() {
		
		String firstSessionKey = SessionHelper.getSessionKey(9876);
		String secondSessionKey = SessionHelper.getSessionKey(5432);
		
		ScoreHelper.insertScore(SessionHelper.getUserId(firstSessionKey), 1, 30);
		ScoreHelper.insertScore(SessionHelper.getUserId(secondSessionKey), 1, 40);
		
		System.out.println("2:"+ScoreHelper.getHighestScores(1));
	}
	
	@Test
	public void testAppThirdThread() {
		
		String firstSessionKey = SessionHelper.getSessionKey(1098);
		String secondSessionKey = SessionHelper.getSessionKey(7654);
		
		ScoreHelper.insertScore(SessionHelper.getUserId(firstSessionKey), 1, 50);
		ScoreHelper.insertScore(SessionHelper.getUserId(secondSessionKey), 1, 60);
		
		System.out.println("3:"+ScoreHelper.getHighestScores(1));
	}
	
	@Test
	public void testAppFourthThread() {
		
		String firstSessionKey = SessionHelper.getSessionKey(3210);
		String secondSessionKey = SessionHelper.getSessionKey(2345);
		
		ScoreHelper.insertScore(SessionHelper.getUserId(firstSessionKey), 1, 70);
		ScoreHelper.insertScore(SessionHelper.getUserId(secondSessionKey), 1, 80);
		
		System.out.println("4:"+ScoreHelper.getHighestScores(1));
	}
	
	@Test
	public void testAppFifthThread() {
		
		String firstSessionKey = SessionHelper.getSessionKey(6789);
		String secondSessionKey = SessionHelper.getSessionKey(1111);
		String thirdSessionKey = SessionHelper.getSessionKey(2222);
		String fourthSessionKey = SessionHelper.getSessionKey(3333);
		String fifthSessionKey = SessionHelper.getSessionKey(4444);
		String sixthSessionKey = SessionHelper.getSessionKey(5555);
		String seventhSessionKey = SessionHelper.getSessionKey(6666);
		String eigthSessionKey = SessionHelper.getSessionKey(7777);
		
		ScoreHelper.insertScore(SessionHelper.getUserId(firstSessionKey), 1, 90);
		ScoreHelper.insertScore(SessionHelper.getUserId(secondSessionKey), 1, 100);
		ScoreHelper.insertScore(SessionHelper.getUserId(thirdSessionKey), 1, 110);
		ScoreHelper.insertScore(SessionHelper.getUserId(fourthSessionKey), 1, 120);
		ScoreHelper.insertScore(SessionHelper.getUserId(fifthSessionKey), 1, 130);
		ScoreHelper.insertScore(SessionHelper.getUserId(sixthSessionKey), 1, 140);
		ScoreHelper.insertScore(SessionHelper.getUserId(seventhSessionKey), 1, 150);
		ScoreHelper.insertScore(SessionHelper.getUserId(eigthSessionKey), 1, 160);
	}
	
	@Test
	public void testAppSixthThread() {
		System.out.println("6:"+ScoreHelper.getHighestScores(1));
	}
}
