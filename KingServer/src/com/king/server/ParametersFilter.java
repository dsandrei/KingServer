package com.king.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;

/**
 * The parameters filter
 * 
 * @author Andrei
 * 
 */
public class ParametersFilter extends Filter {

	@Override
	public void doFilter(HttpExchange exchange, Chain chain) throws IOException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		boolean callFilter = false;
		String uri = exchange.getRequestURI().toString();
		String[] tokens = uri.split("[/?=]");

		if (tokens.length > 2) {
			try {
				if (tokens[2].equals("score") || tokens[2].equals("highscorelist")) {
					Integer levelId = new Integer(tokens[1]);
					if (levelId > 0) {
						parameters.put("levelId", levelId);
						parameters.put("request", tokens[2]);
	
						if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
							//this is a score posting; we need to get the session id
							String query = exchange.getRequestURI().getRawQuery();
							parseQuery(query, parameters);
							if (parameters.containsKey("sessionkey")) {						
							// parse the score only if we have a sessionKey
								InputStreamReader isr = null;
								BufferedReader br = null;
								try {
									isr = new InputStreamReader(
											exchange.getRequestBody(), "utf-8");
									br = new BufferedReader(isr);
									String postBody = br.readLine();
									Integer score = new Integer(postBody);
									parameters.put("score", score);
									callFilter = score >= 0;
								} catch (IOException e) {								
								} finally {
									try {
										br.close();
										isr.close();
									} catch (IOException e) {}
								}
							}
						} else {
							callFilter = true;
						}
					}
					
				} else if (tokens[2].equals("login")) {
					Integer userId = new Integer(tokens[1]);
					if (userId > 0) {
						parameters.put("userId", userId);
						parameters.put("request", tokens[2]);
						callFilter = true;
					}
				}
			} catch (NumberFormatException e) {} 
		}
		if (callFilter) {			
			exchange.setAttribute("parameters", parameters);
			chain.doFilter(exchange);
		} else {
			exchange.sendResponseHeaders(500, 0);
			OutputStream os = null;

			try {
				os = exchange.getResponseBody();
				os.close();
			} catch (IOException e) {
			}
		}		
	}
	
	/**
	 * Parse the query parameters
	 * @param query
	 * @param parameters
	 * @throws UnsupportedEncodingException
	 */
	private void parseQuery(String query, Map<String, Object> parameters)
			throws UnsupportedEncodingException {
		if (query != null) {
			String pairs[] = query.split("&");

			for (String pair : pairs) {
				String param[] = pair.split("=");

				String key = null;
				String value = null;
				if (param.length > 0) {
					key = URLDecoder.decode(param[0], "utf-8");
				}

				if (param.length > 1) {
					value = URLDecoder.decode(param[1], "utf-8");
				}

				parameters.put(key.toLowerCase(), value);
			}
		}
	}

	@Override
	public String description() {
		return "Parameter parser";
	}
}