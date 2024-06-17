import java.io.*;
import java.net.*;
/*
 * Louis Romeo
 * CSC 335 Assignment 9
 * Purpose: Implementation of a client in a client
 * 			server app.
 */
public class Client {
    
	public static void main(String[] args) {
        
		try {
            Socket socket = new Socket("localhost", 4000);

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String input;
            double number;
            do {
                System.out.print("Enter a number or -1 to quit: ");
                input = userInput.readLine();
                number = Double.parseDouble(input);
                out.println(input);
            } while (number != -1);

            String response = in.readLine();
            System.out.println(response);

            userInput.close();
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

