
import java.util.*;
import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class Client
{
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;
    public Client() {

    }


    public static void main(String[] args) throws IOException, UnknownHostException
    {
        int num;
         s = new Socket("127.0.0.1", 1201);
         din = new DataInputStream(s.getInputStream());
         dout = new DataOutputStream(s.getOutputStream());

         Scanner sc = new Scanner(s.getInputStream());
         num = sc.nextInt();


    }

}
