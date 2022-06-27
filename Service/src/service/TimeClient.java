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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

public class TimeClient {
  public static void main(String[] args) {
    try {
      Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
      InputStreamReader reader =
          new InputStreamReader(socket.getInputStream());
      BufferedReader bufferedReader = new BufferedReader(reader);
      String line = bufferedReader.readLine();
      System.out.println(line);
      socket.close();
    }
    catch (IOException ie) {
      System.err.println("Exception: "+ie);
      System.exit(1);
    }

    System.out.println("nThat's all, folks.");
    System.exit(0);
  }
}