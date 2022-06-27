/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author PXNDX
 */
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class TimeServer {
  public static void main(String[] args) {

    ServerSocket serverSocket = null;

    /* section one */
    try {
      serverSocket = new ServerSocket();
      serverSocket.bind(
            new InetSocketAddress(
                  InetAddress.getLocalHost(),
                  9999));
    }
    catch (IOException ioe) {
      System.err.println(
            "Could not bind a server socket to port 9999: "+ioe);
      System.exit(1);
    }

    /* section two */
    System.out.println("Server is now taking connections...");
    while (true) {
      try {
        Socket socket = serverSocket.accept();
        System.out.println("Connection from: "+
                           socket.getInetAddress());
        OutputStreamWriter writer = new OutputStreamWriter(
              socket.getOutputStream());
        writer.write(new Date().toString()+"rn");
        writer.flush();
        socket.close();
      }
      catch (IOException ie) {
        System.err.println("Exception: "+ie);
      }
    }

  }

}