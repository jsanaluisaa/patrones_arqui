/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 *
 * @author PXNDX
 */
public class Service {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
    String SERVICE_NAME  = "timeDemo";
    String INSTANCE_NAME = "Time_Server_1";
    
    try {
      serverSocket = new ServerSocket();
      serverSocket.bind(
          new InetSocketAddress(InetAddress.getLocalHost(),
          0));    /*bind to any available port number*/
    }
    catch (IOException ioe) {
      System.err.println(
          "Could not bind a server socket to a free port: "+ioe);
      System.exit(1);
    }
    
    /* Create a descriptor for the service you are providing. */
    ServiceDescription descriptor = new ServiceDescription();
    descriptor.setAddress(serverSocket.getInetAddress());
    descriptor.setPort(serverSocket.getLocalPort());
    descriptor.setInstanceName(INSTANCE_NAME);
    System.out.println("Service details: "+descriptor);

    /* Read special note for code you should add here */

    /*
     * Set up a responder and give it the descriptor (above)
     * we want to publish. Start the responder, which
     * works in its own thread.
     */
    ServiceResponder responder =
        new ServiceResponder(SERVICE_NAME);
    responder.setDescriptor(descriptor);
    responder.startResponder();

    /* Back to the usual routine of servicing requests */
    System.out.println(
        "Responder listening. Now taking connections...");
    while (true) {
      try {
        Socket socket = serverSocket.accept();
        System.out.println(
            "Connection from: "+socket.getInetAddress());
        OutputStreamWriter writer =
            new OutputStreamWriter(socket.getOutputStream());
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
