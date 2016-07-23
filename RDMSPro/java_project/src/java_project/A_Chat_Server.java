
package java_project;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;



public class A_Chat_Server {
    Socket SOCK=null;
   public static ArrayList<Socket> ConnectionArray =new ArrayList<Socket>(); 
    public static ArrayList<String> CurrentUsers =new ArrayList<String>();
   public static Dictionary d = new Hashtable();
    public static Server_GUI sgui=new Server_GUI();
       //  PrintStream p;   
    
    
    public static void main (String args[]) throws IOException{
       
        try{
            final int PORT=444;
            ServerSocket SERVER=new ServerSocket(PORT);
            //sgui.SinitComponents();
            sgui.BuildSA_Chat_Client_GUI();//Create a server GUI
            System.out.println("Waiting for connections");
            
            while(true){
                Socket SOCK=SERVER.accept();
                ConnectionArray.add(SOCK);
                System.out.println("Client connected from:"+SOCK.getLocalAddress().getHostName());
               // sgui.STA_sCONVERSATION.append("Client connected from:"+SOCK.getLocalAddress().getHostName());
                AddUserName(SOCK);
                A_Chat_Server_Return CHAT=new  A_Chat_Server_Return(SOCK);
                Thread X =new Thread(CHAT);//Takes input from the the current socket and sends to all the clients continuously
                 Thread Y = new Thread(new GetFile());
                  X.start();
                Y.start();
            }
      }
        catch(Exception X){
            System.out.println(X);
           // sgui.STA_CONVERSATION.append(X.printStackTrace);
        
    }
}
    public static void AddUserName(Socket X) throws IOException{
        Scanner INPUT=new Scanner(X.getInputStream());
        String Username=INPUT.nextLine();
        d.put(Username, X.getInetAddress().getHostName());
        CurrentUsers.add(Username);
        Enumeration e1,e;
        for (e = d.keys(),e1 = d.elements(); e.hasMoreElements() && e.hasMoreElements();) 
            System.out.println(e.nextElement()+"################"+e1.nextElement());
        for(int i=1;i<=A_Chat_Server.ConnectionArray.size();i++){
            Socket TEMP_SOCK=(Socket) A_Chat_Server.ConnectionArray.get(i-1);
           PrintWriter OUT=new PrintWriter(TEMP_SOCK.getOutputStream()); 
           OUT.println("?#!"+CurrentUsers);
           OUT.flush();
        }
        
    }
}