package com.king.app;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import com.king.server.ParametersFilter;
import com.king.server.RequestHandler;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;
/**
 * The main class for the server application
 * @author Andrei
 *
 */
public class MainApp {
	/**
	 * Main function of the application
	 * @param args
	 */
	public static void main(String[] args) throws IOException {

        HttpServer server = HttpServer.create(new InetSocketAddress(8081), 0);
        server.setExecutor(Executors.newCachedThreadPool());

        HttpContext context = server.createContext("/", new RequestHandler());
        context.getFilters().add(new ParametersFilter());

        server.start();
        System.out.println("Server UP");
	}
}
