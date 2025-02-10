import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
public class Main {
  public static void main(String[] args) {
    // You can use print statements as follows for debugging, they'll be visible
    // when running tests.
    System.out.println("Logs from your program will appear here!");
    // Uncomment this block to pass the first stage
    ServerSocket serverSocket = null;
    Socket clientSocket = null;
    try {
      serverSocket = new ServerSocket(4221);
      serverSocket.setReuseAddress(true);
      clientSocket = serverSocket.accept(); // Wait for connection from client.
      System.out.println("accepted new connection");
    } catch (IOException e) {
      System.out.println("IOException: " + e.getMessage());
    }
    BufferedReader clientIn = null;
    PrintWriter clientOut = null;
    String input = null;
    try {
      clientIn = new BufferedReader(
          new InputStreamReader(clientSocket.getInputStream()));
      clientOut = new PrintWriter(clientSocket.getOutputStream(), true);
    } catch (IOException e) {
      System.out.println("IOException " + e.getMessage());
    }
    try {
      input = clientIn.readLine();
      // System.out.println(input);
      String get[] = input.split(" ");
      if (get[1].equals("/")) {
        clientOut.println("HTTP/1.1 200 OK\r\n\r\n");
      } else {
        clientOut.println("HTTP/1.1 404 Not Found\r\n\r\n");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    try {
      clientIn.close();
      clientOut.close();
      clientSocket.close();
      serverSocket.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

