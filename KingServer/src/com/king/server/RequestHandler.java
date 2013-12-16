package com.king.server;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import com.king.helper.ScoreHelper;
import com.king.helper.SessionHelper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * The server requests handler
 * 
 * @author Andrei
 * 
 */
public class RequestHandler implements HttpHandler {
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sun.net.httpserver.HttpHandler#handle(com.sun.net.httpserver.HttpExchange
	 * )
	 */
	public void handle(HttpExchange exchange) {
        String response = null;
        int statusCode = 200;

        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> params = (Map<String, Object>)exchange.getAttribute("parameters");

            if (params.get("request").equals("login")) {            	
                response = SessionHelper.getSessionKey((Integer)params.get("userId"));
            } else if (params.get("request").equals("score")) {
                Integer userId = SessionHelper.getUserId(
                        (String)params.get("sessionkey"));

                if (userId != null) {
                	ScoreHelper.insertScore(userId, (Integer)params.get("levelId"), (Integer)params.get("score"));
                }
            } else if (params.get("request").equals("highscorelist")) {
                response = ScoreHelper.getHighestScores((Integer)params.get("levelId"));

                Headers headers = exchange.getResponseHeaders();
                headers.add("Content-Type", "text/csv");
                headers.add("Content-Disposition", "attachment;filename=myfilename.csv");
            }               
        } catch (Exception exception) {
            statusCode = 500;
        }
        
        OutputStream os = null;

        try {
			if (response != null && statusCode == 200) {
			    exchange.sendResponseHeaders(statusCode, response.length());
			    
			    os = exchange.getResponseBody();            
				os.write(response.toString().getBytes());
			} else {
			    exchange.sendResponseHeaders(statusCode, 0);
			}
		} catch (IOException e) {
		} finally {
			try {
				os.close();
			} catch (IOException e) {}
		}
    }
}
