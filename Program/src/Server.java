

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server
{
    static ServerSocket ss;
    static Socket s;
    static DataOutputStream dout;
    static DataInputStream din;
    public Server() {

    }

    public static void main(String[] args) {

        String a = JOptionPane.showInputDialog("Enter number of clients: ");
        int num = Integer.parseInt(a);
        try {
            ss = new ServerSocket(1201);
            s = ss.accept();
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());

            while (a != null){
                dout.writeInt(num);
            }




        } catch (IOException e) {
            e.printStackTrace();
        }





    }
}
