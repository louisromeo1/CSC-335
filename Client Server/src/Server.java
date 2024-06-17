import java.io.*;
import java.net.*;
/*
 * Louis Romeo
 * CSC 335 Assignment 9
 * Purpose: Implementation of a server in a client
 * 			server app.
 */
public class Server {
    
	public static void main(String[] args) {
        
		try {
            ServerSocket serverSocket = new ServerSocket(4000);
            System.out.println("Server started.");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected.");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            double sum = 0.0;
            String input;
            
            while (!(input = in.readLine()).equals("-1")) {
                double number = Double.parseDouble(input);
                sum += number;
            }

            out.println("Sum: " + sum);

            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}