/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javanetworking.sendobject;

import java.net.*;
import java.io.*;

public class SimpleClient {

    public static void main(String args[]) {
        try {
            Socket s = new Socket("localhost", 2002);
            OutputStream os = s.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            testobject to = new testobject(1, "object from client");
            oos.writeObject(to);
            oos.writeObject(new String("another object from the client"));
            oos.close();
            os.close();
            s.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
